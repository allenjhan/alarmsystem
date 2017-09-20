package Model; /**
 * Created by ahan on 5/29/17.
 */

import java.util.ArrayList;

public class SensorSystem {
    protected ArrayList<Sensor> sensorList;
    protected EventScheduler scheduler;
    protected int sensorsInstalledForCurrentMonth;
    protected int timesTriggeredWithCall;
    protected boolean armed;
    protected boolean triggered;
    protected boolean systemInstalled;
    protected boolean systemInstalledBefore;
    protected boolean callCenter;

    public SensorSystem(){
        installSystem(false);
        uninstallSystem();
        sensorsInstalledForCurrentMonth = 0;
        timesTriggeredWithCall = 0;
    }

    /**
     * install the SensorSystem
     * @param registerInstallation if yes, register the installation for computing the invoice
     */
    public void installSystem(boolean registerInstallation){
        armed = false;
        triggered = false;
        systemInstalled = true;

        sensorList = new ArrayList<Sensor>();
        sensorList.add(new Sensor("Northeast room", "R01"));
        sensorList.add(new Sensor("North room", "R02"));
        sensorList.add(new Sensor("Northwest room", "R03"));
        sensorList.add(new Sensor("East Hallway", "H01"));
        sensorList.add(new Sensor("West Hallway", "H02"));
        sensorList.add(new Sensor("Southeast room", "R04"));
        sensorList.add(new Sensor("South room", "R05"));
        sensorList.add(new Sensor("Southwest room", "R06"));

        if(registerInstallation==true)
            systemInstalledBefore = true;

        scheduler = new EventScheduler();
    }

    /**
     * uninstall the SensorSystem
     */
    public void uninstallSystem(){
        armed = false;
        triggered = false;
        systemInstalled = false;

        for(Sensor currentSensor:sensorList){
            currentSensor.uninstall();
        }
    }

    /**
     * Getter for the sensor list arraylist
     * @return return the sensor list
     */
    public ArrayList<Sensor> getSensorList() {
        return sensorList;
    }

    /**
     * Getter for the scheduler of the SensorSystem
     * @return the scheduler
     */
    public EventScheduler getScheduler() {
        return scheduler;
    }

    /**
     * Getter for the maximum number of sensors installed for this SensorSystem for the current month
     * @return maximum number of sensors installed for this SensorSystem for the current month
     */
    public int getSensorsInstalledForCurrentMonth() {
        return sensorsInstalledForCurrentMonth;
    }

    /**
     * Getter for whether the SensorSystem is in an arm state
     * @return arm state
     */
    public boolean isArmed() {
        return armed;
    }

    /**
     * Getter for whether the SensorSystem is in a trigger state
     * @return trigger state
     */
    public boolean isTriggered() {
        return triggered;
    }

    /**
     * Getter for whether the SensorSystem is installed
     * @return whether the SensorSystem is installed
     */
    public boolean isSystemInstalled() {
        return systemInstalled;
    }

    /**
     * Getter for whether the SensorSystem was installed before or not
     * @return
     */
    public boolean isSystemInstalledBefore() {
        return systemInstalledBefore;
    }

    /**
     * Getter for whether to call telephone numbers in the event of trigger state
     * @return whether to call telephone numbers in the event of trigger state
     */
    public boolean isCallCenter() {
        return callCenter;
    }

    /**
     * Getter for the times SensorSystem was triggered with the option to make phone
     * call activated
     * @return number of time SensorSystem was triggered with the option to make phone
     * call activated
     */
    public int getTimesTriggeredWithCall() {
        return timesTriggeredWithCall;
    }

    /**
     * Setter for whether the system has been installed before for the purpose of
     * calculating invoice
     * @param systemInstalledBefore whether the system has been installed before for the purpose
     *                              of calculating invoice
     */
    public void setSystemInstalledBefore(boolean systemInstalledBefore) {
        this.systemInstalledBefore = systemInstalledBefore;
    }

    /**
     * Count the number of sensors currently installed
     * @return the number of sensors currently installed
     */
    public int countSensorsInstalled(){
        int sensorsUsed = 0;
        for(Sensor currentSensor : sensorList){
            if(currentSensor.isInstalled())
                sensorsUsed++;
        }

        return sensorsUsed;
    }

    /**
     * Count the number of sensors currently activated
     * @return the number of sensors currently activated
     */
    public int countSensorsActivated(){
        int sensorsUsed = 0;
        for(Sensor currentSensor : sensorList){
            if(currentSensor.isActivated())
                sensorsUsed++;
        }

        return sensorsUsed;
    }

    /**
     * Count the number of sensors currently with a malfunction
     * @return the number of sensors currently with a malfunction
     */
    public int countSensorsMalfunction(){
        int sensorsUsed = 0;
        for(Sensor currentSensor : sensorList){
            if(currentSensor.isMalfunction())
                sensorsUsed++;
        }

        return sensorsUsed;
    }

