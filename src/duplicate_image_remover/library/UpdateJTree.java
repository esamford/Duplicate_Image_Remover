package duplicate_image_remover.library;

import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class UpdateJTree implements Runnable {
    DIR_Window parentFrame;
    
    File[] targetImage = new File[2];
    File[] targetFolder = new File[2];
    
    public enum SearchType {
        TWO_IMAGES,
        ONE_FOLDER,
        TWO_FOLDERS
    }
    
    // === === === SETTERS === === ===
        
    public void setParentWindow(DIR_Window newWindow) {
        this.parentFrame = newWindow;
    }
    
    // === === === OTHER FUNCTIONS === === ===
    
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
            if (newFile.isDirectory() && this.parentFrame.getIncludeSubfolders())
            { counter += countFilesInFolder(newFile); }
            else if (!newFile.isDirectory() && checkImageValidity(newFile))
            { counter++; }
        }
        return counter;
    }
    
    private DefaultTreeModel getTreeModel(File directory, boolean includeSubfolders) //http://www.java2s.com/Tutorials/Java/Swing_How_to/JTree/Add_windows_directory_structure_in_JTree.htm
    {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(directory);
        DefaultTreeModel newTreeModel = new DefaultTreeModel(node);
        for (File newFile : directory.listFiles())
        {
            if (newFile.isDirectory() && includeSubfolders)
            {
                node.add(new DefaultMutableTreeNode(newFile.getName()));
                node.add(new DefaultMutableTreeNode(getTreeModel(newFile, true)));
            }
            else if (!newFile.isDirectory() && checkImageValidity(newFile))
            {
                node.add(new DefaultMutableTreeNode(newFile.getName()));
            }
        }
        return newTreeModel;
    }

    @Override
    public void run()
    {
        if (parentFrame != null)
        {
            try {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(null);
                DefaultTreeModel treeModel = new DefaultTreeModel(node);
                
                switch (this.parentFrame.getCMBBX_SIaC_SearchType().getSelectedIndex())
                {
                    case 0: //Compare two images
                        this.targetImage = this.parentFrame.getImgFiles();
                        
                        if (this.targetImage[0] != null) { node.add(new DefaultMutableTreeNode(targetImage[0])); }
                        if (this.targetImage[1] != null) { node.add(new DefaultMutableTreeNode(targetImage[1])); }
                        
                        break;
                    case 1: //Compare one folder
                        this.targetFolder = this.parentFrame.getTargetFolders();
                        if (this.targetFolder[0] != null)
                        {
                            node.add(new DefaultMutableTreeNode(getTreeModel(this.targetFolder[0], this.parentFrame.getIncludeSubfolders())));
                        }
                        break;
                    case 2: //Compare two folders
                        this.targetFolder = this.parentFrame.getTargetFolders();
                        if (this.targetFolder[0] != null && this.targetFolder[1] != null)
                        {
                            node.add(new DefaultMutableTreeNode(getTreeModel(this.targetFolder[0], this.parentFrame.getIncludeSubfolders())));
                            node.add(new DefaultMutableTreeNode(getTreeModel(this.targetFolder[1], this.parentFrame.getIncludeSubfolders())));
                        }
                }
                //DefaultTreeModel newTreeModel = new DefaultTreeModel(node);
            } catch (Exception ex) { System.out.println(ex.getMessage()); }
            System.gc();
        }
        System.out.println("FINISHED WITH THE UPDATEJTREE THREAD\n\n\n\n\n");
    }
}