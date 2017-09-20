package Model;

/**
 * Created by ahan on 5/29/17.
 */
public class Sensor {
    private String location;
    private String id;
    private boolean alarmArmed;
    private boolean alarmTriggered;
    private boolean installed;
    private boolean activated;
    private boolean malfunction;

    public Sensor(String location, String id){
        this.location = location;
        this.id = id;
        alarmArmed = false;
        alarmTriggered = false;
        installed = false;
        activated = false;
        malfunction = false;
    }

    /**
     * Getter for the location of the Sensor
     * @return location of the Sensor
     */
    public String getLocation() {
        return location;
    }

    /**
     * Getter for the ID of the Sensor
     * @return ID of the Sensor
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for whether the Sensor is armed
     * @return is the alarm armed for the Sensor
     */
    public boolean isAlarmArmed() {
        return alarmArmed;
    }

    /**
     * Getter for whether the Sensor is triggered.
     * @return is the alarm triggered for the Sensor
     */
    public boolean isAlarmTriggered() {
        return alarmTriggered;
    }

    /**
     * Getter for whether the Sensor is installed
     * @return is the Sensor installed
     */
    public boolean isInstalled(){
        return installed;
    }

    /**
     * Getter for whether the Sensor is activated
     * @return is the Sensor activated
     */
    public boolean isActivated() {return activated;}

    /**
     * Getter for whether the Sensor has a malfunction
     * @return is the Sensor malfunctioning
     */
    public boolean isMalfunction() {
        return malfunction;
    }

    /**
     * Arm the Sensor if possible
     */
    public void armSensor(){
        if(installed==true && activated==true && alarmTriggered==false)
            alarmArmed = true;
    }

    /**
     * Disarm the Sensor if possible
     */
    public void disarmSensor(){
        if(installed==true && activated==true && alarmTriggered==false)
            alarmArmed = false;
    }

    /**
     * Trigger the Sensor if possible
     */
    public void triggerSensor(){
        if(installed==true && ((activated==true && alarmArmed==true)||malfunction==true)) {
            alarmArmed = false;
            alarmTriggered = true;
        }
    }

    /**
     * Reset the Sensor
     */
    public void resetSensor(){
        alarmArmed = false;
        alarmTriggered = false;
    }

    /**
     * Install the Sensor
     */
    public void install(){
        installed = true;
    }

    /**
     * Uninstall the Sensor
     */
    public void uninstall(){
        installed = false;
    }

    /**
     * Toggle the installation state of the Sensor
     */
    public void toggleInstall(){
        if (installed==true)
            installed=false;
        else
            installed=true;
    }

    /**
     * Toggle the activation state of the Sensor
     */
    public void toggleActivate(){
        if (activated==true)
            activated=false;
        else
            activated=true;
    }

    /**
     * Activate the Sensor if possible
     */
    public void activate(){
        /*
        if(installed==true)
            activated=true;
        */
        activated = true;
    }

    /**
     * Deactivate the Sensor if possible
     */
    public void deactivate(){
        /*
        if(installed==true)
            activated=false;
        */
        activated = false;
    }

    /**
     * Toggle the malfunction state of the sensor if possible
     */
    public void toggleMalfunction(){
        if (malfunction==true)
            malfunction=false;
        else
            malfunction=true;
    }
}
