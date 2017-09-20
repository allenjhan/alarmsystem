package ViewGraphical; /**
 * Created by ahan on 6/3/17.
 */

import Model.Customer;
import Model.UsernameAndPassword;

import javax.swing.*;
import java.awt.event.*;

public class GraphicalCustomer {
    private JFrame viewFrame;
    public GraphicalCustomer(Customer customer){
        final Customer currentCustomer = customer;
        viewFrame = new JFrame();
        JPanel level1Panel = new JPanel();
        viewFrame.add(level1Panel);
        level1Panel.setLayout(new BoxLayout(level1Panel, BoxLayout.Y_AXIS));

        JPanel level11Panel = new JPanel();
        level1Panel.add(level11Panel);
        JPanel level12Panel = new JPanel();
        level1Panel.add(level12Panel);
        JPanel level13Panel = new JPanel();
        level1Panel.add(level13Panel);
        JPanel level14Panel = new JPanel();
        level1Panel.add(level14Panel);
        JPanel level15Panel = new JPanel();
        level1Panel.add(level15Panel);
        JPanel level16Panel = new JPanel();
        level1Panel.add(level16Panel);
        JPanel level17Panel = new JPanel();
        level1Panel.add(level17Panel);
        JPanel level18Panel = new JPanel();
        level1Panel.add(level18Panel);

        level11Panel.add(new JLabel("Contract ID"));
        level12Panel.add(new JLabel("Name"));
        level13Panel.add(new JLabel("Address"));
        level14Panel.add(new JLabel("Emergency Telephone 1"));
        level15Panel.add(new JLabel("Emergency Telephone 2"));
        level16Panel.add(new JLabel("Customer ID"));
        level17Panel.add(new JLabel("Effective Date"));

        final JTextField field1 = new JTextField(currentCustomer.getId(),20);
        final JTextField field2 = new JTextField(currentCustomer.getName(), 20);
        final JTextField field3 = new JTextField(currentCustomer.getAddress(), 20);
        final JTextField field4 = new JTextField(currentCustomer.getEmergencyNumber1(), 20);
        final JTextField field5 = new JTextField(currentCustomer.getEmergencyNumber2(), 20);
        final JTextField field6 = new JTextField(currentCustomer.getCustomerID(), 20);
        final JTextField field7 = new JTextField(currentCustomer.getEffectiveDate(), 20);

        level11Panel.add(field1);
        level12Panel.add(field2);
        level13Panel.add(field3);
        level14Panel.add(field4);
        level15Panel.add(field5);
        level16Panel.add(field6);
        level17Panel.add(field7);

        JButton doneButton = new JButton("Done");
        level18Panel.add(doneButton);
        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(UsernameAndPassword.isValidUsernameOrPassword(field6.getText())) {
                    viewFrame.dispatchEvent(new WindowEvent(viewFrame, WindowEvent.WINDOW_CLOSING));
                    currentCustomer.setId(field1.getText());
                    currentCustomer.setName(field2.getText());
                    currentCustomer.setAddress(field3.getText());
                    currentCustomer.setEmergencyNumber1(field4.getText());
                    currentCustomer.setEmergencyNumber2(field5.getText());
                    currentCustomer.setCustomerID(field6.getText());
                    currentCustomer.getAccount().setUsername(field6.getText());
                } else
                    JOptionPane.showMessageDialog(null, "Customer ID must have no whitespace characters", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }

    public void finishFrame(){
        //viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewFrame.pack();
        viewFrame.setVisible(true);
    }


    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GraphicalCustomer newCustomer = new GraphicalCustomer(new Customer("001", "Santa Clara University",
                        "500 El Camino Real", "911",
                        "408-277-8900", "registrar@scu.edu", "04/04/2017-04/04/2018" ));
                newCustomer.finishFrame();

            }
        });
    }
}