    /**
     * Check the number of sensors installed and see if it exceeds the current maximum number
     * of sensors stored by SensorSystem; if so, replace with new maximum
     */
    public void setSensorsInstalledForCurrentMonth(){
        if(countSensorsInstalled()>sensorsInstalledForCurrentMonth)
            sensorsInstalledForCurrentMonth = countSensorsInstalled();
    }

    /*
    public void resetSensorsInstalledForCurrentMonth(){
        sensorsInstalledForCurrentMonth = 0;
    }
    */

    /**
     * Arm the sensors that are both installed and activated
     * @param phoneCall setting to determine whether to make a phone call or not if SensorSystem
     *                  triggered from arm state
     * @return was the arm operation successful?
     */
    public boolean armInstalledAndActivatedSensors(boolean phoneCall){
        boolean atLeastOneSensorInstalledAndActivated = false;
        for(Sensor currentSensor : sensorList){
            if (currentSensor.isInstalled() && currentSensor.isActivated()) {//&& !currentSensor.isAlarmTriggered()) {
                currentSensor.armSensor();
                atLeastOneSensorInstalledAndActivated = true;
                //System.out.println("Model.Sensor Armed");
            }
        }

        if(atLeastOneSensorInstalledAndActivated && triggered==false) {
            if(phoneCall)
                callCenter=true;
            else
                callCenter=false;
            armed = true;
            return true;
        } else
            return false;

    }

    /**
     * Trigger the sensors that are installed, activated, and armed, or only those sensors
     * that are installed and malfunctioning
     * @param i the index for the sensor to detect the fire or break-in
     * @return whether the trigger was initiated sucessfully or not
     */
    public boolean triggerInstalledSensors(int i){
        boolean trippingEventOccurred = false;
        if (getSensorList().get(i).isInstalled() && ((getSensorList().get(i).isActivated()
                && getSensorList().get(i).isAlarmArmed() && armed)||getSensorList().get(i).isMalfunction())) {

            for(Sensor currentSensor:getSensorList()){
                if(currentSensor.isInstalled() && ((currentSensor.isActivated()
                        && currentSensor.isAlarmArmed())||currentSensor.isMalfunction()))
                    currentSensor.triggerSensor();
            }

            armed = false;
            triggered = true;
            if(callCenter)
                timesTriggeredWithCall++;
            return true;
        } else
            return false;
    }

    /**
     * reset all sensors to unarmed, untriggered state
     * @param phoneCall the value of the variable that determines whether a phone call is made
     *                  if the system is triggered; this parameter exists because even when unarmed,
     *                  the system can trigger due to malfunctioning sensors; the Advanced Arming Menu
     *                  has a combobox that allow this state to be set even for a disarm event, so
     *                  this parameter exists as a possible setting
     */
    public void resetAllSensors(boolean phoneCall){
        for(Sensor currentSensor : sensorList){
            currentSensor.resetSensor();
        }

        armed = false;
        triggered = false;
        if(phoneCall)
            callCenter=true;
        else
            callCenter=false;
    }

    /**
     * disarm all sensors to unarmed, if system is not triggered
     * @param phoneCall the value of the variable that determines whether a phone call is made
     *                  if the system is triggered; this parameter exists because even when unarmed,
     *                  the system can trigger due to malfunctioning sensors; the Advanced Arming Menu
     *                  has a combobox that allow this state to be set even for a disarm event, so
     *                  this parameter exists as a possible setting
     * @return whether the disarm operation was successful or not
     */
    public boolean disarmAllSensors(boolean phoneCall){
        if(isTriggered())
            return false;
        else {
            for (Sensor currentSensor : sensorList) {
                currentSensor.disarmSensor();
            }

            armed = false;
            if (phoneCall)
                callCenter = true;
            else
                callCenter = false;

            return true;
        }
    }

    /**
     * install the sensor associated with the index i
     * @param i the index of the sensor to be installed
     */
    public void installSelectedSensor(int i) {
        if (sensorList.get(i) != null) {
            sensorList.get(i).install();
        }
    }

    /*
    public void resetSelectedSensor(int i){
        if(sensorList.get(i)!=null){
            sensorList.get(i).resetSensor();
        }

        checkAllSensorsOff();
    }

    public boolean checkAllSensorsOff(){
        boolean allSensorsOff = true;
        for(Sensor currentSensor : sensorList){
            if(currentSensor.isAlarmArmed() || currentSensor.isAlarmTriggered())
                allSensorsOff = false;
        }

        if (allSensorsOff == true){
            armed = false;
            triggered = false;
        }

        return allSensorsOff;
    }

    */
}
