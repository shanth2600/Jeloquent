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
//        p.with(new String[]{"jeloquent.User"});
//        ArrayList ls = p.where("body","=","hey there").get();
//        printList(ls);
        User user = new User();
//        user.;
        ArrayList usr = user.with(new String[]{"posts"}).where("first_name","=","shant").get();
        printList(usr);
//        ArrayList ls = null;
//        user.with(new String[]{"Post"});
//        try {
//            Class<?> clazz = Class.forName("jeloquent.Post");
//            try {
//                Object p = clazz.newInstance();
//                ls = ((Post) p).all();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

//        Map rs = null;


//        rs = p.find(1);
//        ls = p.all();
//        ls = p.where("title","=","here i am").where("body","LIKE","just%").get();
//                .where("body","LIKE","just%").get(new String[]{"body"});
//                .where("body","LIKE","just%").get();
//
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("title","just a new post");
//        map.put("body","TERRIBLE day today");
//        p.update(8,map);
//
//        printMap(rs);
//        printMap(usr);
//        printList(ls);
//
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