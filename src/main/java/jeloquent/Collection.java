package Jeloquent;
import java.util.*;

/**
 * Created by sargis on 4/3/2016.
 */
public class Collection {
    private ArrayList<Model> Collection = new ArrayList<Model>();
    private int numOfModels;


    Collection() {
        numOfModels = 0;
    }

    public void insert(Model newModel) {
        Collection.add(newModel);
        numOfModels++;
    }

    public int Count() {
        int count = 0;
        Iterator itr = Collection.iterator();
        while(itr.hasNext()){
            itr.next();
            count++;
        }
        return count;
    }

    public boolean isEmpty() {
        if(numOfModels == 0)
            return true;
        else
            return false;
    }
}
