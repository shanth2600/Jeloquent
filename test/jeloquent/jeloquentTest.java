package jeloquent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by edwige on 4/20/16.
 */
public class jeloquentTest {
    @Test
    public void main() throws Exception {
        Post p = new Post();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("title","herasdsde i am");
        map.put("body","justscrew you");
        p.update(1,map);

//        ArrayList list = p.where("title","=","why").where("id","=","1").get(new String[]{"title"});
//        printList(list);

    }

    @Test
    public void printList(ArrayList list) throws Exception {
        int i;
        for(i = 0; i < list.size(); i++ ){
            System.out.println(list.get(i).toString());
        }
    }

}