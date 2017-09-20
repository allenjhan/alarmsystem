package ViewGraphical;

/**
 * Created by ahan on 6/5/17.
 */

import Model.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class GraphicalEventActivation extends GraphicalRooms {
    private ActivationEvent myEvent;

    /**
     * The constructor the view window for planning sensor activations in the Advanced Arming Menu's timer and
     * scheduler options
     * @param currentSystem the sensor system type of the current window
     * @param myInstallationManager the installation manager for the program as a whole
     * @param myCustomer the customer object, containg the customer data
     * @param myEvent the event whose sensor activations are to be configured by the current window
     */
    public GraphicalEventActivation(CurrentSystem currentSystem, InstallationManager myInstallationManager,
                                   Customer myCustomer, ActivationEvent myEvent){
        super(currentSystem, myInstallationManager, myCustomer);
        this.myEvent = myEvent;

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
        this.finishFrame();
    }

    /**
     * Set the text of the display buttons and their ActionListeners.
     */
    public void checkActivateButtons(){
        SensorSystem system = getCurrentSystem();
        //System.out.println(system.getSensorList().size());
        for(int i = 0; i<myEvent.getActivateArray().length; i++){
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
            //System.out.println("j is" + j);
            if (myEvent.getActivateArray()[j]==0)
                roomButtons.get(i).setText(activateText.toString());
            else
                roomButtons.get(i).setText(deactivateText.toString());
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
     * The method for the display buttons' ActionListeners that are called.
     * @param j the index of the button whose ActionListener method is to be set
     */
    public void buttonActionPerformed(int j){
        SensorSystem system = getCurrentSystem();
        myEvent.toggleActivate(j);
        StringBuilder deactivateText = new StringBuilder();
        deactivateText.append("Deactivate Sensor ");
        deactivateText.append(getCurrentSystem().getSensorList().get(j).getId());
        StringBuilder activateText = new StringBuilder();
        activateText.append("Activate Sensor ");
        activateText.append(getCurrentSystem().getSensorList().get(j).getId());
        if (myEvent.getActivateArray()[j]==0)
            roomButtons.get(j).setText(activateText.toString());
        else
            roomButtons.get(j).setText(deactivateText.toString());

        currentStatusLabel.setText(buildStatus());
        paintBackground();

    }

    /**
     * Build the string that will display the current status of the program
     * @return the string that contains the program status
     */
    public String buildStatus(){
        StringBuilder myString = new StringBuilder();
        myString.append(getCurrentSystem().countSensorsActivated());
        myString.append(" sensor(s) activated");

        return myString.toString();
    }

    /**
     * Paint the background of the display
     */
    public void paintBackground() {
        for (int i = 0; i < myEvent.getActivateArray().length; i++) {
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
            int activated = myEvent.getActivateArray()[i];
            if (activated == 1 && getCurrentSystem().getSensorList().get(i).isInstalled()) {
                currentPanel.setBackground(Color.yellow);
            } else {
                currentPanel.setBackground(Color.white);
            }
        }
    }

    /**
     * Pack the Jframe of the program and make it visible
     */
    public void finishFrame(){
        roomsFrame.pack();
        roomsFrame.setVisible(true);
    }

}
