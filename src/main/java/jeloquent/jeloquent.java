package jeloquent;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shant on 3/7/16.
 */
public class jeloquent {

    public static void main(String[] args){
        Post p = new Post();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("title","herasdsde i am");
        map.put("body","justscrew you");
        p.update(1,map);

//        ArrayList list = p.where("title","=","why").where("id","=","1").get(new String[]{"title"});
//        printList(list);

    }


    public static void printList(ArrayList list)
    {
        int i;
        for(i = 0; i < list.size(); i++ ){
            System.out.println(list.get(i).toString());
        }
    }
}