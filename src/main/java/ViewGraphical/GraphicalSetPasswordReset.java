package ViewGraphical;

/**
 * Created by ahan on 6/6/17.
 */
import Model.*;
import javax.swing.*;
import java.awt.event.*;
public class GraphicalSetPasswordReset extends GraphicalSetPassword{
    /**
     * The constructor for the set password window, reached by pressing "Set Password" from the main window
     * @param myCustomer customer data
     */
    public GraphicalSetPasswordReset(Customer myCustomer) {
        super(myCustomer);

    }

    /**
     * the "done" button's ActionListener's method
     * @param password password to be set
     */
    @Override
    public void doneButtonActionPerformed(String password){
        if (myCustomer.getAccount().setPassword(password) == true) {
            myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING));
        } else
            JOptionPane.showMessageDialog(null, "No Whitespace Characters. Must be at least one character long", "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                GraphicalSetPasswordReset testView = new GraphicalSetPasswordReset(new Customer("001", "Santa Clara University",
                        "500 El Camino Real", "911",
                        "408-277-8900", "registrar@scu.edu", "04/04/2017-04/04/2018" ));
                testView.finishFrame();
            }
        });
    }
}
