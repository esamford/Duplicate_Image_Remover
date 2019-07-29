package duplicate_image_remover;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static sun.jvm.hotspot.HelloWorld.e;

public class CompareProcess implements Runnable
{
    String cancelCompareMessage = "Thread has been canceled manually.";
    volatile boolean stopThread = false;
    
    private class fileAndRatio {
        File file;
        double hwRatio;
    }
    
    public enum SearchType {
        TWO_IMAGES,
        ONE_FOLDER,
        TWO_FOLDERS
    }
    SearchType selectedSearchMethod;
    File[] targetFolder = new File[2];
    File[] currentFiles = new File[2];
    
    DIR_Window parentFrame;
    volatile boolean waitingForUser;
    
    long timeWaitingForUser = 0;
    long timeWaitingForStartNum = 0;
    int numFilesDeleted = 0;
    long totalBytesRemoved = 0;
    int finalCurrentProgress = 0;
    int finalMaxProgress = 0;
    boolean invalidFileTypesFound = false;
    
    // === === === SETTERS === === ===
    
    public void setParent(DIR_Window newParent) {
        this.parentFrame = newParent;
    }
    public void setSearchType(SearchType newSearchType) { this.selectedSearchMethod = newSearchType; }
    public void setTargetFolder1(File newTargetFolder) {
        if (newTargetFolder.isDirectory()) { this.targetFolder[0] = newTargetFolder; }
    }
    public void setTargetFolder2(File newTargetFolder) {
        if (newTargetFolder.isDirectory()) { this.targetFolder[1] = newTargetFolder; }
    }
    public void setTargetFiles(File newFile1, File newFile2) {
        if (!newFile1.isDirectory()) { this.targetFolder[0] = newFile1; }
        if (!newFile2.isDirectory()) { this.targetFolder[1] = newFile2; }
    }
    
    // === === === VALIDATION FUNCTIONS === === ===
    
    public void checkForCompareTwoImages() throws IOException {
        if (this.targetFolder[0] == null || this.targetFolder[1] == null)
        { throw new IOException("The class must have both image files before they can be compared."); }
        
        if (this.targetFolder[0].isDirectory() || this.targetFolder[1].isDirectory())
        { throw new IOException("Neither of the image files can be a directory. They must both be non-directory files."); }
        
        if (!checkImageValidity(this.targetFolder[0]))
        { throw new IOException("Image file one is not a valid image file: " + this.targetFolder[0].getName()); }
        
        if (!checkImageValidity(this.targetFolder[1]))
        { throw new IOException("Image file two is not a valid image file: " + this.targetFolder[1].getName()); }
    }
    public void checkForCompareOneFolder() throws IOException {
        if (!this.targetFolder[0].isDirectory()) { throw new IOException("The target file provided was not a directory."); }
    }
    public void checkForCompareTwoFolders() throws IOException {
        if (!this.targetFolder[0].isDirectory() || !this.targetFolder[1].isDirectory())
        { throw new IOException("One or more of the target files provided were not directories."); }
    }
    
    // === === === ACTION LISTENER FUNCTIONS === === ===
    
