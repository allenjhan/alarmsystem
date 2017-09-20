package ViewGraphical; /**
 * Created by ahan on 6/2/17.
 */


import Model.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class GraphicalFireAlarmTriggered extends GraphicalRooms {
    private JLabel armStatusLabel;
    private String fireLocation;
    private GraphicalArmSystem myView;
    private String statusMessage;

    /**
     * The constructor for the sprinkler status page. This is the page opened by clicking "Sprinklers" on the
     * main window and also opens when the fire alarm system triggers.
     * @param fireLocation the location of the sensor which first detected the fire
     * @param myInstallationManager the installation manager of the entire system
     * @param myCustomer the customer object containing customer data
     * @param myView the view object for the main window of the program
     * @param statusMessage status message to display on the created window
     */
    public GraphicalFireAlarmTriggered(String fireLocation, InstallationManager myInstallationManager,
                                       Customer myCustomer,
                                       GraphicalArmSystem myView, String statusMessage){
        super(CurrentSystem.FIREALARMSYSTEM, myInstallationManager, myCustomer);
        this.armStatusLabel = armStatusLabel;
        this.fireLocation = fireLocation;
        this.statusMessage = statusMessage;
        this.armStatusLabel = myView.getCurrentStatusLabel();

        managePasswordButton.setEnabled(false);
        installSensorsButton.setEnabled(false);
        activateSensorsButton.setEnabled(false);
        setMalfunctionButton.setEnabled(false);
        armSensorsButton.setEnabled(false);
        triggerSensorsButton.setEnabled(false);
        //resetSensorsButton.setEnabled(false);
        advancedArmingMenuButton.setEnabled(false);
        changeCustomerButton.setEnabled(false);
        printInvoiceButton.setEnabled(false);

        switchSystemButton.setEnabled(false);
        installSecurityButton.setEnabled(false);
        installFireAlarmButton.setEnabled(false);
        sprinklersButton.setEnabled(false);
        simulateComboBox.setEnabled(false);
        simulateButton.setEnabled(false);

        final GraphicalArmSystem mainView = myView;
        resetSensorsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                GraphicalOpenSesameReset reset = new GraphicalOpenSesameReset(mainView);
                reset.finishFrame();
            }
        });

        /*
        StringBuilder fireStatus = new StringBuilder();
        fireStatus.append("Fire broke out at ");
        fireStatus.append(fireLocation);
        currentStatusLabel.setText(fireStatus.toString());
        */

        currentStatusLabel.setText(statusMessage);

        checkSprinklerButtons();
    }

    /**
     * Define the text on the display buttons and set their ActionListeners
     */
    public void checkSprinklerButtons(){
        final FireAlarmSensorSystem system = (FireAlarmSensorSystem) getCurrentSystem();
        //System.out.println(system.getSensorList().size());
        for(int i = 0; i<system.getSensorList().size(); i++){
            if(system.getSprinklerList().get(i))
                roomButtons.get(i).setEnabled(true);
            else
                roomButtons.get(i).setEnabled(false);
            StringBuilder sprinklerText = new StringBuilder();
            sprinklerText.append("Turn off sprinkler ");
            sprinklerText.append(getCurrentSystem().getSensorList().get(i).getId());
            roomButtons.get(i).setText(sprinklerText.toString());

            final int j = i;
            roomButtons.get(i).addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    sprinklerButtonActionPerformed(system, j);
                }
            });
        }

        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                roomsFrame.dispatchEvent(new WindowEvent(roomsFrame, WindowEvent.WINDOW_CLOSING));
            }
        });

        paintBackground();
    }

    /**
     * Create the status message to display
     * @param currentSensor the current Sensor being modified
     * @return status message
     */
    public String buildStatus(Sensor currentSensor){
        StringBuilder myString = new StringBuilder();
        myString.append("Sprinkler ");
        myString.append(currentSensor.getId());
        myString.append(" turned-off");

        return myString.toString();
    }

    /**
     * The method for the ActionListener for the display buttons
     * @param system the fire alarm sensor system
     * @param j the index for the display button whose ActionListener method is now being set
     */
    public void sprinklerButtonActionPerformed(FireAlarmSensorSystem system, int j) {
        roomButtons.get(j).setEnabled(false);
        system.getSprinklerList().set(j, false);
        currentStatusLabel.setText(buildStatus(getCurrentSystem().getSensorList().get(j)));
        armStatusLabel.setText(buildStatus(getCurrentSystem().getSensorList().get(j)));
        paintBackground();
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
            boolean sprinkler = ((FireAlarmSensorSystem) getCurrentSystem()).getSprinklerList().get(i);
            if (sprinkler) {
                currentPanel.setBackground(Color.blue);
            } else {
                currentPanel.setBackground(Color.white);
            }
        }

    }

    /**
     * Pack the JFrame and make it visible.
     */
    public void finishFrame(){
        roomsFrame.pack();
        roomsFrame.setVisible(true);
    }

}
