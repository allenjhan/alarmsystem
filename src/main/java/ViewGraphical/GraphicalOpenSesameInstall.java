package ViewGraphical;


/**
 * Created by ahan on 6/6/17.
 */

import javax.swing.*;
import java.awt.event.*;

public class GraphicalOpenSesameInstall extends GraphicalOpenSesame{

    /**
     * The constructor for the password validation screen after "Install Sensors" button is pressed on the main window
     * @param myView the view object of the main window of the system
     */
    public GraphicalOpenSesameInstall(GraphicalArmSystem myView){
        super(myView);
    }

    /**
     * The "done" button for which an ActionListener is here described
     * @param password the password of the window
     */
    public void doneButtonActionPerformed(String password){
        if(account.getPassword().equals(password)){
            myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING));
            GraphicalInstallSystem myInstallSystem = new GraphicalInstallSystem(currentSystem, myView.getMyInstallationManager(),
                    myView.getMyCustomer(), myView);
            myInstallSystem.finishFrame();

        } else {
            JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
