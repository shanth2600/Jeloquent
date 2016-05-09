package jeloquent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

/**
 * Created by shant on 3/9/16.
 */
public class Builder {

    //public String dbms ="mysql";
    //public String serverName = "opg.local";
    //public String dbName="jeloquent";
    //public Integer portNumber = 3306;
    protected Connection conn = null;
    protected String table;
    protected String[] fields;
    protected String[] eager = {};
    protected String Query = "SELECT *";
    protected List<String[]> where = new ArrayList<>(0);
    protected Model model;

    public Builder(String table, String[] fields, String connectionType){
        this.table = table;
        this.fields = fields;
        /*try{
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            Properties connectionProps = new Properties();
            connectionProps.put("user", "jeloquent");
            connectionProps.put("password", "password");

            if (this.dbms.equals("mysql")) {
                try {


                    this.conn = DriverManager.getConnection("jdbc:mysql://"+this.serverName+":" + this.portNumber + "/"+this.dbName,connectionProps);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (this.dbms.equals("derby")) {
                try {
                    this.conn = DriverManager.getConnection(
                            "jdbc:" + this.dbms + ":" +
                                    this.dbName +
                                    ";create=true",
                            connectionProps);
                } catch (Exception e) {

                }

            }
        }catch(Exception e){

        }*/
        sqlConnection connection = new sqlConnection(connectionType);
        this.conn = connection.startConnection(connectionType);

    }

    public Builder where(String field, String operator, String value)
    {
        String[] whereClause = {field,operator,value};
        this.where.add(whereClause);
        return this;
    }

    public ArrayList get()
    {

        ArrayList list = null;
        list = this.makeMapList(this.runQuery("SELECT * from "+this.table+" "+this.compileWhere()+";"));
        return list;
    }

    public ArrayList get(String[] fields){
        this.fields = fields;
        return this.get();
    }

    protected String compileWhere()
    {
        String whereClause = "where ";
        for(Iterator<String[]> i = this.where.iterator(); i.hasNext(); ) {
            String[] item = i.next();
            whereClause += ""+item[0]+" "+item[1]+" '"+item[2]+"'";
            if(i.hasNext()){
                whereClause += " AND ";
            }
        }
        return whereClause;
    }

    public Map find(Integer id){
        ResultSet rs = this.runQuery("SELECT * from "+table+" where id = "+id+";");
        try{
            rs.next();
        }catch(Exception e){}
        return this.makeMap(rs);
    }

    public Map find(Integer id, String[] fields){
        this.fields = fields;
        return this.find(id);
    }

    public ArrayList all() {

        ResultSet rs = this.runQuery("SELECT * from "+table);
        ArrayList list = this.makeMapList(rs);
        return list;
    }

    public ArrayList all(String[] fields) {
        this.fields = fields;
        return this.all();
    }

    protected int extractId(Object record)
    {
        String id = ((Map) record).get("id").toString();
        return Integer.parseInt(id);
    }

    protected Model instantiateModel(String modelClass)
    {
        try {
            Class<?> clazz = Class.forName(modelClass);
            try {
                Object o = clazz.newInstance();
                return (Model)o;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected String extractRelationship(String modelClass)
    {
        String[] explodedClass = modelClass.split("\\.");
        int index = Math.max(explodedClass.length-1,0);
        return explodedClass[index].toLowerCase();
    }

    protected boolean endsWithS(String str)
    {
        return str.substring(str.length() -1).equals("s");
    }

    protected Map mergeRelationship(String relationship, String modelClass, int id, Map record)
    {
        ArrayList ls = null;
        Model m = instantiateModel(modelClass);
        String fieldName = this.endsWithS(relationship)?this.table.substring(0,this.table.length()-1)+"_id": "id";
        ls = m.where(fieldName,"=",Integer.toString(id)).get();
        record.put(extractRelationship(modelClass),ls);
        return record;
    }

    public static void printList(ArrayList list)
    {
        int i;
        for(i = 0; i < list.size(); i++ ){
            System.out.println(list.get(i).toString());
        }
    }


    protected String extractRelationshipTable(String relationship)
    {
        try {
            Method method = this.model.getClass().getMethod(relationship);
            try {
                Object modelClass = method.invoke(this.model);
                return modelClass.toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return relationship;
    }

    protected ArrayList eagerLoadRelationships(ArrayList ls)
    {
        int i;
        if(this.eager.length > 0){
            for(i = 0; i < this.eager.length; i++){
                Iterator iterator = ls.iterator();
                while(iterator.hasNext()){
                    Object record = iterator.next();
                    int id = this.extractId(record);
                    Map map = this.mergeRelationship(this.eager[i],this.extractRelationshipTable(this.eager[i]),id, (Map)record);
                }
            }

        }
        return ls;
    }

    protected ArrayList makeMapList(ResultSet rs)
    {
        ArrayList list = new ArrayList();
        try {
            while (rs.next()) {
                list.add(makeMap(rs));

            }
        }catch(Exception e){}
        return this.eagerLoadRelationships(list);
    }

    protected Map makeMap(ResultSet rs){
        Map<String, String> map = new HashMap<>();
        try {

            int i = 0;
            for(i = 0; i< this.fields.length; i++){
                map.put(this.fields[i],rs.getString(this.fields[i]));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;

    }

    private String compileCreateQuery(Map fields){
        String query = "INSERT INTO "+this.table;
        String fieldsString = " (";
        String valuesString = "VALUES (";
        Iterator fieldMap = fields.entrySet().iterator();
        while(fieldMap.hasNext()){
            Map.Entry pair = (Map.Entry)fieldMap.next();
            fieldsString += pair.getKey();
            valuesString += "'"+pair.getValue()+"'";
            if(fieldMap.hasNext()){
                fieldsString += ",";
                valuesString += ",";
            }
        }
        fieldsString += ")";
        valuesString += ")";
        return query+fieldsString+valuesString;
    }

    public int create(Map fields){
        return  this.runUpdate(this.compileCreateQuery(fields));
    }

    private String compileUpdateQuery(int id, Map fields){
        String query = "UPDATE "+this.table+" SET ";
        String values = "";
        this.where("id","=",Integer.toString(id));
        String where = " "+this.compileWhere();
        Iterator fieldMap = fields.entrySet().iterator();
        while(fieldMap.hasNext()){
            Map.Entry pair = (Map.Entry)fieldMap.next();
            values += pair.getKey()+"='"+pair.getValue()+"'";
            if(fieldMap.hasNext()){
                values += ",";
            }
        }

        return query+values+where+";";

    }

    public int update(int id, Map fields){
        return this.runUpdate(this.compileUpdateQuery(id,fields));
    }

    public int runUpdate(String query) {
        Statement stmt = null;

        int rv = 0;

        try {
            stmt = this.conn.createStatement();
            rv = stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rv;
    }


    public ResultSet runQuery(String query) {
        Statement stmt = null;

        ResultSet rs = null;
        try {
            stmt = this.conn.createStatement();
            rs = stmt.executeQuery(query);
            return rs;
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }
}
