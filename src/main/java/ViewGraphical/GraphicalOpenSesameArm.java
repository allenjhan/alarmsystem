package ViewGraphical; /**
 * Created by ahan on 6/1/17.
 */
import Model.CurrentSystem;

import javax.swing.*;
import java.awt.event.*;

public class GraphicalOpenSesameArm extends GraphicalOpenSesame {

    /**
     * The constructor of the password validation window when "Arm Sensors" is pressed on the main window
     * @param myView the view object of the main window of the program
     */
    public GraphicalOpenSesameArm(GraphicalArmSystem myView){
        super(myView);
    }

    /**
     * the "done" button's ActionListener method
     * @param password the password of the window
     */
    @Override
    public void doneButtonActionPerformed(String password){
        if(account.getPassword().equals(password)){
            myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING));
            GraphicalArmOptions newView = new GraphicalArmOptions(myView);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
