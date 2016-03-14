package jeloquent;

/**
 * Created by Francis on 3/7/16.
 */
public class Model {

    private static String primaryKey;
    private static String table;

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
    private static void create(){}
}
