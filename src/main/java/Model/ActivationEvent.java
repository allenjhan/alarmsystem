package Model;

/**
 * Created by ahan on 6/5/17.
 */
public class ActivationEvent {
    private int[] activateArray;
    private boolean phoneCall;

    public ActivationEvent() {
        activateArray = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
    }

    /**
     * Getter for the activateArray, containing the activation state for the sensors to be modified during the event.
     * @return
     */
    public int[] getActivateArray() {
        return activateArray;
    }

    /**
     * Setter for the activateArrary, containing the activation state for the sensors to be modified during the event.
     * @param activateArray the array containing the activation state for the sensors to be modified during the event
     */
    public void setActivateArray(int[] activateArray) {
        this.activateArray = activateArray;
    }

    /**
     * toggles the activation state of the data member stored at index i
     * @param i the index of the array to toggle
     */
    public void toggleActivate(int i){
        if(activateArray[i]==0)
            activateArray[i]=1;
        else
            activateArray[i]=0;
    }

    /**
     * Getter for whether the event will turn phone calls on or off in the event of trigger.
     * @return boolean which represents whether or not the event will turn phone calls on in the event of trigger
     */
    public boolean isPhoneCall() {
        return phoneCall;
    }

    /**
     * Setter for whether the event will turn phone calls on or off in the event of trigger
     * @param phoneCall boolean which represents whether or not the event will turn phone calls on in the event of trigger
     */
    public void setPhoneCall(boolean phoneCall) {
        this.phoneCall = phoneCall;
    }
}
