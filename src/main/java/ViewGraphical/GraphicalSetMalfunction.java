package ViewGraphical;

/**
 * Created by ahan on 6/6/17.
 */

import Model.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class GraphicalSetMalfunction extends GraphicalRooms {
    private GraphicalArmSystem mainView;
    private JLabel armStatusLabel;

    /**
     * The constructor for the window that displays when "Set Malfunction" button is pressed on the main window
     * @param currentSystem the current system type
     * @param myInstallationManager the installation manager of the program
     * @param myCustomer customer data
     * @param mainView the view object of the main window of the program
     */
    public GraphicalSetMalfunction(CurrentSystem currentSystem, InstallationManager myInstallationManager,
                                  Customer myCustomer, GraphicalArmSystem mainView){
        super(currentSystem, myInstallationManager, myCustomer);
        this.mainView = mainView;
        this.armStatusLabel = mainView.getCurrentStatusLabel();

        managePasswordButton.setEnabled(false);
        installSensorsButton.setEnabled(false);
        activateSensorsButton.setEnabled(false);
        setMalfunctionButton.setEnabled(false);
        armSensorsButton.setEnabled(false);
        triggerSensorsButton.setEnabled(false);
        resetSensorsButton.setEnabled(false);
        advancedArmingMenuButton.setEnabled(false);
        changeCustomerButton.setEnabled(false);
        printInvoiceButton.setEnabled(false);

        switchSystemButton.setEnabled(false);
        installSecurityButton.setEnabled(false);
        installFireAlarmButton.setEnabled(false);
        sprinklersButton.setEnabled(false);
        simulateComboBox.setEnabled(false);
        simulateButton.setEnabled(false);

        currentStatusLabel.setText(buildStatus());

        checkInstallButtons();
    }

    /**
     * Set the text and ActionListeners of the window's display buttons
     */
    public void checkInstallButtons(){
        SensorSystem system = getCurrentSystem();
        //System.out.println(system.getSensorList().size());
        for(int i = 0; i<system.getSensorList().size(); i++){
            final int j = i;
            if(system.isSystemInstalled()==true)
                roomButtons.get(i).setEnabled(true);
            else
                roomButtons.get(i).setEnabled(false);
            StringBuilder unsetMalfunctionText = new StringBuilder();
            unsetMalfunctionText.append("Unset Mal. on ");
            unsetMalfunctionText.append(getCurrentSystem().getSensorList().get(i).getId());
            StringBuilder setMalfunctionText = new StringBuilder();
            setMalfunctionText.append("Set Mal. on ");
            setMalfunctionText.append(getCurrentSystem().getSensorList().get(i).getId());
            if (system.getSensorList().get(i).isMalfunction())
                roomButtons.get(i).setText(unsetMalfunctionText.toString());
            else
                roomButtons.get(i).setText(setMalfunctionText.toString());
            roomButtons.get(i).addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae){
                    buttonActionPerformed(j);
                }
            });
        }

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                roomsFrame.dispatchEvent(new WindowEvent(roomsFrame, WindowEvent.WINDOW_CLOSING));
            }
        });

        paintBackground();
    }

    /**
     * The display button's ActionListener's methods
     * @param j the index of the button whose method is being described
     */
    public void buttonActionPerformed(int j){
        SensorSystem system = getCurrentSystem();
        system.getSensorList().get(j).toggleMalfunction();
        StringBuilder unsetMalfunctionText = new StringBuilder();
        unsetMalfunctionText.append("Unset Mal. on ");
        unsetMalfunctionText.append(getCurrentSystem().getSensorList().get(j).getId());
        StringBuilder setMalfunctionText = new StringBuilder();
        setMalfunctionText.append("Set Mal. on ");
        setMalfunctionText.append(getCurrentSystem().getSensorList().get(j).getId());
        if (system.getSensorList().get(j).isMalfunction())
            roomButtons.get(j).setText(unsetMalfunctionText.toString());
        else
            roomButtons.get(j).setText(setMalfunctionText.toString());

        currentStatusLabel.setText(buildStatus());
        armStatusLabel.setText(buildStatus());

        mainView.paintBackground();
        paintBackground();

    }

    /**
     * Build the status message of the current window
     * @return the status message of the current window
     */
    public String buildStatus(){
        StringBuilder myString = new StringBuilder();
        myString.append(getCurrentSystem().countSensorsMalfunction());
        myString.append(" sensor(s) with malfunctions");

        return myString.toString();
    }

    /**
     * Paint the background of the display
     */
    public void paintBackground(){
        for(int i = 0; i<getCurrentSystem().getSensorList().size(); i++) {
            JPanel currentPanel;
            switch (i) {
                case 0:
                    currentPanel = room1Panel;
                    break;
                case 1:
                    currentPanel = room2Panel;
                    break;
                case 2:
                    currentPanel = room3Panel;
                    break;
                case 3:
                    currentPanel = hallwayWestPanel;
                    break;
                case 4:
                    currentPanel = hallwayEastPanel;
                    break;
                case 5:
                    currentPanel = room4Panel;
                    break;
                case 6:
                    currentPanel = room5Panel;
                    break;
                case 7:
                    currentPanel = room6Panel;
                    break;
                default:
                    currentPanel = hallwayCenterPanel;
                    break;

            }
            Sensor currentSensor = getCurrentSystem().getSensorList().get(i);
            if (currentSensor.isInstalled())
                currentPanel.setBackground(Color.green);
            else
                currentPanel.setBackground(Color.white);

            if (currentSensor.isMalfunction()) {
                currentPanel.setBackground(Color.lightGray);
            } else {
                if (currentSensor.isInstalled())
                    currentPanel.setBackground(Color.green);
                else
                    currentPanel.setBackground(Color.white);
            }
        }

    }


    /**
     * pack the JFrame and make it visible
     */
    public void finishFrame(){
        roomsFrame.pack();
        roomsFrame.setVisible(true);
    }

}
