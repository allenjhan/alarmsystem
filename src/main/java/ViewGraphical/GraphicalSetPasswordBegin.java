package ViewGraphical;

/**
 * Created by ahan on 6/6/17.
 */

import Model.*;
import javax.swing.*;
import java.awt.event.*;

public class GraphicalSetPasswordBegin extends GraphicalSetPassword {

    /**
     * The constructor for the set password screen that appears at the start of the program.
     * @param myCustomer customer data
     */
    public GraphicalSetPasswordBegin(Customer myCustomer) {
        super(myCustomer);

    }

    /**
     * the "done" button's ActionListener's method
     * @param password password to be set
     */
    @Override
    public void doneButtonActionPerformed(String password){
        if (myCustomer.getAccount().setPassword(password) == true) {
            GraphicalArmSystem myView = new GraphicalArmSystem(CurrentSystem.SECURITYSYSTEM, new InstallationManager(), myCustomer);
            myView.finishFrame();
            myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING));
        } else
            JOptionPane.showMessageDialog(null, "No Whitespace Characters. Must be at least one character long.", "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GraphicalSetPasswordBegin testView = new GraphicalSetPasswordBegin(new Customer("001", "Santa Clara University",
                        "500 El Camino Real", "911",
                        "408-277-8900", "registrar@scu.edu", "04/04/2017-04/04/2018" ));
                testView.finishFrame();
            }
        });
    }
}
