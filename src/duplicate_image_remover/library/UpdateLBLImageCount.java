package duplicate_image_remover.library;

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
        return validate.isValidExtension(checkFile);
    }
    private int countFilesInFolder(File directory) {
        if (!directory.isDirectory()) { return 0; }
        
        int counter = 0;
        for (File newFile : directory.listFiles())
        {
            if (newFile.isDirectory() && includeSubfolders)
            { counter += countFilesInFolder(newFile); }
            else if (!newFile.isDirectory() && checkImageValidity(newFile))
            { counter++; }
        }
        return counter;
    }
    
    private void validateInput() throws Exception {
        if (folder == null) { throw new Exception(); }
        if (!folder.exists()) { throw new Exception(); }
        if (!folder.isDirectory()) { throw new Exception(); }
        if (label == null) { throw new Exception(); }
    }
    
    @Override
    public void run() {
        try {
            validateInput();
            label.setText(prefix + String.format("%,d", countFilesInFolder(folder)));
        }
        catch (Exception ex) {
            label.setText(prefix + "Failed to get image count.");
        }
    }
}
