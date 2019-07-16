package duplicate_image_remover;

import java.io.File;

public class UpdateLBLImageCount implements Runnable {
    javax.swing.JLabel label;
    File folder;
    boolean includeSubfolders = false;
    String prefix = "";
    
    public void setLabel(javax.swing.JLabel newLabel) {
        this.label = newLabel;
    }
    public void setFolder(File newFolder) {
        this.folder = newFolder;
    }
    public void setIncludeSubfolders(boolean includeSubfolders) {
        this.includeSubfolders = includeSubfolders;
    }
    public void setPrefix(String newPrefix) {
        this.prefix = newPrefix;
    }
    
    private boolean checkImageValidity(File checkFile) {
        if (!checkFile.exists()) { return false; }        
        CompareImages validate = new CompareImages();        
        return validate.checkIfValidImage(checkFile);
    }
    private int countFilesInFolder(File directory) throws InterruptedException {
        if (!directory.isDirectory()) { return 0; }
        
        int counter = 0;
        for (File newFile : directory.listFiles())
        {
            if (Thread.currentThread().isInterrupted())
            {
                throw new InterruptedException();
            }
            
            if (newFile.isDirectory() && includeSubfolders)
            { counter += countFilesInFolder(newFile); }
            else if (!newFile.isDirectory() && checkImageValidity(newFile))
            { counter++; }
        }
        return counter;
    }
    
    private void validateInput() throws Exception {
        if (folder == null) { throw new Exception("The folder to count images from is null."); }
        if (!folder.exists()) { throw new Exception("The folder to count images from does not exist."); }
        if (!folder.isDirectory()) { throw new Exception("The file provided is not a folder."); }
        if (label == null) { throw new Exception("The label provided is null."); }
    }
    
    @Override
    public void run() {
        try {
            validateInput();
            label.setText(prefix + String.format("%,d", countFilesInFolder(folder)));
        }
        catch (InterruptedException ex) {
            System.out.println("Image counting thread interrupted.");
        }
        catch (Exception ex) {
            if (label != null)
            {
                label.setText(prefix + "Failed to get image count.");
            }
            System.out.println("Failed to count images in folder to display image count. Error message: " + ex.getMessage());
        }
    }
}