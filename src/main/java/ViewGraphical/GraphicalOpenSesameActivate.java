package ViewGraphical;

/**
 * Created by ahan on 6/6/17.
 */
import javax.swing.*;
import java.awt.event.*;

public class GraphicalOpenSesameActivate extends GraphicalOpenSesame{

    /**
     * The constructor for the password validation screen after pressing "Activate Sensors" on the main window.
     * @param myView the view object of the main window of the program
     */
    public GraphicalOpenSesameActivate(GraphicalArmSystem myView){
        super(myView);
    }

    /**
     * The ActionListener method for the "done" button of the window.
     * @param password the password of the window
     */
    public void doneButtonActionPerformed(String password){
        if(account.getPassword().equals(password)){
            myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING));
            GraphicalActivateSystem myActivateSystem = new GraphicalActivateSystem(currentSystem,
                    myView.getMyInstallationManager(), myView.getMyCustomer(), myView);
            myActivateSystem.finishFrame();

        } else {
            JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
