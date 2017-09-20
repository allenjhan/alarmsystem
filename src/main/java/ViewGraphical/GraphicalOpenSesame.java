package ViewGraphical; /**
 * Created by ahan on 6/1/17.
 */
import Model.*;

import javax.swing.*;
import java.awt.event.*;

public class GraphicalOpenSesame {
    protected JFrame myFrame;
    protected GraphicalArmSystem myView;
    protected CurrentSystem currentSystem;
    protected UsernameAndPassword account;

    /**
     * The constructor for the superclass object of the password validation windows
     * @param myView the view object of the main window of the program
     */
    public GraphicalOpenSesame(GraphicalArmSystem myView) {
        this.myView = myView;
        this.currentSystem = myView.getCurrentSystemName();
        this.account = myView.getMyCustomer().getAccount();

        myFrame = new JFrame();
        JPanel level1Panel = new JPanel();
        myFrame.add(level1Panel);
        level1Panel.setLayout(new BoxLayout(level1Panel, BoxLayout.Y_AXIS));

        JPanel level11Panel = new JPanel();
        level1Panel.add(level11Panel);
        String usernameString = String.format("Username \""+account.getUsername()+"\"");
        level11Panel.add(new JLabel(usernameString));
        //final JTextField usernameTextField = new JTextField(40);
        //level11Panel.add(usernameTextField);

        JPanel level12Panel = new JPanel();
        level1Panel.add(level12Panel);
        level12Panel.add(new JLabel("Password"));
        final JTextField passwordTextField = new JTextField(40);
        level12Panel.add(passwordTextField);

        JPanel level13Panel = new JPanel();
        level1Panel.add(level13Panel);
        JButton doneButton = new JButton("Done");
        level13Panel.add(doneButton);
        doneButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent) {
                //String myUsername = usernameTextField.getText();
                String myPassword = passwordTextField.getText();
                doneButtonActionPerformed(myPassword);
            }
        });

        passwordTextField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                //String myUsername = usernameTextField.getText();
                String myPassword = passwordTextField.getText();
                doneButtonActionPerformed(myPassword);
            }
        });
    }

    /**
     * The ActionListener method for the done button of the window
     * @param password the password of the window
     */
    public void doneButtonActionPerformed(String password){
        if(account.getPassword().equals(password)){
            myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING));
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Pack the JFrame and make it visible
     */
    public void finishFrame(){
        myFrame.pack();
        myFrame.setVisible(true);
    }
}
