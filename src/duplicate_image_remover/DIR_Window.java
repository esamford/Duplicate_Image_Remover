package duplicate_image_remover;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class DIR_Window extends javax.swing.JFrame {
    File[] targetFolder = new File[2];
    File[] imgFile = new File[2];
    Thread labelUpdate1 = new Thread(new UpdateLBLImageCount());
    Thread labelUpdate2 = new Thread(new UpdateLBLImageCount());
    
    // === === === GETTERS === === ===
    
    public File[] getTargetFolders() { return this.targetFolder; }
    public File[] getImgFiles() { return this.imgFile; }
    
    public javax.swing.JButton getBTN_CompareInfo_Cancel() {
        return this.BTN_CompareInfo_Cancel;
    }
    public javax.swing.JButton getBTN_CompareInfo_ChangeImage1() {
        return this.BTN_CompareInfo_ChangeImage1;
    }
    public javax.swing.JButton getBTN_CompareInfo_ChangeImage2() {
        return this.BTN_CompareInfo_ChangeImage2;
    }
    public javax.swing.JButton getBTN_CompareInfo_Skip() {
        return this.BTN_CompareInfo_Skip;
    }
    public javax.swing.JCheckBox getCHKBX_SIaC_IncludeSubfoldersInFolder1() {
        return this.CHKBX_SIaC_IncludeSubfoldersInFolder1;
    }
    public javax.swing.JCheckBox getCHKBX_SIaC_IncludeSubfoldersInFolder2() {
        return this.CHKBX_SIaC_IncludeSubfoldersInFolder2;
    }
    public javax.swing.JCheckBox getCHKBX_Settings_RepeatNotificationSound() {
        return this.CHKBX_Settings_RepeatNotificationSound;
    }
    public javax.swing.JCheckBox getCHKBX_Settings_ShowCompareDetails() {
        return this.CHKBX_Settings_ShowCompareDetails;
    }
    public javax.swing.JCheckBox getCHKBX_Settings_SoundNotifications() {
        return this.CHKBX_Settings_SoundNotifications;
    }
    public javax.swing.JComboBox<String> getCMBBX_SIaC_SearchType() {
        return this.CMBBX_SIaC_SearchType;
    }
    public javax.swing.JProgressBar getJPRGSBR_Choice_TotalProgress(){
        return this.JPRGSBR_Choice_TotalProgress;
    }
    public javax.swing.JLabel getLBL_Choice_DisplayPercentSimilar() {
        return this.LBL_Choice_DisplayPercentSimilar;
    }
    public javax.swing.JLabel getLBL_CompareInfo_FileType1() {
        return LBL_CompareInfo_FileType1;
    }
    public javax.swing.JLabel getLBL_CompareInfo_FileType2() {
        return LBL_CompareInfo_FileType2;
    }
    public javax.swing.JLabel getLBL_CompareInfo_IMG1Info() {
        return this.LBL_CompareInfo_IMG1Info;
    }
    public javax.swing.JLabel getLBL_CompareInfo_IMG2Info() {
        return this.LBL_CompareInfo_IMG2Info;
    }
    public javax.swing.JLabel getLBL_CompareInfo_IMGFilePath1() {
        return this.LBL_CompareInfo_IMGFilePath1;
    }
    public javax.swing.JLabel getLBL_CompareInfo_IMGFilePath2() {
        return this.LBL_CompareInfo_IMGFilePath2;
    }
    public javax.swing.JLabel getLBL_CompareInfo_IMGFileSize1() {
        return this.LBL_CompareInfo_IMGFileSize1;
    }
    public javax.swing.JLabel getLBL_CompareInfo_IMGFileSize2() {
        return this.LBL_CompareInfo_IMGFileSize2;
    }
    public javax.swing.JLabel getLBL_CompareInfo_IMGName1() {
        return this.LBL_CompareInfo_IMGName1;
    }
    public javax.swing.JLabel getLBL_CompareInfo_IMGName2() {
        return this.LBL_CompareInfo_IMGName2;
    }
    public javax.swing.JLabel getLBL_CompareInfo_IMGParentFolder1() {
        return this.LBL_CompareInfo_IMGParentFolder1;
    }
    public javax.swing.JLabel getLBL_CompareInfo_IMGParentFolder2() {
        return this.LBL_CompareInfo_IMGParentFolder2;
    }
    public javax.swing.JLabel getLBL_CompareInfo_ImageSize1() {
        return this.LBL_CompareInfo_ImageSize1;
    }
    public javax.swing.JLabel getLBL_CompareInfo_ImageSize2() {
        return this.LBL_CompareInfo_ImageSize2;
    }
    public void setLBL_CompareInfo_NumberOfFilesInF1(int newFileCount) {
        if (newFileCount != -1)
        {
            String numVal = String.format("%,d", newFileCount);
            switch (CMBBX_SIaC_SearchType.getSelectedIndex())
            {
                case 0: //Two images
                    //Break, because the label isn't shown in this comparison type.
                    break;
                case 1:
                    LBL_CompareInfo_NumberOfFilesInF1.setText("Number of images: " + numVal);
                    break;
                case 2:
                    LBL_CompareInfo_NumberOfFilesInF1.setText("Number of images in folder one: " + numVal);
            }  
        }
        else
        {
            switch (CMBBX_SIaC_SearchType.getSelectedIndex())
            {
                case 0: //Two images
                    //Break, because the label isn't shown in this comparison type.
                    break;
                case 1:
                    LBL_CompareInfo_NumberOfFilesInF1.setText("Number of images: ");
                    break;
                case 2:
                    LBL_CompareInfo_NumberOfFilesInF1.setText("Number of images in folder one: ");
            }  
        }
        
    }
    public void setLBL_CompareInfo_NumberOfFilesInF2(int newFileCount) {
        if (newFileCount != -1)
        {
            String numVal = String.format("%,d", newFileCount);
            LBL_CompareInfo_NumberOfFilesInF2.setText("Number of images in folder two: " + numVal); 
        }
        else
        {
            LBL_CompareInfo_NumberOfFilesInF2.setText("Number of images in folder two: ");
        }
    }
    public javax.swing.JLabel getLBL_IMG_AbsoluteDifferences() {
        return this.LBL_IMG_AbsoluteDifferences;
    }
    public javax.swing.JLabel getLBL_IMG_Image1() {
        return this.LBL_IMG_Image1;
    }
    public javax.swing.JLabel getLBL_IMG_Image2() {
        return this.LBL_IMG_Image2;
    }
    public javax.swing.JLabel getLBL_IMG_SubtractedDifferences() {
        return this.LBL_IMG_SubtractedDifferences;
    }
    public javax.swing.JSlider getSLDR_MinimumSimilarityThreshold() {
        return this.SLDR_MinimumSimilarityThreshold;
    }
    public javax.swing.JTabbedPane getTBDPN_UserInput() {
        return this.TBDPN_UserInput;
    }
    
    // === === === FUNCTIONS === === ===
    
    public void resetSelectionData() {        
        targetFolder[0] = null;
        targetFolder[1] = null;
        imgFile[0] = null;
        imgFile[1] = null;
        
        BTN_CompareInfo_Skip.setEnabled(false);
        BTN_CompareInfo_ChangeImage1.setEnabled(false);
        BTN_CompareInfo_ChangeImage2.setEnabled(false);
        BTN_CompareInfo_Cancel.setEnabled(false);
        
        clearDisplayedImages();
        clearCIFileInfo();
        LBL_CompareInfo_NumberOfFilesInF1.setText("Number of files: ");
        BTN_SIaC_Compare.setEnabled(false);
    }
    private void updateSIaCFolderInfoLabels() {
        switch (CMBBX_SIaC_SearchType.getSelectedIndex())
        {
            case 0: //Compare two images
                if (imgFile[0] != null)
                {
                    LBL_SIaC_DataName1.setText("Name: " + imgFile[0].getName());
                    LBL_SIaC_DataName1.setToolTipText(imgFile[0].getName());
                    LBL_SIaC_DataPath1.setText("File path: " + imgFile[0].getAbsolutePath());
                    LBL_SIaC_DataPath1.setToolTipText(imgFile[0].getAbsolutePath());
                }
                if (imgFile[1] != null)
                {
                    LBL_SIaC_DataName2.setText("Name: " + imgFile[1].getName());
                    LBL_SIaC_DataName2.setToolTipText(imgFile[1].getName());
                    LBL_SIaC_DataPath2.setText("File path: " + imgFile[1].getAbsolutePath());
                    LBL_SIaC_DataPath2.setToolTipText(imgFile[1].getAbsolutePath());
                }
                break;
            case 1: //Compare all images in one folder
                if (targetFolder[0] != null)
                {
                    LBL_SIaC_DataName1.setText("Name: " + targetFolder[0].getName());
                    LBL_SIaC_DataName1.setToolTipText(targetFolder[0].getName());
                    LBL_SIaC_DataPath1.setText("Folder path: " + targetFolder[0].getAbsolutePath());
                    LBL_SIaC_DataPath1.setToolTipText(targetFolder[0].getAbsolutePath());
                    
                    countImages(true);
                }
                break;
            case 2: //Compare all images between two folders
                if (targetFolder[0] != null)
                {
                    LBL_SIaC_DataName1.setText("Name: " + targetFolder[0].getName());
                    LBL_SIaC_DataName1.setToolTipText(targetFolder[0].getName());
                    LBL_SIaC_DataPath1.setText("Folder path: " + targetFolder[0].getAbsolutePath());
                    LBL_SIaC_DataPath1.setToolTipText(targetFolder[0].getAbsolutePath());
                    LBL_SIaC_ImageCount1.setText("Counting images...");
                    
                    countImages(true);
                }
                if (targetFolder[1] != null)
                {
                    LBL_SIaC_DataName2.setText("Name: " + targetFolder[1].getName());
                    LBL_SIaC_DataName2.setToolTipText(targetFolder[1].getName());
                    LBL_SIaC_DataPath2.setText("Folder path: " + targetFolder[1].getAbsolutePath());
                    LBL_SIaC_DataPath2.setToolTipText(targetFolder[1].getAbsolutePath());
                    LBL_SIaC_ImageCount2.setText("Counting images...");
                    
                    countImages(false);
                }
        }
    }
    private void countImages(boolean labelOne) {
        if (labelOne)
        {
            if (labelUpdate1.isAlive())
            {
                labelUpdate1.interrupt();
            }

            LBL_SIaC_ImageCount1.setText("Counting images...");
            UpdateLBLImageCount countFiles = new UpdateLBLImageCount();
            countFiles.setLabel(LBL_SIaC_ImageCount1);
            countFiles.setFolder(targetFolder[0]);
            countFiles.setIncludeSubfolders(this.CHKBX_SIaC_IncludeSubfoldersInFolder1.isSelected());
            countFiles.setPrefix("Number of images: ");

            labelUpdate1 = new Thread(countFiles);
            labelUpdate1.start();
        }
        else
        {
            if (labelUpdate2.isAlive())
            {
                labelUpdate2.interrupt();
            }

            LBL_SIaC_ImageCount2.setText("Counting images...");
            UpdateLBLImageCount countFiles = new UpdateLBLImageCount();                    
            countFiles.setLabel(LBL_SIaC_ImageCount2);
            countFiles.setFolder(targetFolder[1]);
            countFiles.setIncludeSubfolders(this.CHKBX_SIaC_IncludeSubfoldersInFolder2.isSelected());
            countFiles.setPrefix("Number of images: ");

            labelUpdate2 = new Thread(countFiles);
            labelUpdate2.start();
        }
    }
    public void checkIfReadyToCompare() {
        switch (CMBBX_SIaC_SearchType.getSelectedIndex())
        {
            case 0: //Compare two images
                if (imgFile[0] != null && imgFile[1] != null)
                { BTN_SIaC_Compare.setEnabled(true); }
                else { BTN_SIaC_Compare.setEnabled(false); }
                break;
            case 1: //Compare all images in one folder
                if (targetFolder[0] != null)
                { BTN_SIaC_Compare.setEnabled(true); }
                else { BTN_SIaC_Compare.setEnabled(false); }
                break;
            case 2: //Compare all images between two folders
                if (targetFolder[0] != null && targetFolder[1] != null)
                { BTN_SIaC_Compare.setEnabled(true); }
                else { BTN_SIaC_Compare.setEnabled(false); }
        }
    }
    private boolean checkImageValidity(File checkFile) {
        if (!checkFile.exists()) { return false; }        
        CompareImages validate = new CompareImages();        
        return validate.checkIfValidImage(checkFile);
    }
    private void clearCIFileInfo() {
        LBL_CompareInfo_IMGName1.setText("Name: "); //LBL_CompareInfo_IMGName1
        LBL_CompareInfo_ImageSize1.setText("Image size: "); //LBL_CompareInfo_ImageSize1
        LBL_CompareInfo_IMGFileSize1.setText("File size: "); //LBL_CompareInfo_IMGFileSize1
        LBL_CompareInfo_IMGParentFolder1.setText("Parent folder: "); //LBL_CompareInfo_IMGParentFolder1
        LBL_CompareInfo_IMGFilePath1.setText("File path: "); //LBL_CompareInfo_IMGFilePath1
        
        LBL_CompareInfo_IMGName2.setText("Name: "); //LBL_CompareInfo_IMGName2
        LBL_CompareInfo_ImageSize2.setText("Image size: "); //LBL_CompareInfo_ImageSize2
        LBL_CompareInfo_IMGFileSize2.setText("File size: "); //LBL_CompareInfo_IMGFileSize2
        LBL_CompareInfo_IMGParentFolder2.setText("Parent folder: "); //LBL_CompareInfo_IMGParentFolder2
        LBL_CompareInfo_IMGFilePath2.setText("File path: "); //LBL_CompareInfo_IMGFilePath2
        
        LBL_Choice_DisplayPercentSimilar.setText("Percent similar: "); //LBL_Choice_DisplayPercentSimilar
    }
    private void clearDisplayedImages() {
        BufferedImage blankImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Color blankColor = new Color(240, 240, 240);
        blankImage.setRGB(0, 0, blankColor.getRGB());
        
        LBL_IMG_Image1.setIcon(new ImageIcon(blankImage));
        LBL_IMG_Image2.setIcon(new ImageIcon(blankImage));
        LBL_IMG_AbsoluteDifferences.setIcon(new ImageIcon(blankImage));
        LBL_IMG_SubtractedDifferences.setIcon(new ImageIcon(blankImage));
    }
    
    // === === === GENERATED CODE === === ===
    
    public DIR_Window() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        CMBBX_SIaC_SearchType.setSelectedIndex(0);
        
        //* Change the text color of the progress bar so that it's black both with and without the progress color.
        BasicProgressBarUI textColor = new BasicProgressBarUI() {
            protected Color getSelectionBackground() { return Color.black; }
            protected Color getSelectionForeground() { return Color.black; }
        };
        this.JPRGSBR_Choice_TotalProgress.setUI(textColor);
        this.JPRGSBR_Choice_TotalProgress.setForeground(new Color(21, 214, 58)); //Change the progress color to a teal-ish green
        //*/
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TBDPN_Images = new javax.swing.JTabbedPane();
        SCRLPN_Image1 = new javax.swing.JScrollPane();
        LBL_IMG_Image1 = new javax.swing.JLabel();
        SCRLPN_Image2 = new javax.swing.JScrollPane();
        LBL_IMG_Image2 = new javax.swing.JLabel();
        SCRLPN_HighlightedDifferences = new javax.swing.JScrollPane();
        LBL_IMG_AbsoluteDifferences = new javax.swing.JLabel();
        SCRLPN_SubtractedDifferences = new javax.swing.JScrollPane();
        LBL_IMG_SubtractedDifferences = new javax.swing.JLabel();
        TBDPN_UserInput = new javax.swing.JTabbedPane();
        JPNL_SIaC = new javax.swing.JPanel();
        CMBBX_SIaC_SearchType = new javax.swing.JComboBox<>();
        CHKBX_SIaC_IncludeSubfoldersInFolder1 = new javax.swing.JCheckBox();
        BTN_SIaC_Data1 = new javax.swing.JButton();
        BTN_SIaC_Data2 = new javax.swing.JButton();
        BTN_SIaC_Compare = new javax.swing.JButton();
        LBL_SIaC_DataHeader1 = new javax.swing.JLabel();
        LBL_SIaC_DataHeader2 = new javax.swing.JLabel();
        LBL_SIaC_DataName1 = new javax.swing.JLabel();
        LBL_SIaC_DataPath1 = new javax.swing.JLabel();
        LBL_SIaC_ImageCount1 = new javax.swing.JLabel();
        LBL_SIaC_ImageCount2 = new javax.swing.JLabel();
        LBL_SIaC_DataPath2 = new javax.swing.JLabel();
        LBL_SIaC_DataName2 = new javax.swing.JLabel();
        SIaC_Filler = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        CHKBX_SIaC_IncludeSubfoldersInFolder2 = new javax.swing.JCheckBox();
        PNL_CompareInfo = new javax.swing.JPanel();
        JPRGSBR_Choice_TotalProgress = new javax.swing.JProgressBar();
        LBL_CompareInfo_IMG1Info = new javax.swing.JLabel();
        LBL_CompareInfo_IMG2Info = new javax.swing.JLabel();
        LBL_CompareInfo_ImageSize1 = new javax.swing.JLabel();
        LBL_CompareInfo_IMGName1 = new javax.swing.JLabel();
        LBL_CompareInfo_IMGFileSize1 = new javax.swing.JLabel();
        LBL_CompareInfo_IMGFileSize2 = new javax.swing.JLabel();
        LBL_CompareInfo_ImageSize2 = new javax.swing.JLabel();
        LBL_CompareInfo_IMGName2 = new javax.swing.JLabel();
        LBL_CompareInfo_IMGFilePath1 = new javax.swing.JLabel();
        LBL_CompareInfo_IMGFilePath2 = new javax.swing.JLabel();
        BTN_CompareInfo_ChangeImage1 = new javax.swing.JButton();
        BTN_CompareInfo_ChangeImage2 = new javax.swing.JButton();
        BTN_CompareInfo_Skip = new javax.swing.JButton();
        BTN_CompareInfo_Cancel = new javax.swing.JButton();
        LBL_Choice_DisplayPercentSimilar = new javax.swing.JLabel();
        LBL_CompareInfo_IMGParentFolder1 = new javax.swing.JLabel();
        LBL_CompareInfo_IMGParentFolder2 = new javax.swing.JLabel();
        Filler_CompareInfo = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        LBL_CompareInfo_NumberOfFilesInF1 = new javax.swing.JLabel();
        LBL_CompareInfo_FileType2 = new javax.swing.JLabel();
        LBL_CompareInfo_FileType1 = new javax.swing.JLabel();
        LBL_CompareInfo_TaskDetails = new javax.swing.JLabel();
        LBL_CompareInfo_ComparisonType = new javax.swing.JLabel();
        LBL_CompareInfo_NumberOfFilesInF2 = new javax.swing.JLabel();
        PNL_Settings = new javax.swing.JPanel();
        SLDR_MinimumSimilarityThreshold = new javax.swing.JSlider();
        CHKBX_Settings_SoundNotifications = new javax.swing.JCheckBox();
        LBL_Settings_SimilarityThresholdInstructionLabel = new javax.swing.JLabel();
        CHKBX_Settings_RepeatNotificationSound = new javax.swing.JCheckBox();
        CHKBX_Settings_ShowCompareDetails = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Duplicate Image Remover");
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setName(""); // NOI18N
        setSize(new java.awt.Dimension(1000, 700));

        TBDPN_Images.setFocusable(false);

        LBL_IMG_Image1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SCRLPN_Image1.setViewportView(LBL_IMG_Image1);

        TBDPN_Images.addTab("Image One", SCRLPN_Image1);

        LBL_IMG_Image2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SCRLPN_Image2.setViewportView(LBL_IMG_Image2);

        TBDPN_Images.addTab("Image Two", SCRLPN_Image2);

        LBL_IMG_AbsoluteDifferences.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SCRLPN_HighlightedDifferences.setViewportView(LBL_IMG_AbsoluteDifferences);

        TBDPN_Images.addTab(" Absolute Differences", SCRLPN_HighlightedDifferences);

        LBL_IMG_SubtractedDifferences.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SCRLPN_SubtractedDifferences.setViewportView(LBL_IMG_SubtractedDifferences);

        TBDPN_Images.addTab("Subtracted Differences", SCRLPN_SubtractedDifferences);

        TBDPN_UserInput.setFocusable(false);
        TBDPN_UserInput.setName(""); // NOI18N

        JPNL_SIaC.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        CMBBX_SIaC_SearchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Compare two images", "Compare one folder", "Compare two folders" }));
        CMBBX_SIaC_SearchType.setFocusable(false);
        CMBBX_SIaC_SearchType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBBX_SIaC_SearchTypeActionPerformed(evt);
            }
        });

        CHKBX_SIaC_IncludeSubfoldersInFolder1.setText("Include subfolders");
        CHKBX_SIaC_IncludeSubfoldersInFolder1.setFocusable(false);
        CHKBX_SIaC_IncludeSubfoldersInFolder1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHKBX_SIaC_IncludeSubfoldersInFolder1ActionPerformed(evt);
            }
        });

        BTN_SIaC_Data1.setText("Get image one");
        BTN_SIaC_Data1.setFocusable(false);
        BTN_SIaC_Data1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SIaC_Data1ActionPerformed(evt);
            }
        });

        BTN_SIaC_Data2.setText("Get image two");
        BTN_SIaC_Data2.setFocusable(false);
        BTN_SIaC_Data2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SIaC_Data2ActionPerformed(evt);
            }
        });

        BTN_SIaC_Compare.setText("Compare");
        BTN_SIaC_Compare.setEnabled(false);
        BTN_SIaC_Compare.setFocusable(false);
        BTN_SIaC_Compare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SIaC_CompareActionPerformed(evt);
            }
        });

        LBL_SIaC_DataHeader1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LBL_SIaC_DataHeader1.setText("Folder One Information:");

        LBL_SIaC_DataHeader2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LBL_SIaC_DataHeader2.setText("Folder Two Information:");

        LBL_SIaC_DataName1.setText("Name:");

        LBL_SIaC_DataPath1.setText("Folder path:");

        LBL_SIaC_ImageCount1.setText("Number of images:");

        LBL_SIaC_ImageCount2.setText("Number of images:");

        LBL_SIaC_DataPath2.setText("Folder path:");

        LBL_SIaC_DataName2.setText("Name:");

        CHKBX_SIaC_IncludeSubfoldersInFolder2.setText("Include subfolders");
        CHKBX_SIaC_IncludeSubfoldersInFolder2.setFocusable(false);
        CHKBX_SIaC_IncludeSubfoldersInFolder2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHKBX_SIaC_IncludeSubfoldersInFolder2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPNL_SIaCLayout = new javax.swing.GroupLayout(JPNL_SIaC);
        JPNL_SIaC.setLayout(JPNL_SIaCLayout);
        JPNL_SIaCLayout.setHorizontalGroup(
            JPNL_SIaCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNL_SIaCLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPNL_SIaCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SIaC_Filler, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CMBBX_SIaC_SearchType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_SIaC_Data1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_SIaC_Compare, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CHKBX_SIaC_IncludeSubfoldersInFolder1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_SIaC_Data2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_SIaC_DataHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_SIaC_DataName1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_SIaC_DataPath1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_SIaC_ImageCount1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_SIaC_DataHeader2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_SIaC_DataName2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_SIaC_DataPath2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_SIaC_ImageCount2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CHKBX_SIaC_IncludeSubfoldersInFolder2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        JPNL_SIaCLayout.setVerticalGroup(
            JPNL_SIaCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNL_SIaCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CMBBX_SIaC_SearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_SIaC_DataHeader1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_SIaC_DataName1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_SIaC_DataPath1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_SIaC_ImageCount1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTN_SIaC_Data1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CHKBX_SIaC_IncludeSubfoldersInFolder1)
                .addGap(18, 18, 18)
                .addComponent(LBL_SIaC_DataHeader2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_SIaC_DataName2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_SIaC_DataPath2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_SIaC_ImageCount2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTN_SIaC_Data2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CHKBX_SIaC_IncludeSubfoldersInFolder2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SIaC_Filler, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTN_SIaC_Compare)
                .addContainerGap())
        );

        TBDPN_UserInput.addTab("Select Images and Compare", JPNL_SIaC);

        PNL_CompareInfo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        JPRGSBR_Choice_TotalProgress.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JPRGSBR_Choice_TotalProgress.setFocusable(false);
        JPRGSBR_Choice_TotalProgress.setMaximumSize(new java.awt.Dimension(32767, 20));
        JPRGSBR_Choice_TotalProgress.setMinimumSize(new java.awt.Dimension(10, 15));
        JPRGSBR_Choice_TotalProgress.setPreferredSize(new java.awt.Dimension(146, 16));
        JPRGSBR_Choice_TotalProgress.setRequestFocusEnabled(false);
        JPRGSBR_Choice_TotalProgress.setString("");

        LBL_CompareInfo_IMG1Info.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LBL_CompareInfo_IMG1Info.setText("Image one information:");

        LBL_CompareInfo_IMG2Info.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LBL_CompareInfo_IMG2Info.setText("Image two information:");

        LBL_CompareInfo_ImageSize1.setText("Image size:");
        LBL_CompareInfo_ImageSize1.setMaximumSize(new java.awt.Dimension(271, 14));
        LBL_CompareInfo_ImageSize1.setMinimumSize(new java.awt.Dimension(271, 14));

        LBL_CompareInfo_IMGName1.setText("Name:");
        LBL_CompareInfo_IMGName1.setMaximumSize(new java.awt.Dimension(271, 14));
        LBL_CompareInfo_IMGName1.setMinimumSize(new java.awt.Dimension(271, 14));

        LBL_CompareInfo_IMGFileSize1.setText("File size:");
        LBL_CompareInfo_IMGFileSize1.setMaximumSize(new java.awt.Dimension(271, 14));
        LBL_CompareInfo_IMGFileSize1.setMinimumSize(new java.awt.Dimension(271, 14));

        LBL_CompareInfo_IMGFileSize2.setText("File size:");
        LBL_CompareInfo_IMGFileSize2.setMaximumSize(new java.awt.Dimension(271, 14));
        LBL_CompareInfo_IMGFileSize2.setMinimumSize(new java.awt.Dimension(271, 14));

        LBL_CompareInfo_ImageSize2.setText("Image size:");
        LBL_CompareInfo_ImageSize2.setMaximumSize(new java.awt.Dimension(271, 14));
        LBL_CompareInfo_ImageSize2.setMinimumSize(new java.awt.Dimension(271, 14));

        LBL_CompareInfo_IMGName2.setText("Name:");
        LBL_CompareInfo_IMGName2.setMaximumSize(new java.awt.Dimension(271, 14));
        LBL_CompareInfo_IMGName2.setMinimumSize(new java.awt.Dimension(271, 14));

        LBL_CompareInfo_IMGFilePath1.setText("File path:");
        LBL_CompareInfo_IMGFilePath1.setMaximumSize(new java.awt.Dimension(271, 14));
        LBL_CompareInfo_IMGFilePath1.setMinimumSize(new java.awt.Dimension(271, 14));

        LBL_CompareInfo_IMGFilePath2.setText("File path:");
        LBL_CompareInfo_IMGFilePath2.setMaximumSize(new java.awt.Dimension(271, 14));
        LBL_CompareInfo_IMGFilePath2.setMinimumSize(new java.awt.Dimension(271, 14));

        BTN_CompareInfo_ChangeImage1.setText("Delete image one");
        BTN_CompareInfo_ChangeImage1.setEnabled(false);
        BTN_CompareInfo_ChangeImage1.setFocusable(false);
        BTN_CompareInfo_ChangeImage1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CompareInfo_ChangeImage1ActionPerformed(evt);
            }
        });

        BTN_CompareInfo_ChangeImage2.setText("Delete image two");
        BTN_CompareInfo_ChangeImage2.setEnabled(false);
        BTN_CompareInfo_ChangeImage2.setFocusable(false);
        BTN_CompareInfo_ChangeImage2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CompareInfo_ChangeImage2ActionPerformed(evt);
            }
        });

        BTN_CompareInfo_Skip.setText("Skip deletion");
        BTN_CompareInfo_Skip.setEnabled(false);
        BTN_CompareInfo_Skip.setFocusable(false);
        BTN_CompareInfo_Skip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CompareInfo_SkipActionPerformed(evt);
            }
        });

        BTN_CompareInfo_Cancel.setText("Stop comparing");
        BTN_CompareInfo_Cancel.setEnabled(false);
        BTN_CompareInfo_Cancel.setFocusable(false);
        BTN_CompareInfo_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CompareInfo_CancelActionPerformed(evt);
            }
        });

        LBL_Choice_DisplayPercentSimilar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LBL_Choice_DisplayPercentSimilar.setText("Percent similar: ");

        LBL_CompareInfo_IMGParentFolder1.setText("Parent folder:");
        LBL_CompareInfo_IMGParentFolder1.setMaximumSize(new java.awt.Dimension(271, 14));
        LBL_CompareInfo_IMGParentFolder1.setMinimumSize(new java.awt.Dimension(271, 14));

        LBL_CompareInfo_IMGParentFolder2.setText("Parent folder:");
        LBL_CompareInfo_IMGParentFolder2.setMaximumSize(new java.awt.Dimension(271, 14));
        LBL_CompareInfo_IMGParentFolder2.setMinimumSize(new java.awt.Dimension(271, 14));

        LBL_CompareInfo_NumberOfFilesInF1.setText("Number of files: ");

        LBL_CompareInfo_FileType2.setText("File type:");

        LBL_CompareInfo_FileType1.setText("File type:");

        LBL_CompareInfo_TaskDetails.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LBL_CompareInfo_TaskDetails.setText("Task details:");

        LBL_CompareInfo_ComparisonType.setText("Comparison type:");

        LBL_CompareInfo_NumberOfFilesInF2.setText("Number of files:");

        javax.swing.GroupLayout PNL_CompareInfoLayout = new javax.swing.GroupLayout(PNL_CompareInfo);
        PNL_CompareInfo.setLayout(PNL_CompareInfoLayout);
        PNL_CompareInfoLayout.setHorizontalGroup(
            PNL_CompareInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNL_CompareInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PNL_CompareInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LBL_CompareInfo_NumberOfFilesInF1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Filler_CompareInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTN_CompareInfo_ChangeImage2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CompareInfo_IMG1Info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTN_CompareInfo_Skip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTN_CompareInfo_ChangeImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JPRGSBR_Choice_TotalProgress, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addComponent(BTN_CompareInfo_Cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CompareInfo_IMGName1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CompareInfo_IMGFilePath1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CompareInfo_ImageSize1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CompareInfo_IMGFileSize1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CompareInfo_IMG2Info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_Choice_DisplayPercentSimilar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CompareInfo_IMGName2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CompareInfo_IMGFilePath2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CompareInfo_ImageSize2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CompareInfo_IMGFileSize2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CompareInfo_IMGParentFolder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CompareInfo_IMGParentFolder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CompareInfo_FileType2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CompareInfo_FileType1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CompareInfo_TaskDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CompareInfo_ComparisonType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CompareInfo_NumberOfFilesInF2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PNL_CompareInfoLayout.setVerticalGroup(
            PNL_CompareInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PNL_CompareInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LBL_CompareInfo_TaskDetails)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_CompareInfo_ComparisonType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_CompareInfo_NumberOfFilesInF1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_CompareInfo_NumberOfFilesInF2)
                .addGap(18, 18, 18)
                .addComponent(LBL_CompareInfo_IMG1Info)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_CompareInfo_IMGName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_CompareInfo_IMGParentFolder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_CompareInfo_IMGFilePath1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LBL_CompareInfo_ImageSize1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_CompareInfo_IMGFileSize1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_CompareInfo_FileType1)
                .addGap(18, 18, 18)
                .addComponent(LBL_CompareInfo_IMG2Info)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_CompareInfo_IMGName2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_CompareInfo_IMGParentFolder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_CompareInfo_IMGFilePath2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LBL_CompareInfo_ImageSize2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_CompareInfo_IMGFileSize2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_CompareInfo_FileType2)
                .addGap(18, 18, 18)
                .addComponent(LBL_Choice_DisplayPercentSimilar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Filler_CompareInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTN_CompareInfo_Skip)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BTN_CompareInfo_ChangeImage1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTN_CompareInfo_ChangeImage2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BTN_CompareInfo_Cancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPRGSBR_Choice_TotalProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        TBDPN_UserInput.addTab("Compare Info", PNL_CompareInfo);

        PNL_Settings.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        SLDR_MinimumSimilarityThreshold.setMajorTickSpacing(5);
        SLDR_MinimumSimilarityThreshold.setMinimum(75);
        SLDR_MinimumSimilarityThreshold.setMinorTickSpacing(1);
        SLDR_MinimumSimilarityThreshold.setPaintLabels(true);
        SLDR_MinimumSimilarityThreshold.setPaintTicks(true);
        SLDR_MinimumSimilarityThreshold.setSnapToTicks(true);
        SLDR_MinimumSimilarityThreshold.setToolTipText("");
        SLDR_MinimumSimilarityThreshold.setValue(95);
        SLDR_MinimumSimilarityThreshold.setFocusable(false);
        SLDR_MinimumSimilarityThreshold.setRequestFocusEnabled(false);

        CHKBX_Settings_SoundNotifications.setSelected(true);
        CHKBX_Settings_SoundNotifications.setText("Play a sound when a potential match is found.");
        CHKBX_Settings_SoundNotifications.setFocusable(false);
        CHKBX_Settings_SoundNotifications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHKBX_Settings_SoundNotificationsActionPerformed(evt);
            }
        });

        LBL_Settings_SimilarityThresholdInstructionLabel.setText("Minimum percent similarity required for manual review:");

        CHKBX_Settings_RepeatNotificationSound.setText("Repeat sound once per minute while waiting.");
        CHKBX_Settings_RepeatNotificationSound.setFocusable(false);
        CHKBX_Settings_RepeatNotificationSound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHKBX_Settings_RepeatNotificationSoundActionPerformed(evt);
            }
        });

        CHKBX_Settings_ShowCompareDetails.setSelected(true);
        CHKBX_Settings_ShowCompareDetails.setText("Display general information once finished.");
        CHKBX_Settings_ShowCompareDetails.setFocusable(false);

        javax.swing.GroupLayout PNL_SettingsLayout = new javax.swing.GroupLayout(PNL_Settings);
        PNL_Settings.setLayout(PNL_SettingsLayout);
        PNL_SettingsLayout.setHorizontalGroup(
            PNL_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNL_SettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PNL_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SLDR_MinimumSimilarityThreshold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_Settings_SimilarityThresholdInstructionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CHKBX_Settings_ShowCompareDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CHKBX_Settings_RepeatNotificationSound, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CHKBX_Settings_SoundNotifications, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PNL_SettingsLayout.setVerticalGroup(
            PNL_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNL_SettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LBL_Settings_SimilarityThresholdInstructionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SLDR_MinimumSimilarityThreshold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CHKBX_Settings_SoundNotifications)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CHKBX_Settings_RepeatNotificationSound)
                .addGap(18, 18, 18)
                .addComponent(CHKBX_Settings_ShowCompareDetails)
                .addContainerGap(465, Short.MAX_VALUE))
        );

        TBDPN_UserInput.addTab("Settings", PNL_Settings);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TBDPN_UserInput, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TBDPN_Images, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TBDPN_UserInput)
                    .addComponent(TBDPN_Images))
                .addContainerGap())
        );

        TBDPN_UserInput.getAccessibleContext().setAccessibleName("Select Images");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_SIaC_CompareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SIaC_CompareActionPerformed
        TBDPN_UserInput.setSelectedIndex(1); //Switch to the image choice tab.
        TBDPN_UserInput.setEnabledAt(0, false);

        CompareProcess newCompare = new CompareProcess();
        newCompare.setParent(this);

        switch (CMBBX_SIaC_SearchType.getSelectedIndex())
        {
            case 0: //Compare two images
                newCompare.setTargetFiles(imgFile[0], imgFile[1]);
                newCompare.setSearchType(CompareProcess.SearchType.TWO_IMAGES);
                break;
            case 1: //Compare all images in one folder
                newCompare.setTargetFolder1(targetFolder[0]);
                newCompare.setSearchType(CompareProcess.SearchType.ONE_FOLDER);
                break;
            case 2: //Compare all images between two folders
                newCompare.setTargetFolder1(targetFolder[0]);
                newCompare.setTargetFolder2(targetFolder[1]);
                newCompare.setSearchType(CompareProcess.SearchType.TWO_FOLDERS);
        }

        new Thread(newCompare).start();
    }//GEN-LAST:event_BTN_SIaC_CompareActionPerformed
    private void CMBBX_SIaC_SearchTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMBBX_SIaC_SearchTypeActionPerformed
        resetSelectionData();
        
        setLBL_CompareInfo_NumberOfFilesInF1(-1);
        setLBL_CompareInfo_NumberOfFilesInF2(-1);
        
        switch (CMBBX_SIaC_SearchType.getSelectedIndex())
        {
            case 0: //Compare two images
                CHKBX_SIaC_IncludeSubfoldersInFolder1.setVisible(false);
                CHKBX_SIaC_IncludeSubfoldersInFolder1.setSelected(false);
                CHKBX_SIaC_IncludeSubfoldersInFolder2.setVisible(false);
                CHKBX_SIaC_IncludeSubfoldersInFolder2.setSelected(false);
                
                BTN_SIaC_Data1.setText("Get image one");
                BTN_SIaC_Data2.setVisible(true);
                BTN_SIaC_Data2.setText("Get image two");
                
                LBL_SIaC_DataHeader1.setText("Image One Information:");
                LBL_SIaC_DataName1.setText("Name:");
                LBL_SIaC_DataPath1.setText("File path:");
                LBL_SIaC_ImageCount1.setVisible(false);
                LBL_SIaC_DataHeader2.setText("Image Two Information:");
                LBL_SIaC_DataHeader2.setVisible(true);
                LBL_SIaC_DataName2.setText("Name:");
                LBL_SIaC_DataName2.setVisible(true);
                LBL_SIaC_DataPath2.setText("File path:");
                LBL_SIaC_DataPath2.setVisible(true);
                LBL_SIaC_ImageCount2.setVisible(false);
                
                LBL_CompareInfo_ComparisonType.setText("Comparison type: Two images");
                LBL_CompareInfo_NumberOfFilesInF1.setVisible(false);
                LBL_CompareInfo_NumberOfFilesInF2.setVisible(false);
                
                break;
            case 1: //Compare all images in one folder
                CHKBX_SIaC_IncludeSubfoldersInFolder1.setVisible(true);
                CHKBX_SIaC_IncludeSubfoldersInFolder2.setVisible(false);
                CHKBX_SIaC_IncludeSubfoldersInFolder2.setSelected(false);
                
                BTN_SIaC_Data1.setText("Get folder");
                BTN_SIaC_Data2.setVisible(false);
                
                LBL_SIaC_DataHeader1.setText("Folder Information:");
                LBL_SIaC_DataName1.setText("Name:");
                LBL_SIaC_DataPath1.setText("Folder path:");
                LBL_SIaC_ImageCount1.setText("Number of images:");
                LBL_SIaC_ImageCount1.setVisible(true);
                LBL_SIaC_DataHeader2.setVisible(false);
                LBL_SIaC_DataName2.setVisible(false);
                LBL_SIaC_DataPath2.setVisible(false);
                LBL_SIaC_ImageCount2.setVisible(false);
                
                LBL_CompareInfo_ComparisonType.setText("Comparison type: Single folder");
                LBL_CompareInfo_NumberOfFilesInF1.setVisible(true);
                LBL_CompareInfo_NumberOfFilesInF2.setVisible(false);
                
                break;
            case 2: //Compare all images between two folders
                CHKBX_SIaC_IncludeSubfoldersInFolder1.setVisible(true);
                CHKBX_SIaC_IncludeSubfoldersInFolder2.setVisible(true);
                
                
                BTN_SIaC_Data1.setText("Get folder one");
                BTN_SIaC_Data2.setVisible(true);
                BTN_SIaC_Data2.setText("Get folder two");
                
                LBL_SIaC_DataHeader1.setText("Folder One Information:");
                LBL_SIaC_DataName1.setText("Name:");
                LBL_SIaC_DataPath1.setText("Folder path:");
                LBL_SIaC_ImageCount1.setText("Number of images:");
                LBL_SIaC_DataHeader2.setText("Folder Two Information:");
                LBL_SIaC_DataName2.setText("Name:");
                LBL_SIaC_DataPath2.setText("Folder path:");
                LBL_SIaC_ImageCount2.setText("Number of images:");
                LBL_SIaC_ImageCount1.setVisible(true);
                LBL_SIaC_DataHeader2.setVisible(true);
                LBL_SIaC_DataName2.setVisible(true);
                LBL_SIaC_DataPath2.setVisible(true);
                LBL_SIaC_ImageCount2.setVisible(true);
                
                LBL_CompareInfo_ComparisonType.setText("Comparison type: Two folders");
                LBL_CompareInfo_NumberOfFilesInF1.setVisible(true);
                LBL_CompareInfo_NumberOfFilesInF2.setVisible(true);
        }
    }//GEN-LAST:event_CMBBX_SIaC_SearchTypeActionPerformed
    private void BTN_SIaC_Data1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SIaC_Data1ActionPerformed
        JFileChooser getData = new JFileChooser();
        switch (CMBBX_SIaC_SearchType.getSelectedIndex())
        {
            case 0: //Compare two images
                getData.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (getData.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
                {
                    if (!getData.getSelectedFile().exists())
                    {
                        String errorMSG = "That file does not exist.";
                        JOptionPane.showMessageDialog(this, errorMSG, "File Does Not Exist", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (!checkImageValidity(getData.getSelectedFile()))
                    {
                        String errorMSG = "The selected file is not a valid image.";
                        JOptionPane.showMessageDialog(this, errorMSG, "Invalid File Type", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        imgFile[0] = getData.getSelectedFile();
                        try
                        {
                            CompareImages importIMG = new CompareImages();
                            BufferedImage imgBuff = importIMG.importImage(imgFile[0]);
                            LBL_IMG_Image1.setIcon(new ImageIcon(imgBuff));
                        }
                        catch (IOException ex)
                        {
                            imgFile[0] = null;
                            clearDisplayedImages();
                            
                            String errorMSG = "", popupTitle = "";
                            
                            ImageIcon img = new ImageIcon(getData.getSelectedFile().getAbsolutePath());
                            if (img.getIconHeight() < 1 || img.getIconWidth() < 1)
                            {
                                errorMSG = "The selected image cannot be read. It may be an invalid file type that was renamed to look" +
                                           "\nlike a valid image. If this is the case, please instead convert it using an image converter." +
                                           "\nValid image types can be found in the README file.";
                                popupTitle = "Invalid Image";
                            }
                            else
                            {
                                errorMSG = "Unable to import the image.\nError message: " + ex.getMessage();
                                popupTitle = "Failed to Import Image";
                            }
                            JOptionPane.showMessageDialog(this, errorMSG, popupTitle, JOptionPane.ERROR_MESSAGE);
                        }
                        TBDPN_Images.setSelectedIndex(0);
                    }
                }
                break;
            case 1: //Compare all images in one folder                
            case 2: //Compare all images between two folders
                getData.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);                
                if (getData.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
                {
                    if (!getData.getSelectedFile().exists())
                    {
                        String errorMSG = "That file does not exist.";
                        JOptionPane.showMessageDialog(this, errorMSG, "File Does Not Exist", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        targetFolder[0] = getData.getSelectedFile();
                        if (targetFolder[0] != null && targetFolder[1] != null)
                        {
                            if (targetFolder[0].getAbsolutePath().equals(targetFolder[1].getAbsolutePath()))
                            {
                                targetFolder[0] = null;
                                String errorMSG = "You cannot choose the same folder twice. ";
                                errorMSG += "If you would like to compare the images inside this folder, please change the search options.";
                                JOptionPane.showMessageDialog(this, errorMSG, "Invalid Folder Selection", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
        }
        updateSIaCFolderInfoLabels();
        checkIfReadyToCompare();
    }//GEN-LAST:event_BTN_SIaC_Data1ActionPerformed
    private void BTN_SIaC_Data2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SIaC_Data2ActionPerformed
        JFileChooser getData = new JFileChooser();
        switch (CMBBX_SIaC_SearchType.getSelectedIndex())
        {
            case 0: //Compare two images
                getData.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (getData.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
                {
                    if (!getData.getSelectedFile().exists())
                    {
                        String errorMSG = "That file does not exist.";
                        JOptionPane.showMessageDialog(this, errorMSG, "File Does Not Exist", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (!checkImageValidity(getData.getSelectedFile()))
                    {
                        String errorMSG = "The selected file is not a valid image.";
                        JOptionPane.showMessageDialog(this, errorMSG, "Invalid File Type", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        imgFile[1] = getData.getSelectedFile();
                        try {
                            CompareImages importIMG = new CompareImages();
                            BufferedImage imgBuff = importIMG.importImage(imgFile[1]);
                            LBL_IMG_Image2.setIcon(new ImageIcon(imgBuff));
                        } catch (IOException ex) {
                            imgFile[1] = null;
                            clearDisplayedImages();
                            
                            String errorMSG = "", popupTitle = "";
                            
                            ImageIcon img = new ImageIcon(getData.getSelectedFile().getAbsolutePath());
                            if (img.getIconHeight() < 1 || img.getIconWidth() < 1)
                            {
                                errorMSG = "The selected image cannot be read. It may be an invalid file type that was renamed to look" +
                                           "\nlike a valid image. If this is the case, please instead convert it using an image converter." +
                                           "\nValid image types can be found in the README file.";
                                popupTitle = "Invalid Image";
                            }
                            else
                            {
                                errorMSG = "Unable to import the image.\nError message: " + ex.getMessage();
                                popupTitle = "Failed to Import Image";
                            }
                            JOptionPane.showMessageDialog(this, errorMSG, popupTitle, JOptionPane.ERROR_MESSAGE);
                        }
                        TBDPN_Images.setSelectedIndex(1);
                    }
                }
                break;
            case 1: //Compare all images in one folder
                break;
            case 2: //Compare all images between two folders
                getData.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);                
                if (getData.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
                {
                    if (!getData.getSelectedFile().exists())
                    {
                        String errorMSG = "That file does not exist.";
                        JOptionPane.showMessageDialog(this, errorMSG, "File Does Not Exist", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        targetFolder[1] = getData.getSelectedFile();
                        if (targetFolder[0] != null && targetFolder[1] != null)
                        {
                            if (targetFolder[0].getAbsolutePath().equals(targetFolder[1].getAbsolutePath()))
                            {
                                targetFolder[1] = null;
                                String errorMSG = "You cannot choose the same folder twice. ";
                                errorMSG += "If you would like to compare the images inside this folder, please change the search options.";
                                JOptionPane.showMessageDialog(this, errorMSG, "Invalid Folder Selection", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
        }
        updateSIaCFolderInfoLabels();
        checkIfReadyToCompare();
    }//GEN-LAST:event_BTN_SIaC_Data2ActionPerformed
    private void CHKBX_SIaC_IncludeSubfoldersInFolder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CHKBX_SIaC_IncludeSubfoldersInFolder1ActionPerformed
        switch (CMBBX_SIaC_SearchType.getSelectedIndex())
        {
            case 0: //Compare two images
                break;
            case 1: //Compare all images in one folder
            case 2: //Compare all images between two folders
                if (targetFolder[0] != null)
                {
                    countImages(true);
                }
        }
    }//GEN-LAST:event_CHKBX_SIaC_IncludeSubfoldersInFolder1ActionPerformed
    private void BTN_CompareInfo_SkipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CompareInfo_SkipActionPerformed
    }//GEN-LAST:event_BTN_CompareInfo_SkipActionPerformed
    private void BTN_CompareInfo_ChangeImage1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CompareInfo_ChangeImage1ActionPerformed
    }//GEN-LAST:event_BTN_CompareInfo_ChangeImage1ActionPerformed
    private void BTN_CompareInfo_ChangeImage2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CompareInfo_ChangeImage2ActionPerformed
    }//GEN-LAST:event_BTN_CompareInfo_ChangeImage2ActionPerformed
    private void BTN_CompareInfo_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CompareInfo_CancelActionPerformed
    }//GEN-LAST:event_BTN_CompareInfo_CancelActionPerformed
    private void CHKBX_Settings_RepeatNotificationSoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CHKBX_Settings_RepeatNotificationSoundActionPerformed
    }//GEN-LAST:event_CHKBX_Settings_RepeatNotificationSoundActionPerformed
    private void CHKBX_Settings_SoundNotificationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CHKBX_Settings_SoundNotificationsActionPerformed
        if (this.CHKBX_Settings_SoundNotifications.isSelected())
        {
            this.CHKBX_Settings_RepeatNotificationSound.setEnabled(true);
        }
        else
        {
            this.CHKBX_Settings_RepeatNotificationSound.setEnabled(false);
            this.CHKBX_Settings_RepeatNotificationSound.setSelected(false);
        }
    }//GEN-LAST:event_CHKBX_Settings_SoundNotificationsActionPerformed

    private void CHKBX_SIaC_IncludeSubfoldersInFolder2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CHKBX_SIaC_IncludeSubfoldersInFolder2ActionPerformed
        switch (CMBBX_SIaC_SearchType.getSelectedIndex())
        {
            case 0: //Compare two images
                break;
            case 1: //Compare all images in one folder
                break;
            case 2: //Compare all images between two folders
                if (targetFolder[1] != null)
                {
                    countImages(false);
                }
        }
    }//GEN-LAST:event_CHKBX_SIaC_IncludeSubfoldersInFolder2ActionPerformed
    
    // === === === OTHER === === ===
    public static void main(String args[]) {
        //Attempt to set the look and feel to the system default.
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        { System.out.println("Unable to import system default look and feel."); }
                
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DIR_Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_CompareInfo_Cancel;
    private javax.swing.JButton BTN_CompareInfo_ChangeImage1;
    private javax.swing.JButton BTN_CompareInfo_ChangeImage2;
    private javax.swing.JButton BTN_CompareInfo_Skip;
    private javax.swing.JButton BTN_SIaC_Compare;
    private javax.swing.JButton BTN_SIaC_Data1;
    private javax.swing.JButton BTN_SIaC_Data2;
    private javax.swing.JCheckBox CHKBX_SIaC_IncludeSubfoldersInFolder1;
    private javax.swing.JCheckBox CHKBX_SIaC_IncludeSubfoldersInFolder2;
    private javax.swing.JCheckBox CHKBX_Settings_RepeatNotificationSound;
    private javax.swing.JCheckBox CHKBX_Settings_ShowCompareDetails;
    private javax.swing.JCheckBox CHKBX_Settings_SoundNotifications;
    private javax.swing.JComboBox<String> CMBBX_SIaC_SearchType;
    private javax.swing.Box.Filler Filler_CompareInfo;
    private javax.swing.JPanel JPNL_SIaC;
    private javax.swing.JProgressBar JPRGSBR_Choice_TotalProgress;
    private javax.swing.JLabel LBL_Choice_DisplayPercentSimilar;
    private javax.swing.JLabel LBL_CompareInfo_ComparisonType;
    private javax.swing.JLabel LBL_CompareInfo_FileType1;
    private javax.swing.JLabel LBL_CompareInfo_FileType2;
    private javax.swing.JLabel LBL_CompareInfo_IMG1Info;
    private javax.swing.JLabel LBL_CompareInfo_IMG2Info;
    private javax.swing.JLabel LBL_CompareInfo_IMGFilePath1;
    private javax.swing.JLabel LBL_CompareInfo_IMGFilePath2;
    private javax.swing.JLabel LBL_CompareInfo_IMGFileSize1;
    private javax.swing.JLabel LBL_CompareInfo_IMGFileSize2;
    private javax.swing.JLabel LBL_CompareInfo_IMGName1;
    private javax.swing.JLabel LBL_CompareInfo_IMGName2;
    private javax.swing.JLabel LBL_CompareInfo_IMGParentFolder1;
    private javax.swing.JLabel LBL_CompareInfo_IMGParentFolder2;
    private javax.swing.JLabel LBL_CompareInfo_ImageSize1;
    private javax.swing.JLabel LBL_CompareInfo_ImageSize2;
    private javax.swing.JLabel LBL_CompareInfo_NumberOfFilesInF1;
    private javax.swing.JLabel LBL_CompareInfo_NumberOfFilesInF2;
    private javax.swing.JLabel LBL_CompareInfo_TaskDetails;
    private javax.swing.JLabel LBL_IMG_AbsoluteDifferences;
    private javax.swing.JLabel LBL_IMG_Image1;
    private javax.swing.JLabel LBL_IMG_Image2;
    private javax.swing.JLabel LBL_IMG_SubtractedDifferences;
    private javax.swing.JLabel LBL_SIaC_DataHeader1;
    private javax.swing.JLabel LBL_SIaC_DataHeader2;
    private javax.swing.JLabel LBL_SIaC_DataName1;
    private javax.swing.JLabel LBL_SIaC_DataName2;
    private javax.swing.JLabel LBL_SIaC_DataPath1;
    private javax.swing.JLabel LBL_SIaC_DataPath2;
    private javax.swing.JLabel LBL_SIaC_ImageCount1;
    private javax.swing.JLabel LBL_SIaC_ImageCount2;
    private javax.swing.JLabel LBL_Settings_SimilarityThresholdInstructionLabel;
    private javax.swing.JPanel PNL_CompareInfo;
    private javax.swing.JPanel PNL_Settings;
    private javax.swing.JScrollPane SCRLPN_HighlightedDifferences;
    private javax.swing.JScrollPane SCRLPN_Image1;
    private javax.swing.JScrollPane SCRLPN_Image2;
    private javax.swing.JScrollPane SCRLPN_SubtractedDifferences;
    private javax.swing.Box.Filler SIaC_Filler;
    private javax.swing.JSlider SLDR_MinimumSimilarityThreshold;
    private javax.swing.JTabbedPane TBDPN_Images;
    private javax.swing.JTabbedPane TBDPN_UserInput;
    // End of variables declaration//GEN-END:variables
}