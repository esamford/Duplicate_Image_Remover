package duplicate_image_remover;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class CompareProcess implements Runnable
{
    String cancelCompareMessage = "Thread has been canceled manually.";
    volatile boolean stopThread = false;
    
    public enum SearchType {
        TWO_IMAGES,
        ONE_FOLDER,
        TWO_FOLDERS
    }
    SearchType selectedSearchMethod;
    File[] targetFile = new File[2];
    
    DIR_Window parentFrame;
    volatile boolean waitingForUser;
    
    long timeWaitingForUser = 0;
    int numFilesDeleted = 0;
    
    
    // === === === SETTERS === === ===
    
    public void setParent(DIR_Window newParent) {
        this.parentFrame = newParent;
    }
    public void setSearchType(SearchType newSearchType) { this.selectedSearchMethod = newSearchType; }
    public void setTargetFolder1(File newTargetFolder) {
        if (newTargetFolder.isDirectory()) { this.targetFile[0] = newTargetFolder; }
    }
    public void setTargetFolder2(File newTargetFolder) {
        if (newTargetFolder.isDirectory()) { this.targetFile[1] = newTargetFolder; }
    }
    public void setTargetFiles(File newFile1, File newFile2) {
        if (!newFile1.isDirectory()) { this.targetFile[0] = newFile1; }
        if (!newFile2.isDirectory()) { this.targetFile[1] = newFile2; }
    }
    
    // === === === VALIDATION FUNCTIONS === === ===
    
    public void checkForCompareTwoImages() throws IOException {
        if (this.targetFile[0] == null || this.targetFile[1] == null)
        { throw new IOException("The class must have both image files before they can be compared."); }
        
        if (this.targetFile[0].isDirectory() || this.targetFile[1].isDirectory())
        { throw new IOException("Neither of the image files can be a directory. They must both be non-directory files."); }
        
        if (!checkImageValidity(this.targetFile[0]))
        { throw new IOException("Image file one is not a valid image file: " + this.targetFile[0].getName()); }
        
        if (!checkImageValidity(this.targetFile[1]))
        { throw new IOException("Image file two is not a valid image file: " + this.targetFile[1].getName()); }
    }
    public void checkForCompareOneFolder() throws IOException {
        if (!this.targetFile[0].isDirectory()) { throw new IOException("The target file provided was not a directory."); }
    }
    public void checkForCompareTwoFolders() throws IOException {
        if (!this.targetFile[0].isDirectory() || !this.targetFile[1].isDirectory())
        { throw new IOException("One or more of the target files provided were not directories."); }
    }
    
    // === === === ACTION LISTENER FUNCTIONS === === ===
    
    private void deleteFile(File deleteFile) {
        try {
            int result = JOptionPane.showConfirmDialog(this.parentFrame, "Warning: This will permenantly delete the image and you will not be able to restore it from the recycle bin.\n\nDo you wish to continue?");
            if (result == JOptionPane.YES_OPTION)
            {
                if (deleteFile.delete())
                {
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
        if (this.targetFile[0].exists())
        {
            deleteFile(this.targetFile[0]);
        }
    }
    private void deleteImageTwoButtonClicked() {
        if (this.targetFile[1].exists())
        {
            deleteFile(this.targetFile[1]);
        }
    }
    
    // === === === COMPARISON FUNCTIONS === === ===
    
    private void processUserDecision(BufferedImage imageBuffer1, BufferedImage imageBuffer2, float percentSimilar) throws IOException {
        displayImages(imageBuffer1, imageBuffer2);
        displayFileInfo(this.targetFile[0], this.targetFile[1], percentSimilar);
        
        System.gc();
        enableChoiceButtons(true);
        
        if (this.parentFrame.getCHKBX_Settings_SoundNotifications().isSelected())
        { Toolkit.getDefaultToolkit().beep(); }
        waitingForUser = true;
        int timeWaited = 0, sleepTime = 500;
        long startTime = System.currentTimeMillis();
        try {
            while (waitingForUser) {
                Thread.sleep(sleepTime);
                if (this.parentFrame.getCHKBX_Settings_RepeatNotificationSound().isSelected() && timeWaited * sleepTime >= 30000)
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
        return validate.isValidExtension(checkFile);
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

                processUserDecision(imgBuff[0], imgBuff[1], percentSimilar);
                System.out.println("\tFile 1 name: " + file1.getName());
                System.out.println("\tFile 2 name: " + file2.getName()); 
            }
            else { JOptionPane.showMessageDialog(this.parentFrame, "You cannot compare two images with different height/width ratios.", "Unequal Size Ratios", JOptionPane.ERROR_MESSAGE); }
        } 
        catch (Exception ex)
        {
            System.out.println("Error when comparing two single images: " + ex.getMessage());
            throw ex;
        }
    }
    private void checkOneFolder(File folder) throws IOException {
        checkForCompareOneFolder();
        
        int numberOfFiles = countFilesInFolder(folder);
        this.parentFrame.getLBL_CompareInfo_NumberOfFiles().setText("Number of files: " + makeNumeric(numberOfFiles));
        
        if (folder.isDirectory())
        {
            int[] imgInt = new int[2];
            CompareImages.CompareMethod compareMethod = CompareImages.CompareMethod.SUBTRACT_COLOR;
            CompareImages compare = new CompareImages();
            
            ArrayList<File> allImageFiles = getImagesInFolder(folder, this.parentFrame.getIncludeSubfolders());
            
            int progressMax = (allImageFiles.size() - 1) * allImageFiles.size();
            int progressCurrent;
            
            this.parentFrame.getJPRGSBR_Choice_TotalProgress().setMinimum(0);
            this.parentFrame.getJPRGSBR_Choice_TotalProgress().setMaximum(progressMax);
            this.parentFrame.getLBL_CompareInfo_ProgressCurrent().setText("0");
            this.parentFrame.getLBL_CompareInfo_ProgressSplit().setText("/");
            this.parentFrame.getLBL_CompareInfo_ProgressMax().setText(makeNumeric(progressMax));
            
            int[] startNum = {0, 0};
            if (progressMax >= 1000)
            {
                long userNum = askUserForStartNum(progressMax);
                startNum[0] = (int) userNum / (allImageFiles.size() - 1);
                startNum[1] = (int) userNum % (allImageFiles.size() - 1);
            }
            
            for (imgInt[0] = startNum[0]; imgInt[0] < allImageFiles.size() - 1; imgInt[0]++)
            {
                if (stopThread) { break; }
                
                this.targetFile[0] = allImageFiles.get((imgInt[0]));
                if (!this.targetFile[0].exists())
                {
                    allImageFiles.remove(imgInt[0]);
                    imgInt[0]--;
                    progressMax = (allImageFiles.size() - 1) * allImageFiles.size();
                    this.parentFrame.getJPRGSBR_Choice_TotalProgress().setMaximum(progressMax);
                    this.parentFrame.getLBL_CompareInfo_ProgressMax().setText(makeNumeric(progressMax));
                    this.parentFrame.getLBL_CompareInfo_NumberOfFiles().setText("Number of files: " + makeNumeric(--numberOfFiles));
                }
                else
                {
                    try {
                        compare.setImage(this.targetFile[0], CompareImages.FileNum.FIRST);

                        for (imgInt[1] = imgInt[0] + 1; imgInt[1] < allImageFiles.size(); imgInt[1]++)
                        {
                            if (stopThread) { break; }
                            
                            if (startNum[1] > 0)
                            {
                                imgInt[1] = startNum[1];
                                startNum[1] = -1;
                            }
                            
                            this.targetFile[1] = allImageFiles.get((imgInt[1]));
                            if (!this.targetFile[1].exists())
                            {
                                allImageFiles.remove(imgInt[1]);
                                imgInt[1]--;
                                progressMax = (allImageFiles.size() - 1) * allImageFiles.size();
                                this.parentFrame.getJPRGSBR_Choice_TotalProgress().setMaximum(progressMax);
                                this.parentFrame.getLBL_CompareInfo_ProgressMax().setText(makeNumeric(progressMax));
                                this.parentFrame.getLBL_CompareInfo_NumberOfFiles().setText("Number of files: " + makeNumeric(--numberOfFiles));
                            }
                            else
                            {
                                try {
                                    progressCurrent = imgInt[0] * (allImageFiles.size() - 1) + imgInt[1];
                                    this.parentFrame.getJPRGSBR_Choice_TotalProgress().setValue(progressCurrent);
                                    this.parentFrame.getLBL_CompareInfo_ProgressCurrent().setText(makeNumeric(progressCurrent++));

                                    compare.setImage(this.targetFile[1], CompareImages.FileNum.SECOND);
                                    
                                    System.out.println();
                                    System.out.println();
                                    System.out.println("Image one name: " + this.targetFile[0].getName());
                                    System.out.println("Image two name: " + this.targetFile[1].getName());
                                    
                                    long startTime = System.nanoTime();
                                    float percentSimilar = compare.getPercentSimilar(compareMethod, this.parentFrame.getSLDR_MinimumSimilarityThreshold());
                                    long endTime = System.nanoTime();
                                    System.out.println("Compared images. Time taken (in nanoseconds): " + String.format("%,d", endTime-startTime));
                                    
                                    if (percentSimilar >= 0 && percentSimilar * 100 >= this.parentFrame.getSLDR_MinimumSimilarityThreshold().getValue())
                                    {
                                        processUserDecision(compare.getImage(CompareImages.FileNum.FIRST),
                                                            compare.getImage(CompareImages.FileNum.SECOND),
                                                            percentSimilar);
                                        
                                        if (!this.targetFile[0].exists())
                                        {
                                            allImageFiles.remove(imgInt[0]);
                                            imgInt[0]--;
                                            progressMax = (allImageFiles.size() - 1) * allImageFiles.size();
                                            this.parentFrame.getJPRGSBR_Choice_TotalProgress().setMaximum(progressMax);
                                            this.parentFrame.getLBL_CompareInfo_ProgressMax().setText(makeNumeric(progressMax));
                                            this.parentFrame.getLBL_CompareInfo_NumberOfFiles().setText("Number of files: " + makeNumeric(--numberOfFiles));
                                            break;
                                        }
                                        else if (!this.targetFile[1].exists())
                                        {
                                            allImageFiles.remove(imgInt[1]);
                                            imgInt[1]--;
                                            progressMax = (allImageFiles.size() - 1) * allImageFiles.size();
                                            this.parentFrame.getJPRGSBR_Choice_TotalProgress().setMaximum(progressMax);
                                            this.parentFrame.getLBL_CompareInfo_ProgressMax().setText(makeNumeric(progressMax));
                                            this.parentFrame.getLBL_CompareInfo_NumberOfFiles().setText("Number of files: " + makeNumeric(--numberOfFiles));
                                        }
                                    }
                                }
                                catch (IOException ex)
                                { if (ex.getMessage().equals(cancelCompareMessage)) { throw ex; } }
                            }
                        }
                    }
                    catch (IOException ex)
                    { if (ex.getMessage().equals(cancelCompareMessage)) { throw ex; } }
                }
                System.gc();
            }
        }
    }
    private void checkTwoFolders(File folderOne, File folderTwo) throws IOException {
        checkForCompareTwoFolders();
        
        int numberOfFiles = countFilesInFolder(folderOne);
        numberOfFiles += countFilesInFolder(folderTwo);
        this.parentFrame.getLBL_CompareInfo_NumberOfFiles().setText("Number of files: " + makeNumeric(numberOfFiles));
        
        if (folderOne.isDirectory() && folderTwo.isDirectory())
        {
            int[] imgInt = new int[2];
            CompareImages.CompareMethod compareMethod = CompareImages.CompareMethod.SUBTRACT_COLOR;
            CompareImages compare = new CompareImages();
            
            ArrayList<File> allFolderOneImages = getImagesInFolder(folderOne, this.parentFrame.getIncludeSubfolders());
            ArrayList<File> allFolderTwoImages = getImagesInFolder(folderTwo, this.parentFrame.getIncludeSubfolders());
            
            int progressMax = allFolderOneImages.size() * allFolderTwoImages.size();
            int progressCurrent;
            this.parentFrame.getJPRGSBR_Choice_TotalProgress().setMinimum(0);
            this.parentFrame.getJPRGSBR_Choice_TotalProgress().setMaximum(progressMax);
            this.parentFrame.getLBL_CompareInfo_ProgressCurrent().setText("0");
            this.parentFrame.getLBL_CompareInfo_ProgressSplit().setText("/");
            this.parentFrame.getLBL_CompareInfo_ProgressMax().setText(makeNumeric(progressMax));
            
            int[] startNum = {0, 0};
            if (progressMax >= 1000)
            {
                long userNum = askUserForStartNum(progressMax);
                startNum[0] = (int) userNum / allFolderTwoImages.size();
                startNum[1] = (int) userNum % allFolderTwoImages.size();
            }
            
            for (imgInt[0] = startNum[0]; imgInt[0] < allFolderOneImages.size(); imgInt[0]++)
            {
                if (stopThread) { break; }
                
                this.targetFile[0] = allFolderOneImages.get((imgInt[0]));
                if (!this.targetFile[0].exists())
                {
                    allFolderOneImages.remove(imgInt[0]);
                    imgInt[0]--;
                    progressMax = allFolderOneImages.size() * allFolderTwoImages.size();
                    this.parentFrame.getJPRGSBR_Choice_TotalProgress().setMaximum(progressMax);
                    this.parentFrame.getLBL_CompareInfo_ProgressMax().setText(makeNumeric(progressMax));
                    this.parentFrame.getLBL_CompareInfo_NumberOfFiles().setText("Number of files: " + makeNumeric(--numberOfFiles));
                }
                else
                {
                    try {
                        compare.setImage(this.targetFile[0], CompareImages.FileNum.FIRST);

                        for (imgInt[1] = 0; imgInt[1] < allFolderTwoImages.size(); imgInt[1]++)
                        {
                            if (stopThread) { break; }
                            
                            if (startNum[1] > 0)
                            {
                                imgInt[1] = startNum[1];
                                startNum[1] = -1;
                            }
                            
                            this.targetFile[1] = allFolderTwoImages.get((imgInt[1]));
                            if (!this.targetFile[1].exists())
                            {
                                allFolderTwoImages.remove(imgInt[1]);
                                imgInt[1]--;
                                progressMax = allFolderOneImages.size() * allFolderTwoImages.size();
                                this.parentFrame.getJPRGSBR_Choice_TotalProgress().setMaximum(progressMax);
                                this.parentFrame.getLBL_CompareInfo_ProgressMax().setText(makeNumeric(progressMax));
                                this.parentFrame.getLBL_CompareInfo_NumberOfFiles().setText("Number of files: " + makeNumeric(--numberOfFiles));
                            }
                            else
                            {
                                try {
                                    progressCurrent = allFolderTwoImages.size() * imgInt[0] + imgInt[1];
                                    this.parentFrame.getJPRGSBR_Choice_TotalProgress().setValue(progressCurrent);
                                    this.parentFrame.getLBL_CompareInfo_ProgressCurrent().setText(makeNumeric(progressCurrent++));
                                    
                                    if (!this.targetFile[0].equals(this.targetFile[1])) //Make sure not to compare an image with itself.
                                    {
                                        compare.setImage(this.targetFile[1], CompareImages.FileNum.SECOND);
                                        
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("Image one name: " + this.targetFile[0].getName());
                                        System.out.println("Image two name: " + this.targetFile[1].getName());
                                        
                                        float percentSimilar = compare.getPercentSimilar(compareMethod, this.parentFrame.getSLDR_MinimumSimilarityThreshold());

                                        if (percentSimilar >= 0 && percentSimilar * 100 >= this.parentFrame.getSLDR_MinimumSimilarityThreshold().getValue())
                                        {
                                            processUserDecision(compare.getImage(CompareImages.FileNum.FIRST),
                                                                compare.getImage(CompareImages.FileNum.SECOND),
                                                                percentSimilar);

                                            if (!this.targetFile[0].exists())
                                            {
                                                allFolderOneImages.remove(imgInt[0]);
                                                imgInt[0]--;
                                                progressMax = allFolderOneImages.size() * allFolderTwoImages.size();
                                                this.parentFrame.getJPRGSBR_Choice_TotalProgress().setMaximum(progressMax);
                                                this.parentFrame.getLBL_CompareInfo_ProgressMax().setText(makeNumeric(progressMax));
                                                this.parentFrame.getLBL_CompareInfo_NumberOfFiles().setText("Number of files: " + makeNumeric(--numberOfFiles));
                                                break;
                                            }
                                            else if (!this.targetFile[1].exists())
                                            {
                                                allFolderTwoImages.remove(imgInt[1]);
                                                imgInt[1]--;
                                                progressMax = allFolderOneImages.size() * allFolderTwoImages.size();
                                                this.parentFrame.getJPRGSBR_Choice_TotalProgress().setMaximum(progressMax);
                                                this.parentFrame.getLBL_CompareInfo_ProgressMax().setText(makeNumeric(progressMax));
                                                this.parentFrame.getLBL_CompareInfo_NumberOfFiles().setText("Number of files: " + makeNumeric(--numberOfFiles));
                                            }
                                        }
                                    }
                                }
                                catch (IOException ex)
                                { if (ex.getMessage().equals(cancelCompareMessage)) { throw ex; } }
                            }
                        }
                    }
                    catch (IOException ex)
                    { if (ex.getMessage().equals(cancelCompareMessage)) { throw ex; } }
                }
                System.gc();
            }
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
            this.parentFrame.getLBL_IMG_HighlightedDifferences().setIcon(new ImageIcon(compare.getDifferenecs(CompareImages.CompareMethod.BASIC)));
            this.parentFrame.getLBL_IMG_SubtractedDifferences().setIcon(new ImageIcon(compare.getDifferenecs(CompareImages.CompareMethod.SUBTRACT_COLOR)));
        }
        catch (Exception ex) {
            System.out.println("\tSOMETHING WENT WRONG WHILE TRYING TO DISPLAY THE IMAGES AND AN EXCEPTION WAS THROWN.");
            System.out.println("\t\t" + ex.getMessage());
        }
    }
    private void clearDisplayedImages() {
        BufferedImage blankImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Color blankColor = new Color(240, 240, 240);
        blankImage.setRGB(0, 0, blankColor.getRGB());
        
        this.parentFrame.getLBL_IMG_Image1().setIcon(new ImageIcon(blankImage));
        this.parentFrame.getLBL_IMG_Image2().setIcon(new ImageIcon(blankImage));
        this.parentFrame.getLBL_IMG_HighlightedDifferences().setIcon(new ImageIcon(blankImage));
        this.parentFrame.getLBL_IMG_SubtractedDifferences().setIcon(new ImageIcon(blankImage));
    }
    private void displayFileInfo(File file1, File file2, float percentSimilar) {
        this.parentFrame.getLBL_CompareInfo_IMGName1().setText("Name: " + file1.getName());
        this.parentFrame.getLBL_CompareInfo_FileType1().setText("File type: " + getFileExt(file1));
        this.parentFrame.getLBL_CompareInfo_IMGFileSize1().setText("File size (bytes): " + makeNumeric((int) file1.length()));
        this.parentFrame.getLBL_CompareInfo_IMGParentFolder1().setText("Parent folder: " + getParentFolderName(file1));
        this.parentFrame.getLBL_CompareInfo_IMGFilePath1().setText("File path: " + file1.getPath());
        
        this.parentFrame.getLBL_CompareInfo_IMGName1().setToolTipText(file1.getName());
        this.parentFrame.getLBL_CompareInfo_IMGParentFolder1().setToolTipText(getParentFolderName(file1));
        this.parentFrame.getLBL_CompareInfo_IMGFilePath1().setToolTipText(file1.getPath());
                
        this.parentFrame.getLBL_CompareInfo_IMGName2().setText("Name: " + file2.getName());
        this.parentFrame.getLBL_CompareInfo_FileType2().setText("File type: " + getFileExt(file2));
        this.parentFrame.getLBL_CompareInfo_IMGFileSize2().setText("File size (bytes): " + makeNumeric((int) file2.length()));
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
            
            this.parentFrame.getLBL_CompareInfo_ImageSize1().setText("Image size: " + makeNumeric(imgIcon[0].getIconWidth()) + 
                             " x " + makeNumeric(imgIcon[0].getIconHeight()));
            
            this.parentFrame.getLBL_CompareInfo_ImageSize2().setText("Image size: " + makeNumeric(imgIcon[1].getIconWidth()) +
                             " x " + makeNumeric(imgIcon[1].getIconHeight()));
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
    
    // === === === OTHER FUNCTIONS === === ===
    
    private ArrayList<File> getImagesInFolder(File directory, boolean includeSubfolders) {
        System.out.println("Entered the 'getImagesInFolder' function.");
        ArrayList<File> files = new ArrayList<File>();
        if (directory.isDirectory())
        {
            for (File newFile : directory.listFiles())
            {
                if (newFile.isDirectory() && includeSubfolders)
                {
                    ArrayList<File> subFolder = getImagesInFolder(newFile, true);
                    for (File subFile : subFolder)
                    {
                        if (checkImageValidity(subFile)) { files.add(subFile); }
                    }
                }
                else if (!newFile.isDirectory() && checkImageValidity(newFile))
                {
                    files.add(newFile);
                }
            }
        }
        else {
            System.out.println("File is not a directory.");
        }
        return files;
    }
    private String makeNumeric(int number) {
        return String.format("%,d", number);
    }
    private String getParentFolderName(File tempFile) {
        File parentFolder = tempFile.getParentFile();
        return parentFolder.getName();
    }
    private String getFileExt(File tempFile) {
        return tempFile.getName().substring(tempFile.getName().lastIndexOf("."));
    }
    private int countFilesInFolder(File directory) {
        if (!directory.isDirectory()) { return 0; }
        
        int counter = 0;
        for (File newFile : directory.listFiles())
        {
            if (newFile.isDirectory() && this.parentFrame.getIncludeSubfolders())
            { counter += countFilesInFolder(newFile); }
            else if (!newFile.isDirectory() && checkImageValidity(newFile))
            { counter++; }
        }
        return counter;
    }
    private long askUserForStartNum(long maxNum) {
        String message = "There are " + String.format("%,d", maxNum) + " potential combinations to go through, which may take a while.";
        message += "\nWould you like to skip some and start at a specific point?";
        int result = JOptionPane.showConfirmDialog(this.parentFrame, message, "Skip Comparisons", JOptionPane.YES_NO_OPTION);
        
        long startTime = System.currentTimeMillis();
        while (true)
        {
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
                            timeWaitingForUser += System.currentTimeMillis() - startTime;
                            return 0;
                        }
                    }
                    else
                    {
                        timeWaitingForUser += System.currentTimeMillis() - startTime;
                        return userNum;
                    }
                }
                catch (NumberFormatException ex)
                {
                    String errorMSG = "You entered something that was not a number. Would you like to try again?";
                    if (JOptionPane.showConfirmDialog(parentFrame, errorMSG, "Invalid Input", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                    {
                        timeWaitingForUser += System.currentTimeMillis() - startTime;
                        return 0;
                    }
                }
                catch (Exception ex) { }
            }
            else
            {
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
    
    @Override
    public void run() {
        
        
        if (this.parentFrame != null)
        {
            ActionListener skip = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("The skip button was pressed.");
                    waitingForUser = false;
                }
            };
            ActionListener delete1 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("The change file 1 button was clicked.");
                    deleteImageOneButtonClicked();
                }
            };
            ActionListener delete2 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("The change file 2 button was clicked.");
                    deleteImageTwoButtonClicked();
                }
            };
            ActionListener cancel = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("The cancel button was pressed.");
                    stopThread = true;
                    waitingForUser = false;
                }
            };
            
            this.parentFrame.getBTN_CompareInfo_Skip().addActionListener(skip);
            this.parentFrame.getBTN_CompareInfo_ChangeImage1().addActionListener(delete1);
            this.parentFrame.getBTN_CompareInfo_ChangeImage2().addActionListener(delete2);
            this.parentFrame.getBTN_CompareInfo_Cancel().addActionListener(cancel);
            
            this.parentFrame.getBTN_CompareInfo_Cancel().setEnabled(true);
            
            long startTime = System.currentTimeMillis();
            
            try {
                switch (selectedSearchMethod)
                {
                    case TWO_IMAGES:
                        if (this.targetFile[0] != null && this.targetFile[1] != null)
                        { checkTwoImages(this.targetFile[0], this.targetFile[1]); }
                        break;
                    case ONE_FOLDER:
                        if (this.targetFile[0] != null)
                        { checkOneFolder(this.targetFile[0]); }
                        break;
                    case TWO_FOLDERS:
                        if (this.targetFile[0] != null && this.targetFile[1] != null)
                        { checkTwoFolders(this.targetFile[0], this.targetFile[1]); }
                }
            }
            catch (Exception ex) {
                System.out.println("Something went wrong and the comparison had to be canceled.");
                System.out.println("Error type: " + ex.getClass().toString());
                System.out.println("Message: " + ex.getMessage());
            }
            
            this.parentFrame.getBTN_CompareInfo_Skip().removeActionListener(skip);
            this.parentFrame.getBTN_CompareInfo_ChangeImage1().removeActionListener(delete1);
            this.parentFrame.getBTN_CompareInfo_ChangeImage2().removeActionListener(delete2);
            this.parentFrame.getBTN_CompareInfo_Cancel().removeActionListener(cancel);
            
            this.parentFrame.getLBL_CompareInfo_ProgressCurrent().setText(" ");
            this.parentFrame.getLBL_CompareInfo_ProgressMax().setText(" ");
            this.parentFrame.getLBL_CompareInfo_ProgressSplit().setText(" ");
            this.parentFrame.getJPRGSBR_Choice_TotalProgress().setValue(0);
            this.parentFrame.getBTN_CompareInfo_Cancel().setEnabled(false);
            enableChoiceButtons(false);
            
            this.parentFrame.getTBDPN_UserInput().setEnabledAt(0, true);
            
            System.gc(); //Call the garbage collector
            
            if (this.parentFrame.getCHKBX_Settings_ShowCompareDetails().isSelected())
            {
                long timeSpentComparing = System.currentTimeMillis() - startTime - timeWaitingForUser;
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

                String message = "Here are the details about the last comparison:";
                message += "\n\nComparison type: " + searchType;
                message += "\nTime spent comparing: " + getTimeString(timeSpentComparing);
                message += "\nTime spent waiting for user: " + getTimeString(timeWaitingForUser);
                message += "\nNumber of files deleted: " + String.format("%,d", numFilesDeleted);

                JOptionPane.showMessageDialog(this.parentFrame, message, "Compare Details", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else { System.out.println("Parent frame is null!"); }
        
        
        
        
        System.out.println("FINISHED WITH THE COMPAREPROCESS THREAD.");
    }
}