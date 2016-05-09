package jeloquent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by shant on 4/6/16.
 */
public abstract class Model {

    protected String table;
    protected String[] fields;
    protected Builder builder;

    protected void instantiateBuilder(){
        this.builder = new Builder(this, this.table,this.fields);
    }

    protected void createBuilderIfNotAlreadyCreated()
    {
        if(this.builder == null){
            this.instantiateBuilder();
        }
    }

    public Map find(Integer id)
    {
        createBuilderIfNotAlreadyCreated();
        return this.builder.find(id);
    }

    public Map find(Integer id, String[] fields){
        createBuilderIfNotAlreadyCreated();
        return this.builder.find(id,fields);
    }


    public ArrayList all()
    {
        createBuilderIfNotAlreadyCreated();
        return this.builder.all();
    }


    public ArrayList all(String[] fields)
    {
        createBuilderIfNotAlreadyCreated();
        return this.builder.all(fields);
    }

    public Builder where(String field, String operator, String value){
        createBuilderIfNotAlreadyCreated();
        return this.builder.where(field,operator,value);
    }

    public int create(Map fields)
    {
        createBuilderIfNotAlreadyCreated();
        return this.builder.create(fields);
    }

    public int update(int id, Map fields)
    {
        createBuilderIfNotAlreadyCreated();
        return this.builder.update(id,fields);
    }

    public Builder with(String[] relationships){
        createBuilderIfNotAlreadyCreated();
        this.builder.eager = relationships;
        return builder;
    }


}
