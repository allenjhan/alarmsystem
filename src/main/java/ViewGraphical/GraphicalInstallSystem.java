package ViewGraphical; /**
 * Created by ahan on 6/1/17.
 */

import Model.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class GraphicalInstallSystem extends GraphicalRooms {
    private GraphicalArmSystem mainView;
    private JLabel armStatusLabel;

    /**
     * The constructor of the JFrame that gives the options for installing the system and its sensors
     * @param currentSystem the current system type
     * @param myInstallationManager the installation manager of the program
     * @param myCustomer the customer object containing the customer data
     * @param mainView the view object for the main window of the program
     */
    public GraphicalInstallSystem(CurrentSystem currentSystem, InstallationManager myInstallationManager,
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
        if(currentSystem==CurrentSystem.FIREALARMSYSTEM)
            installSecurityButton.setEnabled(false);
        else
            installFireAlarmButton.setEnabled(false);
        sprinklersButton.setEnabled(false);
        simulateComboBox.setEnabled(false);
        simulateButton.setEnabled(false);

        currentStatusLabel.setText(buildStatus());

        checkInstallButtons();
    }

    /**
     * Set the text and ActionListeners for the display buttons
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
            StringBuilder uninstallText = new StringBuilder();
            uninstallText.append("Uninstall Sensor ");
            uninstallText.append(getCurrentSystem().getSensorList().get(i).getId());
            StringBuilder installText = new StringBuilder();
            installText.append("Install Sensor ");
            installText.append(getCurrentSystem().getSensorList().get(i).getId());
            if (system.getSensorList().get(i).isInstalled())
                roomButtons.get(i).setText(uninstallText.toString());
            else
                roomButtons.get(i).setText(installText.toString());
            roomButtons.get(i).addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae){
                    buttonActionPerformed(j);
                }
            });
        }

        installSecurityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                installSecurityButtonActionPerformed();
            }
        });

        installFireAlarmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                installFireAlarmButtonActionPerformed();

            }
        });

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               roomsFrame.dispatchEvent(new WindowEvent(roomsFrame, WindowEvent.WINDOW_CLOSING));
            }
        });

        paintBackground();
    }

    /**
     * The ActionListener method now being set
     * @param j the index of the button that the method corresponds to
     */
    public void buttonActionPerformed(int j){
        SensorSystem system = getCurrentSystem();
        system.getSensorList().get(j).toggleInstall();
        StringBuilder uninstallText = new StringBuilder();
        uninstallText.append("Uninstall Sensor ");
        uninstallText.append(getCurrentSystem().getSensorList().get(j).getId());
        StringBuilder installText = new StringBuilder();
        installText.append("Install Sensor ");
        installText.append(getCurrentSystem().getSensorList().get(j).getId());
        if (system.getSensorList().get(j).isInstalled())
            roomButtons.get(j).setText(uninstallText.toString());
        else
            roomButtons.get(j).setText(installText.toString());
        system.setSensorsInstalledForCurrentMonth();

        currentStatusLabel.setText(buildStatus());
        armStatusLabel.setText(buildStatus());

        mainView.paintBackground();
        paintBackground();
    }

    /**
     * The ActionListener for the install Security System button
     */
    public void installSecurityButtonActionPerformed(){

        if (mainView.myInstallationManager.getSecuritySensorSystem().isSystemInstalled()) {
            mainView.currentStatusLabel.setText("Security System Uninstalled");
            installSecurityButton.setText("Install Security System");
            mainView.myInstallationManager.uninstallSecuritySensorSystem();
            if (mainView.currentSystem == CurrentSystem.SECURITYSYSTEM) {
                //mainView.installSensorsButton.setEnabled(false);
                mainView.activateSensorsButton.setEnabled(false);
                mainView.setMalfunctionButton.setEnabled(false);
                mainView.armSensorsButton.setEnabled(false);
                mainView.triggerSensorsButton.setEnabled(false);
                mainView.resetSensorsButton.setEnabled(false);
                mainView.advancedArmingMenuButton.setEnabled(false);
            }
        } else {
            mainView.currentStatusLabel.setText("Security System Installed");
            installSecurityButton.setText("Uninstall Security System");
            mainView.myInstallationManager.installSecuritySensorSystem();
            if (currentSystem == CurrentSystem.SECURITYSYSTEM) {
                //mainView.installSensorsButton.setEnabled(true);
                mainView.activateSensorsButton.setEnabled(true);
                mainView.setMalfunctionButton.setEnabled(true);
                mainView.armSensorsButton.setEnabled(true);
                mainView.triggerSensorsButton.setEnabled(true);
                mainView.resetSensorsButton.setEnabled(true);
                mainView.advancedArmingMenuButton.setEnabled(true);
            }
        }

        mainView.paintBackground();
        paintBackground();
    }

    /**
     * The install button for the fire alarm action button
     */
    public void installFireAlarmButtonActionPerformed(){
        if (mainView.myInstallationManager.getFireAlarmSystem().isSystemInstalled()) {
            mainView.currentStatusLabel.setText("Fire Alarm System Uninstalled");
            installFireAlarmButton.setText("Install Fire Alarm System");
            mainView.myInstallationManager.uninstallFireAlarmSensorSystem();
            if (mainView.currentSystem == CurrentSystem.FIREALARMSYSTEM) {
                //mainView.installSensorsButton.setEnabled(false);
                mainView.activateSensorsButton.setEnabled(false);
                mainView.setMalfunctionButton.setEnabled(false);
                mainView.armSensorsButton.setEnabled(false);
                mainView.triggerSensorsButton.setEnabled(false);
                mainView.resetSensorsButton.setEnabled(false);
                mainView.advancedArmingMenuButton.setEnabled(false);
            }
        } else {
            mainView.currentStatusLabel.setText("Fire Alarm System Installed");
            installFireAlarmButton.setText("Uninstall Fire Alarm System");
            mainView.myInstallationManager.installFireAlarmSensorSystem();
            if (currentSystem == CurrentSystem.FIREALARMSYSTEM) {
                //mainView.installSensorsButton.setEnabled(true);
                mainView.activateSensorsButton.setEnabled(true);
                mainView.setMalfunctionButton.setEnabled(true);
                mainView.armSensorsButton.setEnabled(true);
                mainView.triggerSensorsButton.setEnabled(true);
                mainView.resetSensorsButton.setEnabled(true);
                mainView.advancedArmingMenuButton.setEnabled(true);
            }
        }

        mainView.paintBackground();
        paintBackground();
    }

    /**
     * build the current status message of the program
     * @return the current status of the program
     */
    public String buildStatus(){
        StringBuilder myString = new StringBuilder();
        myString.append(getCurrentSystem().countSensorsInstalled());
        myString.append(" sensor(s) installed");

        return myString.toString();
    }

    /**
     * paint the background of the display
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
            if (currentSensor.isInstalled()) {
                currentPanel.setBackground(Color.green);
            } else {
                currentPanel.setBackground(Color.white);
            }

            if (getCurrentSystem().isSystemInstalled() == true)
                roomButtons.get(i).setEnabled(true);
            else
                roomButtons.get(i).setEnabled(false);
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
