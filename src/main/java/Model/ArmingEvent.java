package Model; /**
 * Created by ahan on 5/29/17.
 */

import java.time.LocalDateTime;

public class ArmingEvent extends ActivationEvent implements Comparable<ArmingEvent>{
    private EventType eventType;
    private LocalDateTime time;
    private boolean isDailyArmingEvent;

    public ArmingEvent(){
    }

    public ArmingEvent(EventType eventType, LocalDateTime time, boolean isDailyArmingEvent){
        super();
        this.eventType = eventType;
        this.time = time;
        this.isDailyArmingEvent = isDailyArmingEvent;
    }

    /**
     * Getter for the event type
     * @return returns the event type
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * Setter for the event type
     * @param eventType the event type
     */
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     * Getter for the LocalDateTime of the event
     * @return the LocalDateTime of the event
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Setter for the LocalDateTime of the event
     * @param time the LocalDateTime of the event
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * Getter for whether the event is a scheduled event
     * @return whether the event is a scheduled event
     */
    public boolean isDailyArmingEvent() {
        return isDailyArmingEvent;
    }

    /**
     * Setter for whether the event has a corresponding scheduler event in DailyArmingEventArrayList
     * @param dailyArmingEvent whether the event is a scheduled event
     */
    public void setDailyArmingEvent(boolean dailyArmingEvent) {
        isDailyArmingEvent = dailyArmingEvent;
    }

    /**
     * Compares the caller with the parameter by LocalDateTime
     * @param event the event to be compared to
     * @return -1 if caller is less than parameter, 0 if equal, and +1 if greater than parameter
     */
    public int compareTo(ArmingEvent event){
        return time.compareTo(event.getTime());
    }

}
