package jeloquent;

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

}
