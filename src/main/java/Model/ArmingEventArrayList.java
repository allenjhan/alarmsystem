package Model; /**
 * Created by ahan on 5/29/17.
 */
import Model.ArmingEvent;

import java.util.ArrayList;

public class ArmingEventArrayList extends ArrayList<ArmingEvent>{
    /**
     * A sorting method for the arraylist that sorts by LocalDateTime
     */
    public void sort(){
        for(int i = 1; i<this.size(); i++){
            for(int j = 0; j<i; j++){
                if(this.get(i).compareTo(this.get(j))<0) {
                    this.add(j, this.remove(i));
                    break;
                }
            }
        }
    }

    /**
     * Adds an event into the arraylist so that the arraylist remains sorted
     * @param toAdd event to add
     * @return returns the index where the event was inserted
     */
    public int addInOrder(ArmingEvent toAdd){
        int i;
        for(i = 0; i<this.size(); i++){
            if( toAdd.compareTo(this.get(i))<0 ){
                this.add(i,toAdd);
                return i;
            }
        }
        this.add(i,toAdd);
        return i;
    }

    /**
     * Deletes an event from the arraylist
     * @param toRemove event to remove
     */
    public void delete(ArmingEvent toRemove){
        for(int i = 0; i<=this.size(); i++) {
            if(this.get(i)==toRemove) {
                this.remove(i);
                break;
            }
        }
    }
}
