package jeloquent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Francis on 3/7/16.
 */
public class Model {

    private static String primaryKey;
    private static String table;
    private static Map<String, String> model;

    public Model() {
        Model.table = "";
        Model.primaryKey = "";
    }
    public Model(String table, String key){
        Model.table = table;
        Model.primaryKey = key;
    }
    public static String getPrimaryKey() {
        return primaryKey;
    }

    public static void setPrimaryKey(String primaryKey) {
        Model.primaryKey = primaryKey;
    }

    public static String getTable() {
        return table;
    }

    public static void setTable(String table) {
        Model.table = table;}
    protected static void create(String table, String pk){
        model = new HashMap<>() ;
        model.put(table, pk);

    }

    protected void find(String key){}
    protected void destroy(String [] keys){}
    protected QueryBuilder where(String field, String operator, String value){}
    protected QueryBuilder hasOne(String relationship){}
    protected QueryBuilder hasMany(String relationship){}
    protected QueryBuilder belongsTo(String relationship){}
    protected QueryBuilder belongsToMany(String relationship){}
    protected QueryBuilder with(String relationship){}

}
