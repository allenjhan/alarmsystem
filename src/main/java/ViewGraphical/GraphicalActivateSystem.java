package ViewGraphical; /**
 * Created by ahan on 6/2/17.
 */
/**
 * Created by ahan on 6/1/17.
 */

import Model.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class GraphicalActivateSystem extends GraphicalRooms {
    private GraphicalArmSystem mainView;
    private JLabel armStatusLabel;

    /**
     * This is the class for the frame where the activation state is set for all the sensors. It is entered
     * upon selecting "Activate Sensors" from the main window of the program.
     * @param currentSystem the system type of the current system
     * @param myInstallationManager the installation manager, containing the various sensor systems
     * @param myCustomer the customer object, containg the account information
     * @param mainView the view object for the main window of the program
     */
    public GraphicalActivateSystem(CurrentSystem currentSystem, InstallationManager myInstallationManager,
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

        checkActivateButtons();
    }

    /**
     * Set the text and ActionListeners for the display buttons
     */
    public void checkActivateButtons(){
        SensorSystem system = getCurrentSystem();
        //System.out.println(system.getSensorList().size());
        for(int i = 0; i<system.getSensorList().size(); i++){
            final int j = i;
            if(system.getSensorList().get(i).isInstalled()==true)
                roomButtons.get(i).setEnabled(true);
            else
                roomButtons.get(i).setEnabled(false);
            StringBuilder deactivateText = new StringBuilder();
            deactivateText.append("Deactivate Sensor ");
            deactivateText.append(getCurrentSystem().getSensorList().get(i).getId());
            StringBuilder activateText = new StringBuilder();
            activateText.append("Activate Sensor ");
            activateText.append(getCurrentSystem().getSensorList().get(i).getId());
            if (system.getSensorList().get(i).isActivated())
                roomButtons.get(i).setText(deactivateText.toString());
            else
                roomButtons.get(i).setText(activateText.toString());
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
     * ActionListener method for the display buttons
     * @param j index of the button whose ActionListener method is being set
     */
    public void buttonActionPerformed(int j){
        SensorSystem system = getCurrentSystem();
        system.getSensorList().get(j).toggleActivate();
        StringBuilder deactivateText = new StringBuilder();
        deactivateText.append("Deactivate Sensor ");
        deactivateText.append(getCurrentSystem().getSensorList().get(j).getId());
        StringBuilder activateText = new StringBuilder();
        activateText.append("Activate Sensor ");
        activateText.append(getCurrentSystem().getSensorList().get(j).getId());
        if (system.getSensorList().get(j).isActivated())
            roomButtons.get(j).setText(deactivateText.toString());
        else
            roomButtons.get(j).setText(activateText.toString());

        currentStatusLabel.setText(buildStatus());
        armStatusLabel.setText(buildStatus());

        mainView.paintBackground();
        paintBackground();
    }

    /**
     * Build the status message for what just occurred in the system
     * @return
     */
    public String buildStatus(){
        StringBuilder myString = new StringBuilder();
        myString.append(getCurrentSystem().countSensorsActivated());
        myString.append(" sensor(s) activated");

        return myString.toString();
    }

    /**
     * Paint the background of the display the required colors and set the text of the display buttons.
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
            if (currentSensor.isActivated()) {
                currentPanel.setBackground(Color.yellow);
            } else if (currentSensor.isInstalled()){
                currentPanel.setBackground(Color.green);
            } else {
                currentPanel.setBackground(Color.white);
            }
        }

    }


    /**
     * Pack the JFrame and make it visible
     */
    public void finishFrame(){
        roomsFrame.pack();
        roomsFrame.setVisible(true);
    }

}
