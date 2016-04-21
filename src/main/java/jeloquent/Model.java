package jeloquent;

<<<<<<< HEAD
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
=======
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by shant on 4/6/16.
 */
public abstract class Model {

    protected String table;
    protected String[] fields = {"id","body","title"};
    protected Builder builder;

    public Model()
    {
        this.builder = new Builder("posts",this.fields);
    }

    public Map find(Integer id)
    {
        return this.builder.find(1);
    }

    public Map find(Integer id, String[] fields){
        return this.builder.find(id,fields);
    }


    public ArrayList all()
    {
        return this.builder.all();
    }


    public ArrayList all(String[] fields)
    {
        return this.builder.all(fields);
    }

    public Builder where(String field, String operator, String value){
        return this.builder.where(field,operator,value);
    }

    public int create(Map fields)
    {
        return this.builder.create(fields);
    }

    public int update(int id, Map fields){
        return this.builder.update(id,fields);
    }
>>>>>>> remotes/origin/dev

}
