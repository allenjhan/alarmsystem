package ViewGraphical; /**
 * Created by ahan on 6/1/17.
 */

import Model.CurrentSystem;
import Model.Customer;
import Model.InstallationManager;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public abstract class GraphicalSetPassword {
    protected Customer myCustomer;
    String username;
    String password;
    String filename;

    JFrame myFrame;

    /**
     * The constructor for the superclass of the set password windows
     * @param myCustomer customer data
     */
    public GraphicalSetPassword(Customer myCustomer) {
        this.myCustomer = myCustomer;
        this.username = myCustomer.getCustomerID();
        this.filename = "password.txt";

        myCustomer.getAccount().setUsername(username);

        myFrame = new JFrame();
        JPanel level1Panel = new JPanel();
        myFrame.add(level1Panel);
        level1Panel.setLayout(new BoxLayout(level1Panel, BoxLayout.Y_AXIS));

        JPanel level11Panel = new JPanel();
        level1Panel.add(level11Panel);
        StringBuilder newString = new StringBuilder();
        newString.append("Set password for user \"");
        newString.append(username);
        newString.append("\"");
        JLabel usernameLabel = new JLabel(newString.toString());
        level11Panel.add(usernameLabel);

        JPanel level12Panel = new JPanel();
        level1Panel.add(level12Panel);
        level12Panel.add(new JLabel("Password"));
        final JTextField myTextField = new JTextField(40);
        level12Panel.add(myTextField);

        JPanel level13Panel = new JPanel();
        level1Panel.add(level13Panel);

        JButton saveButton = new JButton("Save");
        level13Panel.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                saveToFile(myTextField);
            }
        });

        JButton loadButton = new JButton("Load");
        level13Panel.add(loadButton);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loadFromFile(myTextField);
                myFrame.setVisible(true);
            }
        });

        JButton doneButton = new JButton("Done");
        level13Panel.add(doneButton);
        doneButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent) {
                String myPassword = myTextField.getText();
                doneButtonActionPerformed(myPassword);
            }
        });

        myTextField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                String myPassword = myTextField.getText();
                doneButtonActionPerformed(myPassword);
            }
        });
    }

    /**
     * Action performed method for "done" button's ActionListener; is only a declaration that will be defined in subclasses
     * @param password password to be set
     */
    public void doneButtonActionPerformed(String password) { };

    /**
     * load the password from the external file and display in the text field
     * @param textField the text field in which to display the password
     */
    public void loadFromFile(JTextField textField){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filename)));
            String myPassword = bufferedReader.readLine();
            textField.setText(myPassword);
            bufferedReader.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Session Load Error", "Error", JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * save the password entered in the textfield to an external file
     * @param textField the text field which holds the password to be saved
     */
    public void saveToFile(JTextField textField){
        try {
            PrintWriter writer = new PrintWriter(filename);
            writer.println(textField.getText());
            writer.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Session Save Error", "Error", JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }

    }

    public void finishFrame(){
        myFrame.pack();
        myFrame.setVisible(true);
    }

}
