package duplicate_image_remover;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Duplicate_Image_Remover {
    public static void main(String[] args) {
        //Attempt to set the look and feel to the system default.
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        { System.out.println("Unable to import system default look and feel."); }
        
        try {
            JFrame window = new duplicate_image_remover.library.DIR_Window();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setExtendedState(JFrame.MAXIMIZED_BOTH);
            window.setVisible(true);
        } catch (Exception ex) {
            String errorMSG = "An uncaught exception has occured and caused the program to crash.\n\n";
            JOptionPane.showMessageDialog(null, errorMSG + ex.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