    private void deleteFile(File deleteFile) {
        try {
            int result = JOptionPane.showConfirmDialog(this.parentFrame, "Warning: This will permenantly delete the image and you will not be able to restore it from the Recycle Bin. Are you sure you want to delete this image?", "Delete Image?", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)
            {
                long tempByteCount = deleteFile.length();
                if (deleteFile.delete())
                {
                    totalBytesRemoved += tempByteCount;
                    numFilesDeleted++;
                    waitingForUser = false;
                }
                else
                {
                    String errorMSG = "The program was unable to delete the following file: " + deleteFile.getName();
                    JOptionPane.showMessageDialog(this.parentFrame, errorMSG, "Unable to Delete Image", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch (Exception ex)
        {
            String errorMSG = "The program was unable to delete the following file: " + deleteFile.getName();
            errorMSG += "\n\nThe exception that was thrown is: " + ex.toString();
            errorMSG += "\nThe error message from that exception is: " + ex.getMessage();
            JOptionPane.showMessageDialog(this.parentFrame, errorMSG, "Unable to Delete Image", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteImageOneButtonClicked() {
        if (this.currentFiles[0].exists())
        {
            deleteFile(this.currentFiles[0]);
        }
    }
    private void deleteImageTwoButtonClicked() {
        if (this.currentFiles[1].exists())
        {
            deleteFile(this.currentFiles[1]);
        }
    }
    
    // === === === COMPARISON FUNCTIONS === === ===
    
    private void processUserDecision(BufferedImage imageBuffer1, BufferedImage imageBuffer2, 
                                     File image1, File image2, float percentSimilar) throws IOException {
        displayImages(imageBuffer1, imageBuffer2);
        displayFileInfo(image1, image2, percentSimilar);
        this.currentFiles[0] = image1;
        this.currentFiles[1] = image2;
        
        System.gc();
        enableChoiceButtons(true);
        
        if (this.parentFrame.getCHKBX_Settings_SoundNotifications().isSelected())
        { Toolkit.getDefaultToolkit().beep(); }
        waitingForUser = true;
        int timeWaited = 0, sleepTime = 250, notificationSeconds = 60;
        long startTime = System.currentTimeMillis();
        try {
            while (waitingForUser) {
                Thread.sleep(sleepTime);
                if (this.parentFrame.getCHKBX_Settings_RepeatNotificationSound().isSelected() && timeWaited * sleepTime >= notificationSeconds * 1000)
                {
                    Toolkit.getDefaultToolkit().beep();
                    timeWaited = 0;
                }
                else { timeWaited++; }
            }
        } catch (InterruptedException ex) { }
        this.timeWaitingForUser += System.currentTimeMillis() - startTime;
        
        clearFileInfo();
        clearDisplayedImages();
        enableChoiceButtons(false);
        
        if (stopThread) { throw new IOException(cancelCompareMessage); }
    }
    private boolean checkImageValidity(File checkFile) {
        if (!checkFile.exists()) { return false; }
        CompareImages validate = new CompareImages();        
        return validate.checkIfValidImage(checkFile);
    }
    private void checkTwoImages(File file1, File file2) throws IOException {
        checkForCompareTwoImages();
        
        CompareImages compare = new CompareImages();
        BufferedImage[] imgBuff = new BufferedImage[2];
        CompareImages.CompareMethod compareMethod = CompareImages.CompareMethod.SUBTRACT_COLOR;
        
        try
        {
            compare.setImage(file1, CompareImages.FileNum.FIRST);
            compare.setImage(file2, CompareImages.FileNum.SECOND);
            
            float percentSimilar = compare.getPercentSimilar(compareMethod);
            
            if (percentSimilar >= 0)
            {
                imgBuff[0] = compare.importImage(file1);
                imgBuff[1] = compare.importImage(file2);

                processUserDecision(imgBuff[0], imgBuff[1], file1, file2, percentSimilar);
            }
            else
            {
                long startTime = System.currentTimeMillis();
                JOptionPane.showMessageDialog(this.parentFrame, "You cannot compare two images with different height/width ratios.", "Unequal Size Ratios", JOptionPane.ERROR_MESSAGE);
                timeWaitingForStartNum += System.currentTimeMillis() - startTime;
            }
        } 
        catch (IOException ex)
        {
            if (ex.getMessage().equals("-1"))
            {
                invalidFileTypesFound = true;
            }
            throw ex;
        }
    }
    private void checkOneFolder(File folder) throws IOException {
        checkForCompareOneFolder();
        if (folder.isDirectory())
        {
            this.parentFrame.getJPRGSBR_Choice_TotalProgress().setString("Collecting image list...");
            ArrayList<fileAndRatio> allImageFiles = getImagesInFolder(folder, this.parentFrame.getCHKBX_SIaC_IncludeSubfoldersInFolder1().isSelected());
            this.parentFrame.getJPRGSBR_Choice_TotalProgress().setString("Sorting list...");
            allImageFiles = sortFileList(allImageFiles);
            this.parentFrame.setLBL_CompareInfo_NumberOfFilesInF1(allImageFiles.size());
            
            int progressMax = getMaxProgressOneFolder(allImageFiles.size()), progressCurrent = 0;
            setProgress(0, progressMax);
            
            int[] startNum = {0, 0};
            if (progressMax >= 1000)
            {
                //I would like to calculate this with math to make it run faster, but this should work for now.
                long userNum = askUserForStartNum(progressMax);
                boolean exit = false;
                for (int x = 0; x < allImageFiles.size() - 1; x++)
                {
                    if (exit) { break; }
                    long adjustVal = 0;
                    for (int y = x; y > 0; y--) { adjustVal += y; }
                    for (int y = x + 1; y < allImageFiles.size(); y++)
                    {
                        if (exit) { break; }
                        if (userNum == (x * (allImageFiles.size())) + y - x - adjustVal)
                        {
                            startNum[0] = x;
                            startNum[1] = y;
                            exit = true;
                        }
                    }
                }
            }
            
            CompareImages.CompareMethod compareMethod = CompareImages.CompareMethod.SUBTRACT_COLOR;
            CompareImages compare = new CompareImages();
            
            int[] imgInt = new int[2];
            for (imgInt[0] = startNum[0]; imgInt[0] < allImageFiles.size() - 1; imgInt[0]++)
            {
                if (stopThread) { break; }
                
                int adjustVal = 0;
                for (int x = imgInt[0]; x > 0; x--) { adjustVal += x; }
                
                if (!allImageFiles.get((imgInt[0])).file.exists())
                {
                    allImageFiles.remove(imgInt[0]--);
                    progressMax = getMaxProgressOneFolder(allImageFiles.size());
                    this.parentFrame.setLBL_CompareInfo_NumberOfFilesInF1(allImageFiles.size());
                }
                else
                {
                    try {
                        compare.setImage(allImageFiles.get((imgInt[0])).file, CompareImages.FileNum.FIRST);

                        for (imgInt[1] = imgInt[0] + 1; imgInt[1] < allImageFiles.size(); imgInt[1]++)
                        {
                            if (stopThread) { break; }
                            if (startNum[1] > 0)
                            {
                                imgInt[1] = startNum[1];
                                startNum[1] = -1;
                            }
                            
                            progressCurrent = (imgInt[0] * (allImageFiles.size())) + imgInt[1] - imgInt[0] - adjustVal;
                            setProgress(progressCurrent, progressMax);
                            
                            if (!allImageFiles.get((imgInt[1])).file.exists())
                            {
                                allImageFiles.remove(imgInt[1]--);
                                progressMax = getMaxProgressOneFolder(allImageFiles.size());
                                this.parentFrame.setLBL_CompareInfo_NumberOfFilesInF1(allImageFiles.size());
                            }
                            else
                            {
                                //Make sure that this image pairing is proportional.
                                double ratioDifference = allImageFiles.get((imgInt[0])).hwRatio - allImageFiles.get((imgInt[1])).hwRatio;
                                if (ratioDifference < 0) { ratioDifference *= -1; }
                                if (ratioDifference >= compare.getProportionError()) {
                                    break; }
                                
                                try {
                                    compare.setImage(allImageFiles.get((imgInt[1])).file, CompareImages.FileNum.SECOND);
                                    
                                    float percentSimilar = compare.getPercentSimilar(compareMethod, this.parentFrame.getSLDR_MinimumSimilarityThreshold());
                                    if (percentSimilar >= 0 && percentSimilar * 100 >= this.parentFrame.getSLDR_MinimumSimilarityThreshold().getValue())
                                    {
                                        processUserDecision(compare.getImage(CompareImages.FileNum.FIRST),
                                                            compare.getImage(CompareImages.FileNum.SECOND),
                                                            allImageFiles.get((imgInt[0])).file,
                                                            allImageFiles.get((imgInt[1])).file,
                                                            percentSimilar);
                                        
                                        if (!allImageFiles.get((imgInt[0])).file.exists())
                                        {
                                            allImageFiles.remove(imgInt[0]--);
                                            progressMax = getMaxProgressOneFolder(allImageFiles.size());
                                            this.parentFrame.setLBL_CompareInfo_NumberOfFilesInF1(allImageFiles.size());
                                            break;
                                        }
                                        else if (!allImageFiles.get((imgInt[1])).file.exists())
                                        {
                                            allImageFiles.remove(imgInt[1]--);
                                            progressMax = getMaxProgressOneFolder(allImageFiles.size());
                                            this.parentFrame.setLBL_CompareInfo_NumberOfFilesInF1(allImageFiles.size());
                                        }
                                    }
                                }
                                catch (IOException ex)
                                {
                                    if (ex.getMessage().equals(cancelCompareMessage))
                                    { throw ex;}
                                    else if (ex.getMessage().equals("-1")) //If an invalid file had been found, remove it from the list.
                                    {
                                        ImageIcon img = new ImageIcon(allImageFiles.get((imgInt[0])).file.getAbsolutePath());
                                        if (img.getIconHeight() < 1 || img.getIconWidth() < 1)
                                        {
                                            invalidFileTypesFound = true;
                                            allImageFiles.remove(imgInt[0]--);
                                            progressMax = getMaxProgressOneFolder(allImageFiles.size());
                                            this.parentFrame.setLBL_CompareInfo_NumberOfFilesInF1(allImageFiles.size());
                                            break;
                                        }
                                        img = new ImageIcon(allImageFiles.get((imgInt[1])).file.getAbsolutePath());
                                        if (img.getIconHeight() < 1 || img.getIconWidth() < 1)
                                        {
                                            invalidFileTypesFound = true;
                                            allImageFiles.remove(imgInt[1]--);
                                            progressMax = getMaxProgressOneFolder(allImageFiles.size());
                                            this.parentFrame.setLBL_CompareInfo_NumberOfFilesInF1(allImageFiles.size());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    catch (IOException ex)
                    {
                        if (ex.getMessage().equals(cancelCompareMessage))
                        {
                            finalCurrentProgress = progressCurrent;
                            finalMaxProgress = progressMax;
                            throw ex;
                        }
                    }
                }
                System.gc();
            }
            finalCurrentProgress = progressCurrent;
            finalMaxProgress = progressMax;
        }
    }
    private void checkTwoFolders(File folderOne, File folderTwo) throws IOException {
        checkForCompareTwoFolders();
        
        if (folderOne.isDirectory() && folderTwo.isDirectory())
        {
            this.parentFrame.getJPRGSBR_Choice_TotalProgress().setString("Collecting image lists...");
            ArrayList<fileAndRatio> allFolderOneImages = getImagesInFolder(folderOne, this.parentFrame.getCHKBX_SIaC_IncludeSubfoldersInFolder1().isSelected());
            ArrayList<fileAndRatio> allFolderTwoImages = getImagesInFolder(folderTwo, this.parentFrame.getCHKBX_SIaC_IncludeSubfoldersInFolder2().isSelected());
            this.parentFrame.getJPRGSBR_Choice_TotalProgress().setString("Sorting lists...");
            allFolderOneImages = sortFileList(allFolderOneImages);
            allFolderTwoImages = sortFileList(allFolderTwoImages);
            this.parentFrame.setLBL_CompareInfo_NumberOfFilesInF1(allFolderOneImages.size());
            this.parentFrame.setLBL_CompareInfo_NumberOfFilesInF2(allFolderTwoImages.size());
            
            int progressMax = allFolderOneImages.size() * allFolderTwoImages.size(), progressCurrent = 0;
            setProgress(0, progressMax);
            
            int[] startNum = {0, 0};
            if (progressMax >= 1000)
            {
                long userNum = askUserForStartNum(progressMax);
                startNum[0] = (int) userNum / allFolderTwoImages.size();
                startNum[1] = (int) userNum % allFolderTwoImages.size();
            }
            
            CompareImages.CompareMethod compareMethod = CompareImages.CompareMethod.SUBTRACT_COLOR;
            CompareImages compare = new CompareImages();
            
            int[] imgInt = new int[2];
            for (imgInt[0] = startNum[0]; imgInt[0] < allFolderOneImages.size(); imgInt[0]++)
            {
                if (stopThread) { break; }
                
                if (!allFolderOneImages.get((imgInt[0])).file.exists())
                {
                    allFolderOneImages.remove(imgInt[0]--);
                    progressMax = allFolderOneImages.size() * allFolderTwoImages.size();
                    this.parentFrame.setLBL_CompareInfo_NumberOfFilesInF1(allFolderOneImages.size());
                }
                else
                {
                    try {
                        compare.setImage(allFolderOneImages.get((imgInt[0])).file, CompareImages.FileNum.FIRST);

                        for (imgInt[1] = 0; imgInt[1] < allFolderTwoImages.size(); imgInt[1]++)
                        {
                            if (stopThread) { break; }
                            else if (startNum[1] > 0)
                            {
                                imgInt[1] = startNum[1];
                                startNum[1] = -1;
                            }
                            
                            progressCurrent = allFolderTwoImages.size() * imgInt[0] + imgInt[1];
                            setProgress(progressCurrent, progressMax);
                            
                            if (!allFolderTwoImages.get((imgInt[1])).file.exists())
                            {
                                allFolderTwoImages.remove(imgInt[1]--);
                                progressMax = allFolderOneImages.size() * allFolderTwoImages.size();
                                this.parentFrame.setLBL_CompareInfo_NumberOfFilesInF2(allFolderTwoImages.size());
                            }
                            else
                            {
                                //Make sure that whatever image pairing is up next is proportional
                                double ratioDifference = allFolderOneImages.get((imgInt[0])).hwRatio - 
                                                         allFolderTwoImages.get((imgInt[1])).hwRatio;
                                if (ratioDifference <= compare.getProportionError() * -1) { break; } //If the second folder's file is too high out of range, break.
                                else if (ratioDifference >= compare.getProportionError())
                                {
                                    for (; imgInt[1] < allFolderTwoImages.size(); imgInt[1]++)
                                    {
                                        progressCurrent = allFolderTwoImages.size() * imgInt[0] + imgInt[1];
                                        setProgress(progressCurrent, progressMax);
                                        
                                        ratioDifference = allFolderOneImages.get((imgInt[0])).hwRatio - 
                                                          allFolderTwoImages.get((imgInt[1])).hwRatio;
                                        if (ratioDifference <= compare.getProportionError() * -1) { break; } //If it's too high, break and exit the nested loop.
                                        if (ratioDifference < 0) { ratioDifference *= -1; }
                                        if (ratioDifference < compare.getProportionError()) { break; } //If it falls within range, break and continue the nested loop.
                                    }
                                    if (ratioDifference <= compare.getProportionError() * -1) { break; }
                                }
                                
                                try {
                                    String file1Path = allFolderOneImages.get(imgInt[0]).file.getAbsolutePath();
                                    String file2Path = allFolderTwoImages.get((imgInt[1])).file.getAbsolutePath();
                                    if (file1Path.equalsIgnoreCase(file2Path)) { break; } //Make sure not to compare an image with itself.
                                    
                                    compare.setImage(allFolderTwoImages.get((imgInt[1])).file, CompareImages.FileNum.SECOND);
                                    
                                    float percentSimilar = compare.getPercentSimilar(compareMethod, this.parentFrame.getSLDR_MinimumSimilarityThreshold());
                                    
                                    if (progressCurrent == progressMax - 1) //Make sure the program says 100% complete once finished.
                                    {
                                        progressCurrent = progressMax;
                                        setProgress(progressCurrent, progressMax);
                                    }
                                    
                                    if (percentSimilar >= 0 && percentSimilar * 100 >= this.parentFrame.getSLDR_MinimumSimilarityThreshold().getValue())
                                    {
                                        processUserDecision(compare.getImage(CompareImages.FileNum.FIRST),
                                                            compare.getImage(CompareImages.FileNum.SECOND),
                                                            allFolderOneImages.get((imgInt[0])).file,
                                                            allFolderTwoImages.get((imgInt[1])).file,
                                                            percentSimilar);
                                        
                                        if (!allFolderOneImages.get((imgInt[0])).file.exists())
                                        {
                                            allFolderOneImages.remove(imgInt[0]--);
                                            progressMax = allFolderOneImages.size() * allFolderTwoImages.size();
                                            this.parentFrame.setLBL_CompareInfo_NumberOfFilesInF1(allFolderOneImages.size());
                                            break;
                                        }
                                        else if (!allFolderTwoImages.get((imgInt[1])).file.exists())
                                        {
                                            allFolderTwoImages.remove(imgInt[1]--);
                                            progressMax = allFolderOneImages.size() * allFolderTwoImages.size();
                                            this.parentFrame.setLBL_CompareInfo_NumberOfFilesInF2(allFolderTwoImages.size());
                                        }
                                    }
                                }
                                catch (IOException ex)
                                {
                                    if (ex.getMessage().equals(cancelCompareMessage))
                                    { throw ex; }
                                    else if (ex.getMessage().equals("-1")) //If an invalid file had been found, remove it from the list.
                                    {
                                        ImageIcon img = new ImageIcon(allFolderOneImages.get((imgInt[0])).file.getAbsolutePath());
                                        if (img.getIconHeight() < 1 || img.getIconWidth() < 1)
                                        {
                                            invalidFileTypesFound = true;
                                            allFolderOneImages.remove(imgInt[0]--);
                                            progressMax = allFolderOneImages.size() * allFolderTwoImages.size();
                                            this.parentFrame.setLBL_CompareInfo_NumberOfFilesInF1(allFolderOneImages.size());
                                            break;
                                        }
                                        img = new ImageIcon(allFolderTwoImages.get((imgInt[1])).file.getAbsolutePath());
                                        if (img.getIconHeight() < 1 || img.getIconWidth() < 1)
                                        {
                                            invalidFileTypesFound = true;
                                            allFolderTwoImages.remove(imgInt[1]--);
                                            progressMax = allFolderOneImages.size() * allFolderTwoImages.size();
                                            this.parentFrame.setLBL_CompareInfo_NumberOfFilesInF2(allFolderTwoImages.size());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    catch (IOException ex)
                    {
                        if (ex.getMessage().equals(cancelCompareMessage))
                        {
                            finalCurrentProgress = progressCurrent;
                            finalMaxProgress = progressMax;
                            throw ex;
                        }
                    }
                }
                System.gc();
            }
            finalCurrentProgress = progressCurrent;
            finalMaxProgress = progressMax;
        }
    }
    
    // === === === FORM FUNCTIONS === === ===
    
    private void displayImages(BufferedImage imageBuff1, BufferedImage imageBuff2) {
        this.parentFrame.getLBL_IMG_Image1().setIcon(new ImageIcon(imageBuff1));
        this.parentFrame.getLBL_IMG_Image2().setIcon(new ImageIcon(imageBuff2));
        
        CompareImages compare = new CompareImages();
        compare.setImage(imageBuff1, CompareImages.FileNum.FIRST);
        compare.setImage(imageBuff2, CompareImages.FileNum.SECOND);
        
        try
        {
            this.parentFrame.getLBL_IMG_AbsoluteDifferences().setIcon(new ImageIcon(compare.getDifferenecs(CompareImages.CompareMethod.BASIC)));
        }
        catch (Exception ex) {
            String errorMSG = "The program was unable to generate the image for the Absolute Differences page.";
            errorMSG += "\nError message: " + ex.getMessage();
            JOptionPane.showMessageDialog(parentFrame, errorMSG, "Problem Generating Image", JOptionPane.ERROR_MESSAGE);
        }
        
        try
        {
            this.parentFrame.getLBL_IMG_SubtractedDifferences().setIcon(new ImageIcon(compare.getDifferenecs(CompareImages.CompareMethod.SUBTRACT_COLOR)));
        }
        catch (Exception ex)
        {
            String errorMSG = "The program was unable to generate the image for the Subtracted Differences page.";
            errorMSG += "\nError message: " + ex.getMessage();
            JOptionPane.showMessageDialog(parentFrame, errorMSG, "Problem Generating Image", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void clearDisplayedImages() {
        BufferedImage blankImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Color blankColor = new Color(240, 240, 240);
        blankImage.setRGB(0, 0, blankColor.getRGB());
        
        this.parentFrame.getLBL_IMG_Image1().setIcon(new ImageIcon(blankImage));
        this.parentFrame.getLBL_IMG_Image2().setIcon(new ImageIcon(blankImage));
        this.parentFrame.getLBL_IMG_AbsoluteDifferences().setIcon(new ImageIcon(blankImage));
        this.parentFrame.getLBL_IMG_SubtractedDifferences().setIcon(new ImageIcon(blankImage));
    }
    private void displayFileInfo(File file1, File file2, float percentSimilar) {
        this.parentFrame.getLBL_CompareInfo_IMGName1().setText("Name: " + file1.getName());
        this.parentFrame.getLBL_CompareInfo_FileType1().setText("File type: " + getFileType(file1));
        this.parentFrame.getLBL_CompareInfo_IMGFileSize1().setText("File size (bytes): " + String.format("%,d", file1.length()));
        this.parentFrame.getLBL_CompareInfo_IMGParentFolder1().setText("Parent folder: " + getParentFolderName(file1));
        this.parentFrame.getLBL_CompareInfo_IMGFilePath1().setText("File path: " + file1.getPath());
        
        this.parentFrame.getLBL_CompareInfo_IMGName1().setToolTipText(file1.getName());
        this.parentFrame.getLBL_CompareInfo_IMGParentFolder1().setToolTipText(getParentFolderName(file1));
        this.parentFrame.getLBL_CompareInfo_IMGFilePath1().setToolTipText(file1.getPath());
                
        this.parentFrame.getLBL_CompareInfo_IMGName2().setText("Name: " + file2.getName());
        this.parentFrame.getLBL_CompareInfo_FileType2().setText("File type: " + getFileType(file2));
        this.parentFrame.getLBL_CompareInfo_IMGFileSize2().setText("File size (bytes): " + String.format("%,d", file2.length()));
        this.parentFrame.getLBL_CompareInfo_IMGParentFolder2().setText("Parent folder: " + getParentFolderName(file2));
        this.parentFrame.getLBL_CompareInfo_IMGFilePath2().setText("File path: " + file2.getPath());
        
        this.parentFrame.getLBL_CompareInfo_IMGName2().setToolTipText(file2.getName());
        this.parentFrame.getLBL_CompareInfo_IMGParentFolder2().setToolTipText(getParentFolderName(file2));
        this.parentFrame.getLBL_CompareInfo_IMGFilePath2().setToolTipText(file2.getPath());
        
        if (percentSimilar >= 0)
        { this.parentFrame.getLBL_Choice_DisplayPercentSimilar().setText("Percent similar: " + Float.toString(percentSimilar * 100).substring(0, 5) + "%"); }
        else { this.parentFrame.getLBL_Choice_DisplayPercentSimilar().setText("Percent similar: 0%"); }
        
        try {
            ImageIcon[] imgIcon = new ImageIcon[2];
            
            imgIcon[0] = new ImageIcon(file1.getAbsolutePath());
            imgIcon[1] = new ImageIcon(file2.getAbsolutePath());
            
            this.parentFrame.getLBL_CompareInfo_ImageSize1().setText("Image size: " + String.format("%,d", imgIcon[0].getIconWidth()) + 
                             " x " + String.format("%,d", imgIcon[0].getIconHeight()));
            
            this.parentFrame.getLBL_CompareInfo_ImageSize2().setText("Image size: " + String.format("%,d", imgIcon[1].getIconWidth()) +
                             " x " + String.format("%,d", imgIcon[1].getIconHeight()));
        } catch (Exception ex) {
            this.parentFrame.getLBL_CompareInfo_IMGFileSize1().setText("Image size: [UNABLE TO READ HEIGHT AND WIDTH]");
            this.parentFrame.getLBL_CompareInfo_IMGFileSize2().setText("Image size: [UNABLE TO READ HEIGHT AND WIDTH]");
        }
    }
    private void clearFileInfo() {
        this.parentFrame.getLBL_CompareInfo_IMGName1().setText("Name: "); //LBL_CompareInfo_IMGName1
        this.parentFrame.getLBL_CompareInfo_FileType1().setText("File type: "); //LBL_CompareInfo_FileType1
        this.parentFrame.getLBL_CompareInfo_ImageSize1().setText("Image size: "); //LBL_CompareInfo_ImageSize1
        this.parentFrame.getLBL_CompareInfo_IMGFileSize1().setText("File size (bytes): "); //LBL_CompareInfo_IMGFileSize1
        this.parentFrame.getLBL_CompareInfo_IMGParentFolder1().setText("Parent folder: "); //LBL_CompareInfo_IMGParentFolder1
        this.parentFrame.getLBL_CompareInfo_IMGFilePath1().setText("File path: "); //LBL_CompareInfo_IMGFilePath1
        
        this.parentFrame.getLBL_CompareInfo_IMGName1().setToolTipText("");
        this.parentFrame.getLBL_CompareInfo_IMGParentFolder1().setToolTipText("");
        this.parentFrame.getLBL_CompareInfo_IMGFilePath1().setToolTipText("");
        
        this.parentFrame.getLBL_CompareInfo_IMGName2().setText("Name: "); //LBL_CompareInfo_IMGName2
        this.parentFrame.getLBL_CompareInfo_FileType2().setText("File type: "); //LBL_CompareInfo_FileType2
        this.parentFrame.getLBL_CompareInfo_ImageSize2().setText("Image size: "); //LBL_CompareInfo_ImageSize2
        this.parentFrame.getLBL_CompareInfo_IMGFileSize2().setText("File size (bytes): "); //LBL_CompareInfo_IMGFileSize2
        this.parentFrame.getLBL_CompareInfo_IMGParentFolder2().setText("Parent folder: "); //LBL_CompareInfo_IMGParentFolder2
        this.parentFrame.getLBL_CompareInfo_IMGFilePath2().setText("File path: "); //LBL_CompareInfo_IMGFilePath2
        
        this.parentFrame.getLBL_CompareInfo_IMGName2().setToolTipText("");
        this.parentFrame.getLBL_CompareInfo_IMGParentFolder2().setToolTipText("");
        this.parentFrame.getLBL_CompareInfo_IMGFilePath2().setToolTipText("");
        
        this.parentFrame.getLBL_Choice_DisplayPercentSimilar().setText("Percent similar: "); //LBL_Choice_DisplayPercentSimilar
    }
    private void enableChoiceButtons(boolean isEnabled) {
        this.parentFrame.getBTN_CompareInfo_Skip().setEnabled(isEnabled);
        this.parentFrame.getBTN_CompareInfo_ChangeImage1().setEnabled(isEnabled);
        this.parentFrame.getBTN_CompareInfo_ChangeImage2().setEnabled(isEnabled);
        //this.parentFrame.getBTN_CompareInfo_Cancel().setEnabled(isEnabled);
    }
    private void setProgress(int currentProgress, int progressMax) {
        this.parentFrame.getJPRGSBR_Choice_TotalProgress().setValue(currentProgress);
        this.parentFrame.getJPRGSBR_Choice_TotalProgress().setMaximum(progressMax);
        
        String progressMessage = String.format("%,d", currentProgress);
        progressMessage += " / ";
        progressMessage += String.format("%,d", progressMax);
        
        this.parentFrame.getJPRGSBR_Choice_TotalProgress().setString(progressMessage);
    }
    private void changeApplicationWindow(boolean isStarting) {
        if (isStarting)
        {
            this.parentFrame.getBTN_CompareInfo_Cancel().setEnabled(true);
            this.parentFrame.getJPRGSBR_Choice_TotalProgress().setStringPainted(true);
        }
        else
        {
            this.parentFrame.setLBL_CompareInfo_NumberOfFilesInF1(-1);
            this.parentFrame.setLBL_CompareInfo_NumberOfFilesInF2(-1);
            this.parentFrame.getJPRGSBR_Choice_TotalProgress().setStringPainted(false);
            this.parentFrame.getJPRGSBR_Choice_TotalProgress().setValue(0);
            this.parentFrame.getBTN_CompareInfo_Cancel().setEnabled(false);
            enableChoiceButtons(false);

            this.parentFrame.getTBDPN_UserInput().setEnabledAt(0, true);
        }
        
    }
    
    // === === === MISCELLANEOUS FUNCTIONS === === ===
    
    private ArrayList<fileAndRatio> getImagesInFolder(File directory, boolean includeSubfolders) {
        ArrayList<fileAndRatio> files = new ArrayList<>();
        CompareImages compare = new CompareImages();
        if (directory.isDirectory())
        {
            for (File newFile : directory.listFiles())
            {
                if (newFile.isDirectory() && includeSubfolders)
                {
                    ArrayList<fileAndRatio> subFolder = getImagesInFolder(newFile, true);
                    for (int x = 0; x < subFolder.size(); x++)
                    {
                        File subFile = subFolder.get(x).file;
                        if (checkImageValidity(subFile))
                        {
                            try
                            {
                                fileAndRatio newImage = new fileAndRatio();
                                newImage.file = subFile;
                                newImage.hwRatio = compare.getHeightWidthRatioFromFile(subFile);
                                files.add(newImage);
                            }
                            catch (Exception ex) { System.out.println("File was not added to the list: " + subFile.getAbsolutePath()); }
                        }
                    }
                }
                else if (!newFile.isDirectory() && checkImageValidity(newFile))
                {
                    try
                    {
                        fileAndRatio newImage = new fileAndRatio();
                        newImage.file = newFile;
                        newImage.hwRatio = compare.getHeightWidthRatioFromFile(newFile);
                        files.add(newImage);
                    }
                    catch (Exception ex) { System.out.println("File was not added to the list: " + newFile.getAbsolutePath()); }
                }
            }
        }
        return files;
    }
    private String getParentFolderName(File tempFile) {
        File parentFolder = tempFile.getParentFile();
        return parentFolder.getName();
    }
    private String getFileType(File file) {
        String fileType = "";
        try
        {
            fileType = Files.probeContentType(file.toPath());
            fileType = fileType.substring(fileType.lastIndexOf('/') + 1);
            fileType = fileType.toUpperCase();
        }
        catch (Exception ex)
        {
            String tempType = file.getName().substring(file.getName().lastIndexOf('.'));
            switch (tempType)
            {
                case ".png":
                    fileType = "PNG";
                    break;
                case ".jpg":
                case ".jpeg":
                    fileType = "JPEG";
                    break;
                default:
                    fileType = tempType;
            }
        }
        return fileType;
    }
    private long askUserForStartNum(long maxNum) {
        long startTime = System.currentTimeMillis();
        while (true)
        {
            String message = "There are " + String.format("%,d", maxNum) + " potential combinations to go through, which may take a while.";
            message += "\nWould you like to skip some and start at a specific point?";
            int result = JOptionPane.showConfirmDialog(this.parentFrame, message, "Skip Comparisons", JOptionPane.YES_NO_OPTION);
            
            if (result == JOptionPane.YES_OPTION)
            {

                try
                {
                    message = "What number would you like to start at?\nYou can choose any number between 1 and " + String.format("%,d", maxNum) + ".";
                    String userInput = JOptionPane.showInputDialog(this.parentFrame, message);
                    userInput = userInput.replaceAll(",", "");

                    long userNum = Long.parseUnsignedLong(userInput);
                    if (userNum > maxNum || userNum < 1)
                    {
                        String errorMSG = "You entered a number that was either less than one or greater than the maximum number of comparisons (" + String.format("%,d", maxNum) + ").";
                        errorMSG += "\nThe text you entered was:\n\n" + userInput;
                        errorMSG += "\n\nWould you like to try again?";
                        if (JOptionPane.showConfirmDialog(parentFrame, errorMSG, "Invalid Input", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                        {
                            timeWaitingForStartNum = System.currentTimeMillis() - startTime;
                            return 0;
                        }
                    }
                    else
                    {
                        timeWaitingForStartNum = System.currentTimeMillis() - startTime;
                        return userNum;
                    }
                }
                catch (NumberFormatException ex)
                {
                    String errorMSG = "You entered something that was not a number. Would you like to try again?";
                    if (JOptionPane.showConfirmDialog(parentFrame, errorMSG, "Invalid Input", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                    {
                        timeWaitingForStartNum = System.currentTimeMillis() - startTime;
                        return 0;
                    }
                }
                catch (Exception ex) { }
            }
            else
            {
                timeWaitingForStartNum = System.currentTimeMillis() - startTime;
                return 0;
            }
        }
    }
    private String getTimeString(long totalSeconds) {
        //totalSeconds = 3600 + 60 + 1;
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;
        
        String returnString = String.format("%,d", seconds) + " seconds";
        if (totalSeconds >= 3600)
        {
            returnString = String.format("%,d", hours) + " hours, " + String.format("%,d", minutes) + " minutes, and " + returnString;
        }
        else if (totalSeconds >= 60)
        {
            returnString = String.format("%,d", minutes) + " minutes and " + returnString;
        }
        return returnString;
    }
    private int getMaxProgressOneFolder(int imageCount) {
        return (imageCount * (imageCount - 1)) / 2;
    }
    private ArrayList<fileAndRatio> sortFileList(ArrayList<fileAndRatio> list) {
        for (int x = 0; x < list.size() - 1; x++)
        {
            for (int y = x + 1; y < list.size(); y++)
            {
                if (list.get(x).hwRatio > list.get(y).hwRatio)
                {
                    fileAndRatio temp = list.get(x);
                    list.set(x, list.get(y));
                    list.set(y, temp);
                }
            }
        }
        return list;
    }
    
    @Override
    public void run() {
        if (this.parentFrame != null)
        {
            ActionListener skip = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    waitingForUser = false;
                }
            };
            ActionListener delete1 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteImageOneButtonClicked();
                }
            };
            ActionListener delete2 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteImageTwoButtonClicked();
                }
            };
            ActionListener cancel = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stopThread = true;
                    waitingForUser = false;
                }
            };
            
            this.parentFrame.getBTN_CompareInfo_Skip().addActionListener(skip);
            this.parentFrame.getBTN_CompareInfo_ChangeImage1().addActionListener(delete1);
            this.parentFrame.getBTN_CompareInfo_ChangeImage2().addActionListener(delete2);
            this.parentFrame.getBTN_CompareInfo_Cancel().addActionListener(cancel);
            
            changeApplicationWindow(true);
            
            long startTime = System.currentTimeMillis();
            
            try
            {
                switch (selectedSearchMethod)
                {
                    case TWO_IMAGES:
                        if (this.targetFolder[0] != null && this.targetFolder[1] != null)
                        { checkTwoImages(this.targetFolder[0], this.targetFolder[1]); }
                        break;
                    case ONE_FOLDER:
                        if (this.targetFolder[0] != null)
                        { checkOneFolder(this.targetFolder[0]); }
                        break;
                    case TWO_FOLDERS:
                        if (this.targetFolder[0] != null && this.targetFolder[1] != null)
                        { checkTwoFolders(this.targetFolder[0], this.targetFolder[1]); }
                }
            }
            catch (Exception ex)
            {
                String errorMSG = "Something went wrong and the process had to be canceled. Please send the following information to the";
                errorMSG += "\nauthor of this program so that they can fix the issue. Contact information can be found in the README file.";
                errorMSG += "\n\n";
                
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                ex.printStackTrace(printWriter);
                errorMSG += stringWriter.toString();
                
                JOptionPane.showMessageDialog(parentFrame, errorMSG, "An Error Occurred", JOptionPane.ERROR_MESSAGE);
            }
            
            this.parentFrame.getBTN_CompareInfo_Skip().removeActionListener(skip);
            this.parentFrame.getBTN_CompareInfo_ChangeImage1().removeActionListener(delete1);
            this.parentFrame.getBTN_CompareInfo_ChangeImage2().removeActionListener(delete2);
            this.parentFrame.getBTN_CompareInfo_Cancel().removeActionListener(cancel);
            
            changeApplicationWindow(false);
            
            System.gc(); //Call the garbage collector
            
            if (this.parentFrame.getCHKBX_Settings_ShowCompareDetails().isSelected())
            {
                long timeSpentComparing = System.currentTimeMillis() - startTime - timeWaitingForUser - timeWaitingForStartNum;
                timeSpentComparing /= 1000;
                timeWaitingForUser /= 1000;

                String searchType = "";
                switch (selectedSearchMethod)
                {
                    case TWO_IMAGES:
                        searchType = "Two images";
                        break;
                    case ONE_FOLDER:
                        searchType = "Single folder";
                        break;
                    case TWO_FOLDERS:
                        searchType = "Two folders";
                }

                String message = "\nComparison type: " + searchType;
                message += "\n";
                message += "\nTime spent comparing: " + getTimeString(timeSpentComparing);
                message += "\nTime spent waiting for user: " + getTimeString(timeWaitingForUser);
                message += "\n";
                message += "\nNumber of files deleted: " + String.format("%,d", numFilesDeleted);
                message += "\nTotal bytes freed: " + String.format("%,d", totalBytesRemoved);
                if (finalCurrentProgress < finalMaxProgress)
                {
                    message += "\n";
                    message += "\nEnding progress: " + String.format("%,d", finalCurrentProgress) + " / " + String.format("%,d", finalMaxProgress);
                    message += "\nPercent complete: " + String.format("%.2f", ((double) finalCurrentProgress / (double) finalMaxProgress) * 100) + "%";
                }
                JOptionPane.showMessageDialog(this.parentFrame, message, "General Information", JOptionPane.INFORMATION_MESSAGE);
            }
            
            if (invalidFileTypesFound)
            {
                String invalidTypeMessage = 
                        "The program found one or more invalid images. These files may be invalid because their file extensions" +
                        "\nwere renamed in an attempt to get the program to read them. If this is the case, please instead convert" +
                        "\nthese using an image converter. You can see which image types are valid in the README file.";
                JOptionPane.showMessageDialog(parentFrame, invalidTypeMessage, "Invalid Image(s)", JOptionPane.ERROR_MESSAGE);
            }
        }
        else { System.out.println("Parent frame is null!"); }
    }
}