package jeloquent;

import org.json.*;
import com.google.gson.*;



import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by sargis on 4/3/2016.
 */
public class Collection {
    private ArrayList<Model> Collection = new ArrayList<Model>();
    private int numOfModels;

    private Builder builder = new Builder();

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
        while (itr.hasNext()) {
            itr.next();
            count++;
        }
        return count;
    }

    public boolean isEmpty() {
        if (numOfModels == 0)
            return true;
        else
            return false;
    }

    public void toJson() throws SQLException, JSONException {

        ResultSet result = builder.runQuery("SELECT * FROM " + builder.table);
        JSONArray jar = new JSONArray();
        JSONObject obj = new JSONObject();
        JSONArray lst = new JSONArray();

        ResultSetMetaData meta = result.getMetaData();
        int numberOfColumn = meta.getColumnCount();
        for(int i = 0; i<=numberOfColumn; i++){
            jar.put(String.valueOf(meta.getColumnName(i)));
            obj.put("Column", jar);
        }
        System.out.println();

        while (result.next()){
            for (int i =1; i<=numberOfColumn; i++){
                lst.put(String.valueOf(result.getObject(i)));
                obj.put("",lst);
            }
        }
        System.out.println();
        Gson gson = new Gson();
        String mes = gson.toJson(obj);
        System.out.println(builder.table + ":"+mes);

    }
}
