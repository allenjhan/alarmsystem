package ViewGraphical; /**
 * Created by ahan on 6/1/17.
 */

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Date;
import java.time.*;
import java.applet.AudioClip;
import java.applet.Applet;
import java.io.*;

public class GraphicalArmSystem extends GraphicalRooms {
    //private GraphicalAdvancedArmingMenu advancedMenu;
    private java.util.Timer timerScreen;
    private java.util.Timer playAlarm;
    private java.util.Timer makePhoneCall;
    private JLabel timeIntro;
    private JLabel eventTypeLabel;

    public GraphicalArmSystem(CurrentSystem currentSystem, InstallationManager myInstallationManager,
                              Customer myCustomer) {
        super(currentSystem, myInstallationManager, myCustomer);
        playAlarm = new java.util.Timer();
        makePhoneCall = new java.util.Timer();

        SensorSystem system = getCurrentSystem();
        CurrentSystem systemName = getCurrentSystemName();

        String timeIntroString;
        if(currentSystem==CurrentSystem.FIREALARMSYSTEM)
            timeIntroString = "Next Fire Alarm Event in:";
        else
            timeIntroString="Next Security Event in:";
        timeIntro = new JLabel(timeIntroString);
        timePanel.add(timeIntro);
        JLabel timeLabel = new JLabel();
        timeLabel.setFont(new Font("new font", Font.PLAIN, 40));
        timePanel.add(timeLabel);
        timePanel.add(new JLabel("Event type of Next Event:"));
        eventTypeLabel = new JLabel("");
        timePanel.add(eventTypeLabel);


        /* 6.8.2017 edit
        installSensorsButton.setEnabled(false);
        */
        activateSensorsButton.setEnabled(false);
        setMalfunctionButton.setEnabled(false);
        armSensorsButton.setEnabled(false);
        triggerSensorsButton.setEnabled(false);
        resetSensorsButton.setEnabled(false);
        advancedArmingMenuButton.setEnabled(false);
        installFireAlarmButton.setEnabled(false);
        installSecurityButton.setEnabled(false);
        for (int i = 0; i < roomButtons.size(); i++)
            roomButtons.get(i).setEnabled(false);

        final GraphicalArmSystem myView = this;

        installSensorsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                installSensorsActionPerformed();
            }
        });

        activateSensorsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                activateSensorsActionPerformed();
            }
        });

        setMalfunctionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                setMalfunctionActionPerformed();
            }
        });

        armSensorsButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent) {
                armSensorsActionPerformed();
            }

        });

        resetSensorsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent){
                resetSensorsActionPerformed();
            }
        });

        advancedArmingMenuButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                GraphicalOpenSesameAdvanced passwordCheck = new GraphicalOpenSesameAdvanced(myView);
                passwordCheck.finishFrame();
            }

        });

        managePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                GraphicalOpenSesamePassword resetPassword = new GraphicalOpenSesamePassword(myView);
                resetPassword.finishFrame();
            }
        });

        changeCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                GraphicalOpenSesameCustomer myGraphicalCustomer = new GraphicalOpenSesameCustomer(myView);
                myGraphicalCustomer.finishFrame();
            }
        });

        printInvoiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent){
                printInvoiceActionPerformed();
            }
        });

        switchSystemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                switchSystemButtonActionPerformed();
            }
        });

        /* 6.8.2017 edit
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
        */

        sprinklersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                sprinklersButtonActionPerformed();
            }
        });

        if(getCurrentSystemName()==CurrentSystem.FIREALARMSYSTEM)
            simulateButton.setText("Simulate Fire");
        else
            simulateButton.setText("Simulate Break-in");
        simulateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                simulateButtonActionPerfomed(simulateComboBox.getSelectedIndex());
            }
        });

        doneButton.setText("Exit Program");
        doneButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                playAlarm.cancel();
                makePhoneCall.cancel();
                timerScreen.cancel();
                roomsFrame.dispatchEvent(new WindowEvent(roomsFrame, WindowEvent.WINDOW_CLOSING));
            }
        });


        timerScreen = new java.util.Timer();
        final JLabel myTimeLabel = timeLabel;
        TimerTask task = new TimerTask(){
            public void run(){
                SensorSystem system = myView.getCurrentSystem();
                countDown(myView, CurrentSystem.FIREALARMSYSTEM, myTimeLabel);
                countDown(myView, CurrentSystem.SECURITYSYSTEM, myTimeLabel);
            }
        };
        timerScreen.scheduleAtFixedRate(task, new Date(), 200);

        paintBackground();
    }

    public void countDown(GraphicalArmSystem myView, CurrentSystem currentSystem, JLabel timeLabel){
        SensorSystem system;
        if(currentSystem==CurrentSystem.FIREALARMSYSTEM)
            system=myView.getMyInstallationManager().getFireAlarmSystem();
        else
            system=myView.getMyInstallationManager().getSecuritySensorSystem();

        long toNextEventSeconds;
        Duration toNextEventDuration;
        Duration minutes;
        Duration seconds;
        String toNextEventString;
        ArmingEvent myEvent;
        if(system.getScheduler().getStandaloneEvents().size()>0) {
            toNextEventSeconds = LocalDateTime.now().until(system.getScheduler().getStandaloneEvents().get(0).getTime(), ChronoUnit.SECONDS);
            toNextEventDuration = Duration.ofSeconds(toNextEventSeconds);
            minutes = toNextEventDuration.minusHours(toNextEventDuration.toHours());
            seconds = minutes.minusMinutes(minutes.toMinutes());
            toNextEventString = String.format("%02d:%02d:%02d",(int) toNextEventDuration.toHours(),(int) minutes.toMinutes(), (int) seconds.toMillis()/1000);
            if(myView.getCurrentSystemName()==currentSystem) {
                timeLabel.setText(toNextEventString);
                if (system.getScheduler().getStandaloneEvents().get(0).getEventType() == EventType.ARM)
                    eventTypeLabel.setText("Arm");
                else if (system.getScheduler().getStandaloneEvents().get(0).getEventType() == EventType.DISARM)
                    eventTypeLabel.setText("Disarm");
                else
                    eventTypeLabel.setText("Sensor (de)activate only");
            }
        } else {
            if(myView.getCurrentSystemName()==currentSystem) {
                timeLabel.setText("");
                eventTypeLabel.setText("");
            }
            toNextEventDuration = null;
        }

        LocalDateTime temp;
        if(toNextEventDuration != null) {
            if (toNextEventDuration.compareTo(Duration.ofMillis(0)) <= 0) {
                myEvent = system.getScheduler().getStandaloneEvents().remove(0);
                if (myEvent.isDailyArmingEvent()) {
                    temp = myEvent.getTime().plusDays(1);
                    myEvent.setTime(temp);
                    system.getScheduler().getStandaloneEvents().addInOrder(myEvent);
                }
                //advancedMenu.getStandaloneModel().fireTableDataChanged();
                getCurrentSystem().getScheduler().getStandaloneModel().fireTableDataChanged();

                for (int i = 0; i < myEvent.getActivateArray().length; i++) {
                    if (myEvent.getActivateArray()[i] == 1)
                        system.getSensorList().get(i).activate();
                    else
                        system.getSensorList().get(i).deactivate();
                }

                boolean phoneCall = myEvent.isPhoneCall();
                if (myEvent.getEventType() == EventType.ARM) {
                    if (system.armInstalledAndActivatedSensors(phoneCall)) {
                        if (currentSystem == CurrentSystem.FIREALARMSYSTEM) {
                            if (phoneCall)
                                currentStatusLabel.setText("Event: Armed Fire Alarm System with phone call upon trigger");
                            else
                                currentStatusLabel.setText("Event: Armed Fire Alarm System without phone call upon trigger");
                        } else {
                            if (phoneCall)
                                currentStatusLabel.setText("Event: Armed Security System with phone call upon trigger");
                            else
                                currentStatusLabel.setText("Event: Armed Security System without phone call upon trigger");
                        }
                    } else {
                        currentStatusLabel.setText("Event: No installed and activated sensors, or alarm on. Not armed.");
                    }
                } else if (myEvent.getEventType()==EventType.DISARM){

                    if(system.disarmAllSensors(phoneCall)) {
                        if (currentSystem == CurrentSystem.FIREALARMSYSTEM) {
                            currentStatusLabel.setText("Event: Disarm Fire Alarm System");
                        } else {
                            currentStatusLabel.setText("Event: Disarm Security System");
                        }
                    } else
                        currentStatusLabel.setText("Event: Disarm Failed; Turn off Alarm");
                } else {
                    currentStatusLabel.setText("Event: Activated/Deactivated Sensors Only");
                }
            }
        }

        paintBackground();
    }

    public void installSensorsActionPerformed(){
        GraphicalOpenSesameInstall passwordInstall = new GraphicalOpenSesameInstall(this);
        passwordInstall.finishFrame();
    }

    public void activateSensorsActionPerformed(){
        GraphicalOpenSesameActivate passwordActivate = new GraphicalOpenSesameActivate(this );
        passwordActivate.finishFrame();
    }

    public void setMalfunctionActionPerformed(){
        GraphicalOpenSesameMalfunction passwordMalfunction = new GraphicalOpenSesameMalfunction(this);
        passwordMalfunction.finishFrame();
    }

    public void armSensorsActionPerformed(){
        GraphicalOpenSesameArm arm = new GraphicalOpenSesameArm(this);
        arm.finishFrame();
    }

    public void resetSensorsActionPerformed(){
        GraphicalOpenSesameReset reset = new GraphicalOpenSesameReset(this);
        reset.finishFrame();
    }

    public void printInvoiceActionPerformed(){
        GraphicalInvoice newInvoice = new GraphicalInvoice(myInstallationManager,myCustomer);
    }

    public void switchSystemButtonActionPerformed() {
        String whichSystem;
        String statusMessage;
        if (currentSystem == CurrentSystem.FIREALARMSYSTEM) {
            currentSystem = CurrentSystem.SECURITYSYSTEM;
            whichSystem = "Security Sensor System";
            statusMessage = "Switched to Security Sensor System";
            simulateButton.setText("Simulate Break-in");
            timeIntro.setText("Next Security Event in:");
        }
        else {
            currentSystem = CurrentSystem.FIREALARMSYSTEM;
            whichSystem = "Fire Alarm Sensor System";
            statusMessage = "Switched to Fire Alarm Sensor System";
            simulateButton.setText("Simulate Fire");
            timeIntro.setText("Next Fire Alarm Event in:");
        }

        currentSystemLabel.setText(whichSystem);
        currentStatusLabel.setText(statusMessage);

        //if(currentSystem==Model.CurrentSystem.FIREALARMSYSTEM)
        //    System.out.println("Fire Alarm System");
        //else
        //    System.out.println("Security System");

        SensorSystem mySystem = getCurrentSystem();
        if (mySystem.isSystemInstalled() == true) {
            /* edit 6.8.2017
            installSensorsButton.setEnabled(true);
            */
            activateSensorsButton.setEnabled(true);
            setMalfunctionButton.setEnabled(true);
            armSensorsButton.setEnabled(true);
            triggerSensorsButton.setEnabled(true);
            resetSensorsButton.setEnabled(true);
            advancedArmingMenuButton.setEnabled(true);
        } else {
            /* edit 6.8.2017
            installSensorsButton.setEnabled(false);
            */
            activateSensorsButton.setEnabled(false);
            setMalfunctionButton.setEnabled(false);
            armSensorsButton.setEnabled(false);
            triggerSensorsButton.setEnabled(false);
            resetSensorsButton.setEnabled(false);
            advancedArmingMenuButton.setEnabled(false);
        }

        paintBackground();
    }

    /* 6.8.2017 edit
    public void installSecurityButtonActionPerformed(){
        if (myInstallationManager.getSecuritySensorSystem().isSystemInstalled()) {
            currentStatusLabel.setText("Security System Uninstalled");
            installSecurityButton.setText("Install Security System");
            myInstallationManager.uninstallSecuritySensorSystem();
            if (currentSystem == CurrentSystem.SECURITYSYSTEM) {
                installSensorsButton.setEnabled(false);
                activateSensorsButton.setEnabled(false);
                setMalfunctionButton.setEnabled(false);
                armSensorsButton.setEnabled(false);
                triggerSensorsButton.setEnabled(false);
                resetSensorsButton.setEnabled(false);
                advancedArmingMenuButton.setEnabled(false);
            }
        } else {
            currentStatusLabel.setText("Security System Installed");
            installSecurityButton.setText("Uninstall Security System");
            myInstallationManager.installSecuritySensorSystem();
            if (currentSystem == CurrentSystem.SECURITYSYSTEM) {
                installSensorsButton.setEnabled(true);
                activateSensorsButton.setEnabled(true);
                setMalfunctionButton.setEnabled(true);
                armSensorsButton.setEnabled(true);
                triggerSensorsButton.setEnabled(true);
                resetSensorsButton.setEnabled(true);
                advancedArmingMenuButton.setEnabled(true);
            }
        }

        paintBackground();
    }

    public void installFireAlarmButtonActionPerformed(){
        if (myInstallationManager.getFireAlarmSystem().isSystemInstalled()) {
            currentStatusLabel.setText("Fire Alarm System Uninstalled");
            installFireAlarmButton.setText("Install Fire Alarm System");
            myInstallationManager.uninstallFireAlarmSensorSystem();
            if (currentSystem == CurrentSystem.FIREALARMSYSTEM) {
                installSensorsButton.setEnabled(false);
                activateSensorsButton.setEnabled(false);
                setMalfunctionButton.setEnabled(false);
                armSensorsButton.setEnabled(false);
                triggerSensorsButton.setEnabled(false);
                resetSensorsButton.setEnabled(false);
                advancedArmingMenuButton.setEnabled(false);
            }
        } else {
            currentStatusLabel.setText("Fire Alarm System Installed");
            installFireAlarmButton.setText("Uninstall Fire Alarm System");
            myInstallationManager.installFireAlarmSensorSystem();
            if (currentSystem == CurrentSystem.FIREALARMSYSTEM) {
                installSensorsButton.setEnabled(true);
                activateSensorsButton.setEnabled(true);
                setMalfunctionButton.setEnabled(true);
                armSensorsButton.setEnabled(true);
                triggerSensorsButton.setEnabled(true);
                resetSensorsButton.setEnabled(true);
                advancedArmingMenuButton.setEnabled(true);
            }
        }

        paintBackground();
    }
    */

    public void sprinklersButtonActionPerformed(){
        GraphicalFireAlarmTriggered newFireView = new GraphicalFireAlarmTriggered(
                (String) simulateComboBox.getSelectedItem(),
                myInstallationManager, myCustomer, this, "");
        newFireView.finishFrame();
    }

    public void simulateButtonActionPerfomed(int i){

        if (getCurrentSystem().triggerInstalledSensors(i)) {
            TimerTask playAlarmTimerTask = new TimerTask() {
                public void run() {
                    try {
                        final AudioClip alarm = Applet.newAudioClip((new File("alarm.wav")).toURI().toURL());
                        alarm.play();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            playAlarm.cancel();
            playAlarm = new java.util.Timer();
            playAlarm.scheduleAtFixedRate(playAlarmTimerTask, 0, 3000);


            if (currentSystem == CurrentSystem.FIREALARMSYSTEM) {
                GraphicalFireAlarmTriggered newFireView = new GraphicalFireAlarmTriggered(
                        (String) simulateComboBox.getSelectedItem(),
                        myInstallationManager, myCustomer,  this, "Fire Alarm Triggered");
                newFireView.finishFrame();
                StringBuilder message = new StringBuilder();
                message.append("Fire occurred at ");
                message.append(simulateComboBox.getSelectedItem());
                message.append("; Alarm triggered");
                currentStatusLabel.setText(message.toString());
            } else {
                StringBuilder message = new StringBuilder();
                message.append("Break-in occurred at ");
                message.append(simulateComboBox.getSelectedItem());
                message.append("; Alarm triggered");
                currentStatusLabel.setText(message.toString());
            }

            final LocalDateTime now = LocalDateTime.now();
            if (getCurrentSystem().isCallCenter()) {
                TimerTask makePhoneCallTimerTask = new TimerTask() {
                    public void run() {
                        String nature;
                        if (getCurrentSystemName() == CurrentSystem.FIREALARMSYSTEM)
                            nature = "Fire";
                        else
                            nature = "Break-in";
                        String location;
                        switch (simulateComboBox.getSelectedIndex()) {
                            case 0:
                                location = "Room 1";
                                break;
                            case 1:
                                location = "Room 2";
                                break;
                            case 2:
                                location = "Room 3";
                                break;
                            case 3:
                                location = "Hallway West";
                                break;
                            case 4:
                                location = "Hallway East";
                                break;
                            case 5:
                                location = "Room 4";
                                break;
                            case 6:
                                location = "Room 5";
                                break;
                            default:
                                location = "Room 6";
                                break;
                        }
                        String message = String.format("Alarm triggered. Called " + myCustomer.getEmergencyNumber1() + " and " +
                                myCustomer.getEmergencyNumber2() + " at time " +
                                now.toLocalDate().toString() + " " + now.toLocalTime().toString() +
                                ", regarding contract " + myCustomer.getId() + ". " + nature +
                                " at " + location + ".");
                        JOptionPane.showMessageDialog(null, message, "Alarm Triggered", JOptionPane.INFORMATION_MESSAGE);
                        writeToLog(message);
                    }
                };

                makePhoneCall.cancel();
                makePhoneCall = new java.util.Timer();
                makePhoneCall.scheduleAtFixedRate(makePhoneCallTimerTask,0, 60000);

            } else {
                String nature;
                if (getCurrentSystemName() == CurrentSystem.FIREALARMSYSTEM)
                    nature = "Fire";
                else
                    nature = "Break-in";
                String location;
                switch (simulateComboBox.getSelectedIndex()) {
                    case 0:
                        location = "Room 1";
                        break;
                    case 1:
                        location = "Room 2";
                        break;
                    case 2:
                        location = "Room 3";
                        break;
                    case 3:
                        location = "Hallway West";
                        break;
                    case 4:
                        location = "Hallway East";
                        break;
                    case 5:
                        location = "Room 4";
                        break;
                    case 6:
                        location = "Room 5";
                        break;
                    default:
                        location = "Room 6";
                        break;

                }
                String message = String.format("Alarm triggered, without phone call, at time " +
                        now.toLocalDate().toString() + " " + now.toLocalTime().toString() +
                        ", regarding contract " + myCustomer.getId() + ". " + nature +
                        " at " + location + ".");
                JOptionPane.showMessageDialog(null, message, "Alarm Triggered", JOptionPane.INFORMATION_MESSAGE);
                writeToLog(message);
            }

        } else {
            StringBuilder message = new StringBuilder();
            if (currentSystem == CurrentSystem.FIREALARMSYSTEM) {
                message.append("Fire broke out at ");
                message.append(simulateComboBox.getSelectedItem());
                JOptionPane.showMessageDialog(null, message.toString(), "Error", JOptionPane.INFORMATION_MESSAGE);
            } else {
                message.append("Break-in occurred at ");
                message.append(simulateComboBox.getSelectedItem());
                JOptionPane.showMessageDialog(null, message.toString(), "Error", JOptionPane.INFORMATION_MESSAGE);

            }
        }

        /*
        if(currentSystem==Model.CurrentSystem.FIREALARMSYSTEM){
            System.out.println("sprinklers");
            for(Boolean sprinkler:((Model.FireAlarmSensorSystem) getCurrentSystem()).getSprinklerList()){
                System.out.println(sprinkler);
            }
        }
        */

        paintBackground();
    }

    public java.util.Timer getPlayAlarm() {
        return playAlarm;
    }

    public java.util.Timer getMakePhoneCall() {
        return makePhoneCall;
    }

    public void paintBackground(){
        ArrayList<Sensor> sensorList = getCurrentSystem().getSensorList();
        for(int i = 0; i < sensorList.size(); i++){
            Sensor currentSensor = sensorList.get(i);
            JPanel currentPanel = hallwayCenterPanel;
            switch(i) {
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
            if(currentSensor.isInstalled()){
                if(currentSensor.isAlarmTriggered())
                    currentPanel.setBackground(Color.red);
                else if (currentSensor.isAlarmArmed())
                    currentPanel.setBackground(Color.pink);
                else if (currentSensor.isActivated())
                    currentPanel.setBackground(Color.yellow);
                else
                    currentPanel.setBackground(Color.green);
            } else {
                currentPanel.setBackground(Color.white);
            }

            if(currentSensor.isMalfunction())
                roomButtons.get(i).setText("Malfunction");
            else {
               switch(i){
                   case 0:
                       roomButtons.get(i).setText("Room 1");
                       break;
                   case 1:
                       roomButtons.get(i).setText("Room 2");
                       break;
                   case 2:
                       roomButtons.get(i).setText("Room 3");
                       break;
                   case 3:
                       roomButtons.get(i).setText("Hallway West");
                       break;
                   case 4:
                       roomButtons.get(i).setText("Hallway East");
                       break;
                   case 5:
                       roomButtons.get(i).setText("Room 4");
                       break;
                   case 6:
                       roomButtons.get(i).setText("Room 5");
                       break;
                   default:
                       roomButtons.get(i).setText("Room 6");
                       break;
               }
            }

        }

    }

    public synchronized void writeToLog(String message) {
        try {
            FileOutputStream fos = new FileOutputStream("alarm-log.dat", true);
            fos.write(message.getBytes());
            fos.write("\n".getBytes());
            fos.close();
        } catch(Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                GraphicalArmSystem testView = new GraphicalArmSystem(
                        CurrentSystem.FIREALARMSYSTEM, new InstallationManager(),
                        new Customer("001", "Santa Clara University",
                        "500 El Camino Real", "911",
                        "408-277-8900", "registrar@scu.edu",
                               "04/04/2017-04/04/2018"));
                testView.finishFrame();
            }
        });
    }
}
