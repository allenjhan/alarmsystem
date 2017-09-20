package ViewGraphical;

/**
 * Created by ahan on 6/6/17.
 */
import Model.CurrentSystem;

import javax.swing.*;
import java.awt.event.*;

public class GraphicalOpenSesamePassword extends GraphicalOpenSesame {

    /**
     * The constructor for the password validation screen when "Set Password" button is pressed on the main window
     * @param myView the view object of the main window of the program
     */
    public GraphicalOpenSesamePassword(GraphicalArmSystem myView){
        super(myView);
    }

    /**
     * The "done" button's ActionListener's method
     * @param password the password of the window
     */
    @Override
    public void doneButtonActionPerformed(String password){
        GraphicalArmSystem mainView = myView;
        if(account.getPassword().equals(password)){
            myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING));
            GraphicalSetPasswordReset myView = new GraphicalSetPasswordReset(mainView.getMyCustomer());
            myView.finishFrame();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
