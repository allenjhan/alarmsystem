package Model; /**
 * Created by ahan on 5/29/17.
 */
import java.util.ArrayList;

public class DailyArmingEventArrayList extends ArrayList<DailyArmingEvent>{
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
    public int addInOrder(DailyArmingEvent toAdd){
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
}
