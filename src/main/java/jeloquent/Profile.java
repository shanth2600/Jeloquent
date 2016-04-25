package jeloquent;

/**
 * Created by shant on 4/25/16.
 */
class Profile extends Model {

    protected String table = "profiles";
    protected String[] fields = {"id","bio"};


    public String User()
    {
        return "jeloquent.User";
    }

    @Override
    protected void instantiateBuilder(){
        this.builder = new Builder(this, this.table,this.fields);
    }

}
