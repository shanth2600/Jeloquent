package jeloquent;

import java.util.Map;

/**
 * Created by shant on 4/6/16.
 */
public class Post extends Model {

    protected String table = "posts";
    protected String[] fields = {"id","body","title"};


    public String user()
    {
        return "jeloquent.user";
    }


    @Override
    protected void instantiateBuilder(){
        this.builder = new Builder(this, this.table,this.fields);
    }
}
