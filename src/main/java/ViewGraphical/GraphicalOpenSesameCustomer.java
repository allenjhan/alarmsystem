package ViewGraphical;

/**
 * Created by ahan on 6/7/17.
 */

import javax.swing.*;
import java.awt.event.*;

public class GraphicalOpenSesameCustomer extends GraphicalOpenSesame{

    /**
     * The the constructor of password validation screen when the "Customer Data" button is pressed on the main window
     * @param myView
     */
    public GraphicalOpenSesameCustomer(GraphicalArmSystem myView){
        super(myView);
    }

    /**
     * The ActionListener's method for the "done" button.
     * @param password the password of the window
     */
    public void doneButtonActionPerformed( String password){
        if(account.getPassword().equals(password)){
            myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING));
            GraphicalCustomer graphicalCustomer = new GraphicalCustomer(myView.getMyCustomer());
            graphicalCustomer.finishFrame();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
