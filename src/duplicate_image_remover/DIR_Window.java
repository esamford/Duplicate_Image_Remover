package duplicate_image_remover;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class DIR_Window extends javax.swing.JFrame {
    File[] targetFolder = new File[2];
    File[] imgFile = new File[2];
    ArrayList<String> nameFilter = new ArrayList<String>();
    int[] numFiles = {0, 0};
    
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
    public javax.swing.JCheckBox getCHKBX_SIaC_IncludeSubfolders() {
        return this.CHKBX_SIaC_IncludeSubfolders;
    }
    public javax.swing.JCheckBox getCHKBX_Settings_RepeatNotificationSound() {
        return this.CHKBX_Settings_RepeatNotificationSound;
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
    public javax.swing.JLabel getLBL_CompareInfo_NumberOfFiles() {
        return this.LBL_CompareInfo_NumberOfFiles;
    }
    public javax.swing.JLabel getLBL_CompareInfo_ProgressCurrent() {
        return this.LBL_CompareInfo_ProgressCurrent;
    }
    public javax.swing.JLabel getLBL_CompareInfo_ProgressMax() {
        return this.LBL_CompareInfo_ProgressMax;
    }
    public javax.swing.JLabel getLBL_CompareInfo_ProgressSplit() {
        return this.LBL_CompareInfo_ProgressSplit;
    }
    public javax.swing.JLabel getLBL_IMG_HighlightedDifferences() {
        return this.LBL_IMG_HighlightedDifferences;
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
    public javax.swing.JRadioButton getRDBTN_Settings_CompareMethodBasic() {
        return this.RDBTN_Settings_CompareMethodBasic;
    }
    public javax.swing.JRadioButton getRDBTN_Settings_CompareMethodSubtract() {
        return this.RDBTN_Settings_CompareMethodSubtract;
    }
    public javax.swing.JRadioButton getRDBTN_Settings_NameBlacklist() {
        return this.RDBTN_Settings_NameBlacklist;
    }
    public javax.swing.JRadioButton getRDBTN_Settings_NameIgnore() {
        return this.RDBTN_Settings_NameIgnore;
    }
    public javax.swing.JRadioButton getRDBTN_Settings_NameWhitelist() {
        return this.RDBTN_Settings_NameWhitelist;
    }
    public javax.swing.JSlider getSLDR_MinimumSimilarityThreshold() {
        return this.SLDR_MinimumSimilarityThreshold;
    }
    public javax.swing.JTabbedPane getTBDPN_UserInput() {
        return this.TBDPN_UserInput;
    }
    public javax.swing.JTextField getTXTBX_Settings_TextInName() {
        return this.TXTBX_Settings_TextInName;
    }
    public boolean getIncludeSubfolders() {
        System.out.println("Getting boolean for includeSubfolders: ");
        System.out.println(this.CHKBX_SIaC_IncludeSubfolders == null);
        return this.CHKBX_SIaC_IncludeSubfolders.isSelected();
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
                    LBL_SIaC_ImageCount1.setText("Counting images...");
                    
                    UpdateLBLImageCount countFiles = new UpdateLBLImageCount();
                    countFiles.setLabel(LBL_SIaC_ImageCount1);
                    countFiles.setFolder(targetFolder[0]);
                    countFiles.setIncludeSubfolders(this.CHKBX_SIaC_IncludeSubfolders.isSelected());
                    countFiles.setPrefix("Number of images: ");
                    new Thread(countFiles).start();
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
                    
                    UpdateLBLImageCount countFiles = new UpdateLBLImageCount();
                    countFiles.setLabel(LBL_SIaC_ImageCount1);
                    countFiles.setFolder(targetFolder[0]);
                    countFiles.setIncludeSubfolders(this.CHKBX_SIaC_IncludeSubfolders.isSelected());
                    countFiles.setPrefix("Number of images: ");
                    new Thread(countFiles).start();
                }
                if (targetFolder[1] != null)
                {
                    LBL_SIaC_DataName2.setText("Name: " + targetFolder[1].getName());
                    LBL_SIaC_DataName2.setToolTipText(targetFolder[1].getName());
                    LBL_SIaC_DataPath2.setText("Folder path: " + targetFolder[1].getAbsolutePath());
                    LBL_SIaC_DataPath2.setToolTipText(targetFolder[1].getAbsolutePath());
                    LBL_SIaC_ImageCount2.setText("Counting images...");
                    
                    UpdateLBLImageCount countFiles = new UpdateLBLImageCount();
                    countFiles.setLabel(LBL_SIaC_ImageCount2);
                    countFiles.setFolder(targetFolder[1]);
                    countFiles.setIncludeSubfolders(this.CHKBX_SIaC_IncludeSubfolders.isSelected());
                    countFiles.setPrefix("Number of images: ");
                    new Thread(countFiles).start();
                }
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
        return validate.isValidExtension(checkFile);
    }
    private int countFilesInFolder(File directory) {
        if (!directory.isDirectory()) { return 0; }
        
        int counter = 0;
        for (File newFile : directory.listFiles())
        {
            if (newFile.isDirectory() && this.CHKBX_SIaC_IncludeSubfolders.isSelected())
            { counter += countFilesInFolder(newFile); }
            else if (!newFile.isDirectory() && checkImageValidity(newFile))
            { counter++; }
        }
        return counter;
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
        LBL_IMG_HighlightedDifferences.setIcon(new ImageIcon(blankImage));
        LBL_IMG_SubtractedDifferences.setIcon(new ImageIcon(blankImage));
    }
    
    // === === === GENERATED CODE === === ===
    
    public DIR_Window() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        LBL_CompareInfo_ProgressCurrent.setText(" ");
        LBL_CompareInfo_ProgressMax.setText(" ");
        LBL_CompareInfo_ProgressSplit.setText(" ");
        
        this.BTNGRP_Settings_NamePrefs.add(this.RDBTN_Settings_NameIgnore);
        this.BTNGRP_Settings_NamePrefs.add(this.RDBTN_Settings_NameWhitelist);
        this.BTNGRP_Settings_NamePrefs.add(this.RDBTN_Settings_NameBlacklist);
        
        this.BTNGRP_Settings_CompareMethod.add(this.RDBTN_Settings_CompareMethodBasic);
        this.BTNGRP_Settings_CompareMethod.add(this.RDBTN_Settings_CompareMethodSubtract);
        
        LBL_SIaC_DataHeader1.setText("Image One Information:");
        LBL_SIaC_DataName2.setText("Name:");
        LBL_SIaC_DataPath1.setText("File path:");
        LBL_SIaC_ImageCount1.setVisible(false);
        LBL_SIaC_DataHeader2.setText("Image Two Information:");
        LBL_SIaC_DataHeader2.setVisible(true);
        LBL_SIaC_DataName2.setText("Name:");
        LBL_SIaC_DataName2.setVisible(true);
        LBL_SIaC_DataPath2.setText("File path:");
        LBL_SIaC_DataPath2.setVisible(true);
        LBL_SIaC_ImageCount2.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BTNGRP_Settings_NamePrefs = new javax.swing.ButtonGroup();
        BTNGRP_Settings_CompareMethod = new javax.swing.ButtonGroup();
        TBDPN_Images = new javax.swing.JTabbedPane();
        SCRLPN_Image1 = new javax.swing.JScrollPane();
        LBL_IMG_Image1 = new javax.swing.JLabel();
        SCRLPN_Image2 = new javax.swing.JScrollPane();
        LBL_IMG_Image2 = new javax.swing.JLabel();
        SCRLPN_HighlightedDifferences = new javax.swing.JScrollPane();
        LBL_IMG_HighlightedDifferences = new javax.swing.JLabel();
        SCRLPN_SubtractedDifferences = new javax.swing.JScrollPane();
        LBL_IMG_SubtractedDifferences = new javax.swing.JLabel();
        TBDPN_UserInput = new javax.swing.JTabbedPane();
        JPNL_SIaC = new javax.swing.JPanel();
        CMBBX_SIaC_SearchType = new javax.swing.JComboBox<>();
        CHKBX_SIaC_IncludeSubfolders = new javax.swing.JCheckBox();
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
        LBL_CompareInfo_ProgressCurrent = new javax.swing.JLabel();
        LBL_CompareInfo_ProgressMax = new javax.swing.JLabel();
        LBL_CompareInfo_ProgressSplit = new javax.swing.JLabel();
        LBL_CompareInfo_NumberOfFiles = new javax.swing.JLabel();
        LBL_CompareInfo_FileType2 = new javax.swing.JLabel();
        LBL_CompareInfo_FileType1 = new javax.swing.JLabel();
        PNL_Settings = new javax.swing.JPanel();
        SLDR_MinimumSimilarityThreshold = new javax.swing.JSlider();
        TXTBX_Settings_TextInName = new javax.swing.JTextField();
        RDBTN_Settings_NameIgnore = new javax.swing.JRadioButton();
        RDBTN_Settings_NameWhitelist = new javax.swing.JRadioButton();
        RDBTN_Settings_NameBlacklist = new javax.swing.JRadioButton();
        RDBTN_Settings_CompareMethodBasic = new javax.swing.JRadioButton();
        RDBTN_Settings_CompareMethodSubtract = new javax.swing.JRadioButton();
        CHKBX_Settings_SoundNotifications = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        CHKBX_Settings_RepeatNotificationSound = new javax.swing.JCheckBox();

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

        LBL_IMG_HighlightedDifferences.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SCRLPN_HighlightedDifferences.setViewportView(LBL_IMG_HighlightedDifferences);

        TBDPN_Images.addTab("Pixel Differences", SCRLPN_HighlightedDifferences);

        LBL_IMG_SubtractedDifferences.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SCRLPN_SubtractedDifferences.setViewportView(LBL_IMG_SubtractedDifferences);

        TBDPN_Images.addTab("Subtracted Differences", SCRLPN_SubtractedDifferences);

        TBDPN_UserInput.setFocusable(false);
        TBDPN_UserInput.setName(""); // NOI18N

        JPNL_SIaC.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        CMBBX_SIaC_SearchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Compare two images", "Compare all images in one folder", "Compare all images between two folders" }));
        CMBBX_SIaC_SearchType.setFocusable(false);
        CMBBX_SIaC_SearchType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMBBX_SIaC_SearchTypeActionPerformed(evt);
            }
        });

        CHKBX_SIaC_IncludeSubfolders.setText("Include subfolders");
        CHKBX_SIaC_IncludeSubfolders.setEnabled(false);
        CHKBX_SIaC_IncludeSubfolders.setFocusable(false);
        CHKBX_SIaC_IncludeSubfolders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHKBX_SIaC_IncludeSubfoldersActionPerformed(evt);
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

        javax.swing.GroupLayout JPNL_SIaCLayout = new javax.swing.GroupLayout(JPNL_SIaC);
        JPNL_SIaC.setLayout(JPNL_SIaCLayout);
        JPNL_SIaCLayout.setHorizontalGroup(
            JPNL_SIaCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNL_SIaCLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPNL_SIaCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CMBBX_SIaC_SearchType, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTN_SIaC_Data1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTN_SIaC_Data2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTN_SIaC_Compare, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CHKBX_SIaC_IncludeSubfolders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_SIaC_DataHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_SIaC_DataHeader2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_SIaC_DataName1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_SIaC_DataPath1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_SIaC_ImageCount1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_SIaC_DataName2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_SIaC_DataPath2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_SIaC_ImageCount2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SIaC_Filler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        JPNL_SIaCLayout.setVerticalGroup(
            JPNL_SIaCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNL_SIaCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CMBBX_SIaC_SearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CHKBX_SIaC_IncludeSubfolders)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTN_SIaC_Data1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTN_SIaC_Data2)
                .addGap(18, 18, 18)
                .addComponent(LBL_SIaC_DataHeader1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_SIaC_DataName1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_SIaC_DataPath1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_SIaC_ImageCount1)
                .addGap(18, 18, 18)
                .addComponent(LBL_SIaC_DataHeader2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_SIaC_DataName2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_SIaC_DataPath2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_SIaC_ImageCount2)
                .addGap(18, 18, 18)
                .addComponent(SIaC_Filler, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTN_SIaC_Compare)
                .addContainerGap())
        );

        TBDPN_UserInput.addTab("Select Images and Compare", JPNL_SIaC);

        PNL_CompareInfo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        JPRGSBR_Choice_TotalProgress.setFocusable(false);
        JPRGSBR_Choice_TotalProgress.setRequestFocusEnabled(false);

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

        BTN_CompareInfo_Skip.setText("Skip");
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

        LBL_CompareInfo_ProgressCurrent.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        LBL_CompareInfo_ProgressCurrent.setText("CURRENT PROGRESS");

        LBL_CompareInfo_ProgressMax.setText("MAX PROGRESS");

        LBL_CompareInfo_ProgressSplit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LBL_CompareInfo_ProgressSplit.setText("/");

        LBL_CompareInfo_NumberOfFiles.setText("Number of files: ");

        LBL_CompareInfo_FileType2.setText("File type:");

        LBL_CompareInfo_FileType1.setText("File type:");

        javax.swing.GroupLayout PNL_CompareInfoLayout = new javax.swing.GroupLayout(PNL_CompareInfo);
        PNL_CompareInfo.setLayout(PNL_CompareInfoLayout);
        PNL_CompareInfoLayout.setHorizontalGroup(
            PNL_CompareInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNL_CompareInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PNL_CompareInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PNL_CompareInfoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(LBL_CompareInfo_ProgressCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LBL_CompareInfo_ProgressSplit, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LBL_CompareInfo_ProgressMax, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LBL_CompareInfo_NumberOfFiles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CompareInfo_FileType2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CompareInfo_FileType1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PNL_CompareInfoLayout.setVerticalGroup(
            PNL_CompareInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PNL_CompareInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LBL_CompareInfo_NumberOfFiles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addComponent(Filler_CompareInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTN_CompareInfo_Skip)
                .addGap(18, 18, 18)
                .addComponent(BTN_CompareInfo_ChangeImage1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTN_CompareInfo_ChangeImage2)
                .addGap(18, 18, 18)
                .addComponent(BTN_CompareInfo_Cancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PNL_CompareInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PNL_CompareInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LBL_CompareInfo_ProgressCurrent)
                        .addComponent(LBL_CompareInfo_ProgressMax))
                    .addComponent(LBL_CompareInfo_ProgressSplit, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPRGSBR_Choice_TotalProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        TBDPN_UserInput.addTab("Compare Info", PNL_CompareInfo);

        PNL_Settings.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        SLDR_MinimumSimilarityThreshold.setMajorTickSpacing(10);
        SLDR_MinimumSimilarityThreshold.setMinorTickSpacing(5);
        SLDR_MinimumSimilarityThreshold.setPaintLabels(true);
        SLDR_MinimumSimilarityThreshold.setPaintTicks(true);
        SLDR_MinimumSimilarityThreshold.setSnapToTicks(true);
        SLDR_MinimumSimilarityThreshold.setToolTipText("");
        SLDR_MinimumSimilarityThreshold.setValue(90);
        SLDR_MinimumSimilarityThreshold.setFocusable(false);
        SLDR_MinimumSimilarityThreshold.setRequestFocusEnabled(false);

        TXTBX_Settings_TextInName.setEnabled(false);
        TXTBX_Settings_TextInName.setFocusable(false);
        TXTBX_Settings_TextInName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTBX_Settings_TextInNameActionPerformed(evt);
            }
        });

        RDBTN_Settings_NameIgnore.setSelected(true);
        RDBTN_Settings_NameIgnore.setText("Ignore image name.");
        RDBTN_Settings_NameIgnore.setEnabled(false);
        RDBTN_Settings_NameIgnore.setFocusable(false);
        RDBTN_Settings_NameIgnore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDBTN_Settings_NameIgnoreActionPerformed(evt);
            }
        });

        RDBTN_Settings_NameWhitelist.setText("Whitelist images with this text in their name:");
        RDBTN_Settings_NameWhitelist.setEnabled(false);
        RDBTN_Settings_NameWhitelist.setFocusable(false);
        RDBTN_Settings_NameWhitelist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDBTN_Settings_NameWhitelistActionPerformed(evt);
            }
        });

        RDBTN_Settings_NameBlacklist.setText("Blacklist images with this text in their name:");
        RDBTN_Settings_NameBlacklist.setEnabled(false);
        RDBTN_Settings_NameBlacklist.setFocusable(false);
        RDBTN_Settings_NameBlacklist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDBTN_Settings_NameBlacklistActionPerformed(evt);
            }
        });

        RDBTN_Settings_CompareMethodBasic.setText("Calculate percentage based on pixel matches.");
        RDBTN_Settings_CompareMethodBasic.setFocusable(false);

        RDBTN_Settings_CompareMethodSubtract.setSelected(true);
        RDBTN_Settings_CompareMethodSubtract.setText("Calculate percentage by subtracting pixel colors.");
        RDBTN_Settings_CompareMethodSubtract.setToolTipText("");
        RDBTN_Settings_CompareMethodSubtract.setFocusable(false);

        CHKBX_Settings_SoundNotifications.setSelected(true);
        CHKBX_Settings_SoundNotifications.setText("Play a sound when a potential match is found.");
        CHKBX_Settings_SoundNotifications.setFocusable(false);
        CHKBX_Settings_SoundNotifications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHKBX_Settings_SoundNotificationsActionPerformed(evt);
            }
        });

        jLabel1.setText("Minimum percent similarity required for manual review:");

        CHKBX_Settings_RepeatNotificationSound.setText("Repeat sound every thirty seconds while waiting.");
        CHKBX_Settings_RepeatNotificationSound.setFocusable(false);
        CHKBX_Settings_RepeatNotificationSound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHKBX_Settings_RepeatNotificationSoundActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PNL_SettingsLayout = new javax.swing.GroupLayout(PNL_Settings);
        PNL_Settings.setLayout(PNL_SettingsLayout);
        PNL_SettingsLayout.setHorizontalGroup(
            PNL_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNL_SettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PNL_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SLDR_MinimumSimilarityThreshold, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RDBTN_Settings_NameIgnore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TXTBX_Settings_TextInName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(RDBTN_Settings_NameWhitelist, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RDBTN_Settings_NameBlacklist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RDBTN_Settings_CompareMethodSubtract, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RDBTN_Settings_CompareMethodBasic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CHKBX_Settings_SoundNotifications, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CHKBX_Settings_RepeatNotificationSound, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PNL_SettingsLayout.setVerticalGroup(
            PNL_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNL_SettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SLDR_MinimumSimilarityThreshold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RDBTN_Settings_CompareMethodSubtract)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RDBTN_Settings_CompareMethodBasic)
                .addGap(18, 18, 18)
                .addComponent(CHKBX_Settings_SoundNotifications)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CHKBX_Settings_RepeatNotificationSound)
                .addGap(70, 70, 70)
                .addComponent(RDBTN_Settings_NameIgnore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RDBTN_Settings_NameWhitelist)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RDBTN_Settings_NameBlacklist)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TXTBX_Settings_TextInName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(281, Short.MAX_VALUE))
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
        
        switch (CMBBX_SIaC_SearchType.getSelectedIndex())
        {
            case 0: //Compare two images
                CHKBX_SIaC_IncludeSubfolders.setEnabled(false);
                CHKBX_SIaC_IncludeSubfolders.setSelected(false);
                BTN_SIaC_Data2.setEnabled(true);
                BTN_SIaC_Data1.setText("Get image one");
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
                
                break;
            case 1: //Compare all images in one folder
                CHKBX_SIaC_IncludeSubfolders.setEnabled(true);
                BTN_SIaC_Data2.setEnabled(false);
                BTN_SIaC_Data1.setText("Get main folder");
                BTN_SIaC_Data2.setText(" ");
                
                LBL_SIaC_DataHeader1.setText("Folder Information:");
                LBL_SIaC_DataName1.setText("Name:");
                LBL_SIaC_DataPath1.setText("Folder path:");
                LBL_SIaC_ImageCount1.setText("Number of images:");
                LBL_SIaC_ImageCount1.setVisible(true);
                LBL_SIaC_DataHeader2.setVisible(false);
                LBL_SIaC_DataName2.setVisible(false);
                LBL_SIaC_DataPath2.setVisible(false);
                LBL_SIaC_ImageCount2.setVisible(false);
                break;
            case 2: //Compare all images between two folders
                CHKBX_SIaC_IncludeSubfolders.setEnabled(true);
                BTN_SIaC_Data2.setEnabled(true);
                BTN_SIaC_Data1.setText("Get folder one");
                BTN_SIaC_Data2.setText("Get folder two");
                
                LBL_SIaC_DataHeader1.setText("Folder One Information:");
                LBL_SIaC_DataName1.setText("Name:");
                LBL_SIaC_DataPath1.setText("Folder path:");
                LBL_SIaC_ImageCount1.setText("Number of images:");
                LBL_SIaC_ImageCount1.setVisible(true);
                LBL_SIaC_DataHeader2.setText("Folder Two Information:");
                LBL_SIaC_DataHeader2.setVisible(true);
                LBL_SIaC_DataName2.setText("Name:");
                LBL_SIaC_DataName2.setVisible(true);
                LBL_SIaC_DataPath2.setText("Folder path:");
                LBL_SIaC_DataPath2.setVisible(true);
                LBL_SIaC_ImageCount2.setText("Number of images:");
                LBL_SIaC_ImageCount2.setVisible(true);
        }
    }//GEN-LAST:event_CMBBX_SIaC_SearchTypeActionPerformed
    private void RDBTN_Settings_NameIgnoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDBTN_Settings_NameIgnoreActionPerformed
        TXTBX_Settings_TextInName.setEnabled(false);
        TXTBX_Settings_TextInName.setText("");
    }//GEN-LAST:event_RDBTN_Settings_NameIgnoreActionPerformed
    private void RDBTN_Settings_NameWhitelistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDBTN_Settings_NameWhitelistActionPerformed
        TXTBX_Settings_TextInName.setEnabled(true);
    }//GEN-LAST:event_RDBTN_Settings_NameWhitelistActionPerformed
    private void RDBTN_Settings_NameBlacklistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDBTN_Settings_NameBlacklistActionPerformed
        TXTBX_Settings_TextInName.setEnabled(true);
    }//GEN-LAST:event_RDBTN_Settings_NameBlacklistActionPerformed
    private void BTN_SIaC_Data1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SIaC_Data1ActionPerformed
        JFileChooser getData = new JFileChooser();
        this.numFiles[0] = 0;
        switch (CMBBX_SIaC_SearchType.getSelectedIndex())
        {
            case 0: //Compare two images
                getData.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (getData.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
                {
                    if (checkImageValidity(getData.getSelectedFile()))
                    {
                        imgFile[0] = getData.getSelectedFile();
                        try {
                            CompareImages importIMG = new CompareImages();
                            BufferedImage imgBuff = importIMG.importImage(imgFile[0]);
                            LBL_IMG_Image1.setIcon(new ImageIcon(imgBuff));
                            this.numFiles[0] = 1;
                        } catch (IOException ex) {
                            imgFile[0] = null;
                            clearDisplayedImages();
                            String errorMSG = "Unable to import the image.\nError message: ";
                            JOptionPane.showMessageDialog(this, errorMSG + ex.getMessage(), "Unable to Read Image", JOptionPane.ERROR_MESSAGE);
                        }
                        TBDPN_Images.setSelectedIndex(0);
                    }
                    else
                    {
                        String errorMSG = "The selected file is not a valid image.";
                        JOptionPane.showMessageDialog(this, errorMSG, "Invalid File Type", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case 1: //Compare all images in one folder                
            case 2: //Compare all images between two folders
                getData.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);                
                if (getData.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
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
                        else
                        {
                            this.numFiles[0] = countFilesInFolder(targetFolder[0]);
                        }
                    }
                }
        }
        updateSIaCFolderInfoLabels();
        checkIfReadyToCompare();
    }//GEN-LAST:event_BTN_SIaC_Data1ActionPerformed
    private void BTN_SIaC_Data2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SIaC_Data2ActionPerformed
        JFileChooser getData = new JFileChooser();
        this.numFiles[1] = 0;
        switch (CMBBX_SIaC_SearchType.getSelectedIndex())
        {
            case 0: //Compare two images
                getData.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (getData.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
                {
                    if (checkImageValidity(getData.getSelectedFile()))
                    {
                        imgFile[1] = getData.getSelectedFile();
                        try {
                            CompareImages importIMG = new CompareImages();
                            BufferedImage imgBuff = importIMG.importImage(imgFile[1]);
                            LBL_IMG_Image2.setIcon(new ImageIcon(imgBuff));
                            this.numFiles[1] = 1;
                        } catch (IOException ex) {
                            imgFile[1] = null;
                            clearDisplayedImages();
                            String errorMSG = "Unable to import the image.\nError message: ";
                            JOptionPane.showMessageDialog(this, errorMSG + ex.getMessage(), "Unable to Read Image", JOptionPane.ERROR_MESSAGE);
                        }
                        TBDPN_Images.setSelectedIndex(1);
                    }
                    else
                    {
                        String errorMSG = "The selected file is not a valid image.";
                        JOptionPane.showMessageDialog(this, errorMSG, "Invalid File Type", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case 1: //Compare all images in one folder
                break;
            case 2: //Compare all images between two folders
                getData.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);                
                if (getData.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
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
                        else
                        {
                            this.numFiles[1] = countFilesInFolder(targetFolder[1]);
                        }
                    }
                }
        }
        updateSIaCFolderInfoLabels();
        checkIfReadyToCompare();
    }//GEN-LAST:event_BTN_SIaC_Data2ActionPerformed
    private void CHKBX_SIaC_IncludeSubfoldersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CHKBX_SIaC_IncludeSubfoldersActionPerformed
        switch (CMBBX_SIaC_SearchType.getSelectedIndex())
        {
            case 0: //Compare two images
                break;
            case 1: //Compare all images in one folder
                if (targetFolder[0] != null)
                {
                    LBL_SIaC_ImageCount1.setText("Counting images...");
                    UpdateLBLImageCount countFiles = new UpdateLBLImageCount();
                    countFiles.setLabel(LBL_SIaC_ImageCount1);
                    countFiles.setFolder(targetFolder[0]);
                    countFiles.setIncludeSubfolders(this.CHKBX_SIaC_IncludeSubfolders.isSelected());
                    countFiles.setPrefix("Number of images: ");
                    new Thread(countFiles).start();
                }
                break;
            case 2: //Compare all images between two folders
                if (targetFolder[0] != null)
                {
                    LBL_SIaC_ImageCount1.setText("Counting images...");
                    UpdateLBLImageCount countFiles = new UpdateLBLImageCount();
                    countFiles.setLabel(LBL_SIaC_ImageCount1);
                    countFiles.setFolder(targetFolder[0]);
                    countFiles.setIncludeSubfolders(this.CHKBX_SIaC_IncludeSubfolders.isSelected());
                    countFiles.setPrefix("Number of images: ");
                    new Thread(countFiles).start();
                }
                if (targetFolder[1] != null)
                {
                    LBL_SIaC_ImageCount2.setText("Counting images...");
                    UpdateLBLImageCount countFiles = new UpdateLBLImageCount();
                    countFiles.setLabel(LBL_SIaC_ImageCount2);
                    countFiles.setFolder(targetFolder[1]);
                    countFiles.setIncludeSubfolders(this.CHKBX_SIaC_IncludeSubfolders.isSelected());
                    countFiles.setPrefix("Number of images: ");
                    new Thread(countFiles).start();
                }
        }
    }//GEN-LAST:event_CHKBX_SIaC_IncludeSubfoldersActionPerformed
    private void BTN_CompareInfo_SkipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CompareInfo_SkipActionPerformed
    }//GEN-LAST:event_BTN_CompareInfo_SkipActionPerformed
    private void BTN_CompareInfo_ChangeImage1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CompareInfo_ChangeImage1ActionPerformed
    }//GEN-LAST:event_BTN_CompareInfo_ChangeImage1ActionPerformed
    private void BTN_CompareInfo_ChangeImage2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CompareInfo_ChangeImage2ActionPerformed
    }//GEN-LAST:event_BTN_CompareInfo_ChangeImage2ActionPerformed
    private void BTN_CompareInfo_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CompareInfo_CancelActionPerformed
    }//GEN-LAST:event_BTN_CompareInfo_CancelActionPerformed
    private void TXTBX_Settings_TextInNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTBX_Settings_TextInNameActionPerformed
        nameFilter.clear();
        String tempName = "";
