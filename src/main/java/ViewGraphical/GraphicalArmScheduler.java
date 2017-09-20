package ViewGraphical;

/**
 * Created by ahan on 6/4/17.
 */

import Model.*;
import javax.swing.*;
import java.awt.event.*;
import java.time.*;
import java.time.temporal.*;
import javax.swing.table.*;

public class GraphicalArmScheduler {

    /**
     * The scheduler window that is opened after selecting the scheduler button from the Advanced
     * Arming Menu.
     * @param menu the view object for the Advanced Arming Menu
     * @param view the view object for the main window of the program
     */
    public GraphicalArmScheduler(GraphicalAdvancedArmingMenu menu, GraphicalArmSystem view){
        final GraphicalAdvancedArmingMenu myMenu = menu;
        final GraphicalArmSystem myView = view;
        final SensorSystem mySystem = myView.getCurrentSystem();

        final JFrame currentFrame = new JFrame("Add Arming Event by Scheduler");
        JPanel level1Panel = new JPanel();
        level1Panel.setLayout(new BoxLayout(level1Panel, BoxLayout.Y_AXIS));
        currentFrame.add(level1Panel);
        level1Panel.add(Box.createVerticalGlue());

        JPanel level11Panel = new JPanel();
        level11Panel.setLayout(new BoxLayout(level11Panel, BoxLayout.X_AXIS));
        level1Panel.add(level11Panel);
        level1Panel.add(Box.createVerticalGlue());

        JPanel level111Panel = new JPanel();
        level111Panel.setLayout(new BoxLayout(level111Panel, BoxLayout.Y_AXIS));
        level11Panel.add(level111Panel);
        level111Panel.add(new JLabel("Hours"));
        final JComboBox hoursBox = new JComboBox(new String[] {"0","1","2","3","4","5","6","7","8","9","10","11",
                "12","13","14","15","16","17","18","19","20","21","22","23"});
        level111Panel.add(hoursBox);

        JPanel level112Panel = new JPanel();
        level112Panel.setLayout(new BoxLayout(level112Panel, BoxLayout.Y_AXIS));
        level11Panel.add(level112Panel);
        level112Panel.add(new JLabel("Minutes"));
        final JComboBox minutesBox = new JComboBox(new String[] {"0","1","2","3","4","5","6","7","8","9","10","11",
                "12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30",
                "31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49",
                "50","51","52","53","54","55","56","57","58","59"});
        level112Panel.add(minutesBox);

        /*
        JPanel level113Panel = new JPanel();
        level113Panel.setLayout(new BoxLayout(level113Panel, BoxLayout.Y_AXIS));
        level11Panel.add(level113Panel);
        level113Panel.add(new JLabel("Seconds"));
        JComboBox secondsBox = new JComboBox(new String[] {"0","1","2","3","4","5","6","7","8","9","10","11",
                "12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30",
                "31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49",
                "50","51","52","53","54","55","56","57","58","59"});
        level113Panel.add(secondsBox);
        */

        /*
        JPanel level114Panel = new JPanel();
        level114Panel.setLayout(new BoxLayout(level114Panel, BoxLayout.Y_AXIS));
        level11Panel.add(level114Panel);
        level114Panel.add(new JLabel("AM/PM"));
        JComboBox ampmBox = new JComboBox(new String[] {"AM","PM"});
        level114Panel.add(ampmBox);
        */

        level1Panel.add(Box.createVerticalGlue());
        JPanel level12Panel = new JPanel();
        level1Panel.add(level12Panel);
        //level12Panel.setLayout(new BoxLayout(level12Panel,BoxLayout.X_AXIS));
        final JComboBox armdisarmBox = new JComboBox(new String[] {"Arm","Disarm","Neither"});
        level12Panel.add(armdisarmBox);
        final JComboBox phoneCallBox = new JComboBox(new String[] {"No Phone on Trigger", "Phone on Trigger"});
        level12Panel.add(phoneCallBox);

        JPanel level13Panel = new JPanel();
        level1Panel.add(level13Panel);
        JButton setActivationButton = new JButton("Set Activation for Event");
        level13Panel.add(setActivationButton);

        final DailyArmingEvent myDailyEvent = new DailyArmingEvent();
        final GraphicalArmSystem mainView = myView;
        setActivationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                GraphicalEventActivation myView = new GraphicalEventActivation(myMenu.getNameOfCurrentSystem(),
                        mainView.getMyInstallationManager(),
                        mainView.getMyCustomer(),
                        myDailyEvent);
            }
        });

        JButton doneButton = new JButton("Done");
        level13Panel.add(doneButton);

        GraphicalArmScheduler myScheduler = this;
        doneButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent) {
                currentFrame.dispatchEvent(new WindowEvent(currentFrame, WindowEvent.WINDOW_CLOSING));

                EventType event;
                switch (armdisarmBox.getSelectedIndex()) {
                    case 0:
                        event = EventType.ARM;
                        break;
                    case 1:
                        event = EventType.DISARM;
                        break;
                    default:
                        event = EventType.NEITHER;
                        break;
                }
                LocalTime myTime = LocalTime.of(hoursBox.getSelectedIndex(),minutesBox.getSelectedIndex());
                boolean phoneCall;
                switch (phoneCallBox.getSelectedIndex()){
                    case 0:
                        phoneCall=false;
                        break;
                    default:
                        phoneCall=true;
                }

                myDailyEvent.setEventType(event);
                myDailyEvent.setTime(myTime);
                myDailyEvent.setPhoneCall(phoneCall);
                int i = myMenu.getMySystem().getScheduler().getDailySchedule().addInOrder(myDailyEvent);
                //myMenu.getDailyModel().fireTableDataChanged();
                mySystem.getScheduler().getDailyModel().fireTableDataChanged();


                LocalDateTime nowDateTime = LocalDateTime.now();
                LocalDateTime myDateTime = LocalDateTime.of(nowDateTime.toLocalDate(), myTime);
                if (myDateTime.compareTo(LocalDateTime.now())<=0)
                    myDateTime = myDateTime.plus(1,ChronoUnit.DAYS);

                ArmingEvent myStandaloneEvent = new ArmingEvent(event, myDateTime, true);
                myStandaloneEvent.setPhoneCall(phoneCall);
                myStandaloneEvent.setActivateArray(myDailyEvent.getActivateArray());
                int j = myMenu.getMySystem().getScheduler().getStandaloneEvents().addInOrder(myStandaloneEvent);
                //myMenu.getStandaloneModel().fireTableDataChanged();
                mySystem.getScheduler().getStandaloneModel().fireTableDataChanged();
            }
        });

        level1Panel.add(Box.createVerticalGlue());
        currentFrame.pack();
        currentFrame.setVisible(true);

    }


    public static void main(String[] args) {
        //GraphicalArmScheduler myScheduler = new GraphicalArmScheduler(new SensorSystem());

    }

}
