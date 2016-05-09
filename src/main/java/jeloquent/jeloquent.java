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
//        Post p = new Post();
//        ArrayList ls = p.with(new String[]{"user"}).where("body","=","hey there").get();

//        printList(ls);
        User user = new User();
        ArrayList usr = user.with(new String[]{"profiles","posts"}).where("first_name","=","shant").get();
        printList(usr);

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