//        for (int x = 0; x < TXTBX_Settings_TextInName.getColumns() - 1; x++)
//        {
//            if (TXTBX_Settings_TextInName.get(x) == ",")
//            {
//                nameFilter.add(tempName);
//                
//            }
//            else
//            {
//                tempName += nameFilter.TXTBX_Settings_TextInName;
//            }
//        }
        
        
    }//GEN-LAST:event_TXTBX_Settings_TextInNameActionPerformed
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
    private javax.swing.ButtonGroup BTNGRP_Settings_CompareMethod;
    private javax.swing.ButtonGroup BTNGRP_Settings_NamePrefs;
    private javax.swing.JButton BTN_CompareInfo_Cancel;
    private javax.swing.JButton BTN_CompareInfo_ChangeImage1;
    private javax.swing.JButton BTN_CompareInfo_ChangeImage2;
    private javax.swing.JButton BTN_CompareInfo_Skip;
    private javax.swing.JButton BTN_SIaC_Compare;
    private javax.swing.JButton BTN_SIaC_Data1;
    private javax.swing.JButton BTN_SIaC_Data2;
    private javax.swing.JCheckBox CHKBX_SIaC_IncludeSubfolders;
    private javax.swing.JCheckBox CHKBX_Settings_RepeatNotificationSound;
    private javax.swing.JCheckBox CHKBX_Settings_SoundNotifications;
    private javax.swing.JComboBox<String> CMBBX_SIaC_SearchType;
    private javax.swing.Box.Filler Filler_CompareInfo;
    private javax.swing.JPanel JPNL_SIaC;
    private javax.swing.JProgressBar JPRGSBR_Choice_TotalProgress;
    private javax.swing.JLabel LBL_Choice_DisplayPercentSimilar;
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
    private javax.swing.JLabel LBL_CompareInfo_NumberOfFiles;
    private javax.swing.JLabel LBL_CompareInfo_ProgressCurrent;
    private javax.swing.JLabel LBL_CompareInfo_ProgressMax;
    private javax.swing.JLabel LBL_CompareInfo_ProgressSplit;
    private javax.swing.JLabel LBL_IMG_HighlightedDifferences;
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
    private javax.swing.JPanel PNL_CompareInfo;
    private javax.swing.JPanel PNL_Settings;
    private javax.swing.JRadioButton RDBTN_Settings_CompareMethodBasic;
    private javax.swing.JRadioButton RDBTN_Settings_CompareMethodSubtract;
    private javax.swing.JRadioButton RDBTN_Settings_NameBlacklist;
    private javax.swing.JRadioButton RDBTN_Settings_NameIgnore;
    private javax.swing.JRadioButton RDBTN_Settings_NameWhitelist;
    private javax.swing.JScrollPane SCRLPN_HighlightedDifferences;
    private javax.swing.JScrollPane SCRLPN_Image1;
    private javax.swing.JScrollPane SCRLPN_Image2;
    private javax.swing.JScrollPane SCRLPN_SubtractedDifferences;
    private javax.swing.Box.Filler SIaC_Filler;
    private javax.swing.JSlider SLDR_MinimumSimilarityThreshold;
    private javax.swing.JTabbedPane TBDPN_Images;
    private javax.swing.JTabbedPane TBDPN_UserInput;
    private javax.swing.JTextField TXTBX_Settings_TextInName;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}