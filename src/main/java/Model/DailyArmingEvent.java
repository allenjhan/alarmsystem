package Model; /**
 * Created by ahan on 5/29/17.
 */

import java.time.LocalTime;

public class DailyArmingEvent extends ActivationEvent implements Comparable<DailyArmingEvent>{
    private EventType eventType;
    private LocalTime time;

    public DailyArmingEvent(){}

    public DailyArmingEvent(EventType eventType, LocalTime time) {
        super();
        this.eventType = eventType;
        this.time = time;
    }

    /**
     * Getter for the event type
     * @return event type
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * Setter for the event type
     * @param eventType event type
     */
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     * getter for the time of scheduler event, as LocalTime
     * @return time of scheduler event
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * setter for time of scheduler event, as LocalTime
     * @param time time of scheduler event
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Compares the caller with the parameter by LocalTime
     * @param event the event to be compared to
     * @return -1 if caller is less than parameter, 0 if equal, and +1 if greater than parameter
     */
    public int compareTo(DailyArmingEvent event){
        return time.compareTo(event.getTime());
    }
}
