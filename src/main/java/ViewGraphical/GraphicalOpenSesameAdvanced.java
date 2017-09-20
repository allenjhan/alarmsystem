package ViewGraphical;

/**
 * Created by ahan on 6/6/17.
 */
import javax.swing.*;
import java.awt.event.*;

public class GraphicalOpenSesameAdvanced extends GraphicalOpenSesame{
    private GraphicalAdvancedArmingMenu myMenu;

    /**
     * The constructor for the password validation window of the Advanced Arming Menu.
     * @param myView the view object of the main window of the program
     */
    public GraphicalOpenSesameAdvanced(GraphicalArmSystem myView){
        super(myView);
        this.myMenu = myMenu;
    }

    /**
     * The "done" button's ActionListener method
     * @param password the password of the window
     */
    public void doneButtonActionPerformed(String password){
        if( account.getPassword().equals(password)){
            myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING));
            //myMenu.createMenu();
            GraphicalAdvancedArmingMenu newMenu = new GraphicalAdvancedArmingMenu(myView);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
