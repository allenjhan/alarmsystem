package Model; /**
 * Created by ahan on 5/29/17.
 */

import Model.ArmingEvent;
import Model.ArmingEventArrayList;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import javax.swing.table.*;

public class EventScheduler {
    private ArmingEventArrayList standaloneEvents;
    private DailyArmingEventArrayList dailySchedule;
    private AbstractTableModel standaloneModel;
    private AbstractTableModel dailyModel;

    public EventScheduler(){
        standaloneEvents = new ArmingEventArrayList();
        dailySchedule = new DailyArmingEventArrayList();


        final String standaloneTitles[] = new String[]{"Date","Time","Event Type", "Active Sensors","Delete"};
        standaloneModel = new AbstractTableModel(){
            public int getColumnCount(){
                return 5;
            }

            public int getRowCount(){
                return getStandaloneEvents().size();
            }

            public Object getValueAt(int row, int col) {
                switch (col) {
                    case 0:
                        LocalDate currentDate = getStandaloneEvents().get(row).getTime().toLocalDate();
                        return currentDate.toString();
                    case 1:
                        LocalTime currentTime = getStandaloneEvents().get(row).getTime().toLocalTime();
                        return currentTime.toString();
                    case 2:
                        EventType currentType = getStandaloneEvents().get(row).getEventType();
                        if (currentType == EventType.ARM)
                            return "Arm";
                        else if (currentType == EventType.DISARM)
                            return "Disarm";
                        else
                            return "Neither";
                    case 3:
                        return "Set";
                    case 4:
                        return "Delete";
                    default:
                        return null;
                }
            }

            public String getColumnName(int c){
                return standaloneTitles[c];
            }

            public boolean isCellEditable(int row, int column){
                if (column==3 || column ==4)
                    return true;
                else
                    return false;
            }
        };

        final String dailyTitles[] = new String[]{"Time","Event Type", "Active Sensors","Delete"};
        dailyModel = new AbstractTableModel() {
            public int getColumnCount() {
                return 4;
            }

            public int getRowCount() {
                return getDailySchedule().size();
            }

            public Object getValueAt(int row, int col) {
                switch (col) {
                    case 0:
                        LocalTime currentTime;
                        currentTime = getDailySchedule().get(row).getTime();
                        return currentTime.toString();
                    case 1:
                        EventType currentType = getDailySchedule().get(row).getEventType();
                        if (currentType == EventType.ARM)
                            return "Arm";
                        else if (currentType == EventType.DISARM)
                            return "Disarm;";
                        else
                            return "Neither";
                    case 2:
                        return "Set";
                    case 3:
                        return "Delete";
                    default:
                        return null;
                }
            }

            public String getColumnName(int c) {
                return dailyTitles[c];
            }

            public boolean isCellEditable(int row, int column){
                if (column==2 || column ==3)
                    return true;
                else
                    return false;
            }
        };

    }

    /**
     * Getter for the arraylist of standalone events (events set by timer).
     * @return array list of timer events
     */
    public ArmingEventArrayList getStandaloneEvents() {
        return standaloneEvents;
    }

    /**
     * Getter for the arraylist of daily events (events set by scheduler).
     * @return array list of scheduler events
     */
    public DailyArmingEventArrayList getDailySchedule() {
        return dailySchedule;
    }

    /**
     * Obtain the AbstractTableModel of the timer events
     * @return AbstractTableModel of timer events
     */
    public AbstractTableModel getStandaloneModel() {
        return standaloneModel;
    }

    /**
     * Obtain the AbstractTableModel of the scheduler events
     * @return AbstractTableModel of timer events
     */
    public AbstractTableModel getDailyModel() {
        return dailyModel;
    }

    /*
    public void addEvent(EventType eventType, LocalDateTime time, boolean isDailyArmingEvent){
        standaloneEvents.addInOrder(new ArmingEvent(eventType, time, isDailyArmingEvent));
        if(isDailyArmingEvent)
            dailySchedule.addInOrder(new DailyArmingEvent(eventType, time.toLocalTime()));
    }
    */

    /**
     * Remove the event at the head of the timer event arraylist (standaloneEvents), and generate a new
     * event 24 hours later if the event has a listing in the arraylist of scheduled events.
     */
    public void removeSmallestTimeIfOver(){
        if(standaloneEvents.get(0)!=null){
            if(standaloneEvents.get(0).getTime().compareTo(LocalDateTime.now())<0) {
                ArmingEvent removed = standaloneEvents.remove(0);
                if(removed.isDailyArmingEvent()) {
                    for (int j = 0; j < dailySchedule.size(); j++) {
                        if (removed.getTime().toLocalTime().equals(dailySchedule.get(j).getTime()) &&
                                removed.getEventType().equals(dailySchedule.get(j).getEventType())) {
                            dailySchedule.remove(j);
                            break;
                        }
                    }
                }

            }
        }
    }

    /*
    public void removeEvent(int i){
        if(standaloneEvents.get(i)!=null){
            ArmingEvent removed = standaloneEvents.remove(i);
            if(removed.isDailyArmingEvent()) {
                for (int j = 0; j < dailySchedule.size(); j++) {
                    if (removed.getTime().toLocalTime().equals(dailySchedule.get(j).getTime()) &&
                            removed.getEventType().equals(dailySchedule.get(j).getEventType())) {
                        dailySchedule.remove(j);
                        break;
                    }
                }
            }
        }
    }
    */
}
