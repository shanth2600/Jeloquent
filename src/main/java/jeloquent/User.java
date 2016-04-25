package jeloquent;

/**
 * Created by shant on 4/25/16.
 */
public class User extends Model {
    protected String table = "users";
    protected String[] fields = {"id","first_name","last_name","email"};

    public String posts(){
        return "jeloquent.Post";
    }

    public String profiles()
    {
        return "jeloquent.Profile";
    }

    @Override
    protected void instantiateBuilder(){
        this.builder = new Builder(this, this.table,this.fields);
    }
}
