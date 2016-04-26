package jeloquent;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shant on 3/7/16.
 */
public class jeloquent {

    // 1. demonstrate find (with and witout fields)
    // 2. demonstrate all (with and witout fields)
    // 3. demonstrate where (with chaining) (here i am)
    // 4. demonstrate create
    // 5. demonstrate update


    public static void main(String[] args){
        User usr = new User();
//        ArrayList rc = usr.all();
//        Map fields = new HashMap<>();
//        fields.put("last_name","jimmy");
//        usr.update(2,fields);
//        fields.put("first_name","jimmy");
//        fields.put("last_name","jimmy");
//        usr.create(fields);
//        printList(rc);
        Post p = new Post();
        ArrayList posts = p.with(new String[]{"user"}).get();
        printList(posts);

//        ArrayList ls = p.with(new String[]{"user"}).where("body","=","hey there").get();
//
//        printList(ls);
//        User user = new User();
//        ArrayList xx = user.with(new String[]{"posts","profiles"}).where("first_name","=","shant").get();
//        printList(xx);

    }

    public static void printMap(Map map)
    {
        System.out.println(map.toString());
    }


    public static void printList(ArrayList list)
    {
        int i;
        for(i = 0; i < list.size(); i++ ){
            System.out.println(list.get(i).toString());
        }
    }
}