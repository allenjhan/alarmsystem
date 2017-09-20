package ViewGraphical;

/**
 * Created by ahan on 6/4/17.
 */

import Model.*;
import javax.swing.*;
import java.awt.event.*;

public class GraphicalArmOptions {

    /**
     * The view object to be displayed upon selecting "Arm Sensors" from the main window.
     * @param view the view object for the main window of the program
     */
    public GraphicalArmOptions(GraphicalArmSystem view){
        final GraphicalArmSystem myView = view;

        final JFrame optionFrame = new JFrame("Arming Options");
        JPanel level1Panel = new JPanel();
        optionFrame.add(level1Panel);
        level1Panel.setLayout(new BoxLayout(level1Panel, BoxLayout.Y_AXIS));

        JPanel level11Panel = new JPanel();
        level1Panel.add(level11Panel);
        level11Panel.add(new JLabel("Emergency Telephone 1"));
        final JTextField field1 = new JTextField(myView.getMyCustomer().getEmergencyNumber1(), 20);
        level11Panel.add(field1);

        JPanel level12Panel = new JPanel();
        level1Panel.add(level12Panel);
        level12Panel.add(new JLabel("Emergency Telephone 2"));
        final JTextField field2 = new JTextField(myView.getMyCustomer().getEmergencyNumber2(), 20);
        level12Panel.add(field2);

        JPanel level13Panel = new JPanel();
        level1Panel.add(level13Panel);
        JButton armNoPhone = new JButton("Arm without phone call upon trigger");
        level1Panel.add(armNoPhone);
        JButton armPhone = new JButton("Arm with phone call upon trigger");
        level1Panel.add(armPhone);

        armNoPhone.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                optionFrame.dispatchEvent(new WindowEvent(optionFrame, WindowEvent.WINDOW_CLOSING));
                if (myView.getCurrentSystem().armInstalledAndActivatedSensors(false)){
                    StringBuilder myString = new StringBuilder();
                    myString.append("Armed ");
                    if(myView.getCurrentSystemName() == CurrentSystem.FIREALARMSYSTEM)
                        myString.append("Fire Alarm");
                    else
                        myString.append("Security");
                    myString.append(" System without phone call upon trigger");
                    myView.getCurrentStatusLabel().setText(myString.toString());
                } else {
                    myView.getCurrentStatusLabel().setText("Arming Failed; Install and Activate Sensors, or Turn off Alarm");
                }
                myView.paintBackground();
            }
        });

        armPhone.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                optionFrame.dispatchEvent(new WindowEvent(optionFrame, WindowEvent.WINDOW_CLOSING));
                if (myView.getCurrentSystem().armInstalledAndActivatedSensors(true)){
                    StringBuilder myString = new StringBuilder();
                    myString.append("Armed ");
                    if(myView.getCurrentSystemName() == CurrentSystem.FIREALARMSYSTEM)
                        myString.append("Fire Alarm");
                    else
                        myString.append("Security");
                    myString.append(" System with phone call upon trigger");
                    myView.getCurrentStatusLabel().setText(myString.toString());
                    myView.getMyCustomer().setEmergencyNumber1(field1.getText());
                    myView.getMyCustomer().setEmergencyNumber2(field2.getText());
                } else {
                    myView.getCurrentStatusLabel().setText("Arming Failed; Install and Activate Sensors, or Turn off Alarm");
                }
                myView.paintBackground();
            }
        });


        optionFrame.pack();
        optionFrame.setVisible(true);
    }
}

