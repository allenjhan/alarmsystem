package ViewGraphical; /**
 * Created by ahan on 6/2/17.
 */

import Model.CurrentSystem;

import javax.swing.*;
import java.awt.event.*;
import java.time.*;

public class GraphicalOpenSesameReset extends GraphicalOpenSesame {

    /**
     * The password validation screen when "Reset System" button is pressed at main window
     * @param myView the view object for the main window of the program
     */
    public GraphicalOpenSesameReset(GraphicalArmSystem myView){
        super(myView);
    }

    /**
     * The "done" button's ActionListener's method
     * @param password the password of the window
     */
    @Override
    public void doneButtonActionPerformed( String password){
        if( account.getPassword().equals(password)){
            myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING));
            myView.getPlayAlarm().cancel();
            myView.getMakePhoneCall().cancel();

            StringBuilder myString = new StringBuilder();
            myString.append("Reset ");
            if(myView.getCurrentSystemName() == CurrentSystem.FIREALARMSYSTEM)
                myString.append("Fire Alarm");
            else
                myString.append("Security");
            myString.append(" System");
            myView.getCurrentStatusLabel().setText(myString.toString());

            if(myView.getCurrentSystem().isTriggered()){
                myString.append(" with Response Code at ");
                myString.append(LocalDateTime.now().toLocalDate().toString());
                myString.append(" ");
                myString.append(LocalDateTime.now().toLocalTime().toString());
                myView.writeToLog(myString.toString());
            }

            myView.getCurrentSystem().resetAllSensors(false);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        myView.paintBackground();
    }
}
