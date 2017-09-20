package ViewGraphical;

/**
 * Created by ahan on 6/6/17.
 */
import javax.swing.*;
import java.awt.event.*;

public class GraphicalOpenSesameMalfunction extends GraphicalOpenSesame{

    /**
     * The constructor for the password validation screen when "Set Malfunction" button is pressed on the main window
     * @param myView the view object of the main window of the system
     */
    public GraphicalOpenSesameMalfunction(GraphicalArmSystem myView){
        super(myView);
    }

    /**
     * The "done" button's ActionListener's method
     * @param password the password of the window
     */
    public void doneButtonActionPerformed( String password){
        if( account.getPassword().equals(password)){
            myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING));
            GraphicalSetMalfunction myMalfunctions = new GraphicalSetMalfunction(currentSystem,
                    myView.getMyInstallationManager(), myView.getMyCustomer(), myView);
            myMalfunctions.finishFrame();

        } else {
            JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
