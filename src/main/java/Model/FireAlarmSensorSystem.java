package Model; /**
 * Created by ahan on 5/29/17.
 */

import java.util.ArrayList;

public class FireAlarmSensorSystem extends SensorSystem {
    private ArrayList<Boolean> sprinklerList;

    public FireAlarmSensorSystem(){
        super();
        sprinklerList = new ArrayList<Boolean>();
        sprinklerList.add(false);
        sprinklerList.add(false);
        sprinklerList.add(false);
        sprinklerList.add(false);
        sprinklerList.add(false);
        sprinklerList.add(false);
        sprinklerList.add(false);
        sprinklerList.add(false);
    }

    /**
     * Getter for the arraylist of sprinklers.
     * @return arraylist of sprinkler states
     */
    public ArrayList<Boolean> getSprinklerList() {
        return sprinklerList;
    }

    /**
     * Overriden version of the method triggerInstalledSensors. Turns on the sprinklers for those sensors that
     * are triggered
     * @param i The index i of the sensor that was the first to be triggered
     * @return return whether the sensor was triggered successfully or not
     */
    public boolean triggerInstalledSensors(int i) {

        boolean trippingEventOccurred = false;
        if (getSensorList().get(i).isInstalled() && ((getSensorList().get(i).isActivated()
                && getSensorList().get(i).isAlarmArmed() && armed)||getSensorList().get(i).isMalfunction())) {


            for (int j = 0; j<getSensorList().size(); j++) {
                if (getSensorList().get(j).isInstalled() && ((getSensorList().get(j).isActivated()
                        && getSensorList().get(j).isAlarmArmed())||getSensorList().get(j).isMalfunction())) {
                    getSensorList().get(j).triggerSensor();
                    sprinklerList.set(j,true);
                }

            }

            armed = false;
            triggered = true;
            if(callCenter)
                timesTriggeredWithCall++;
            return true;
        } else
            return false;

    }
}
