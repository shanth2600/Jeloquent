package jeloquent;

import java.sql.*;
import java.util.*;

/**
 * Created by shant on 3/9/16.
 */
public class Builder {

    public String dbms ="mysql";
    public String serverName = "opg.local";
    public String dbName="jeloquent";
    public Integer portNumber = 3306;
    protected Connection conn = null;
    protected String table;
    protected String[] fields;
    protected String Query = "SELECT *";
    protected List<String[]> where = new ArrayList<>(0);

    public Builder(String table, String[] fields){
        this.table = table;
        this.fields = fields;
        try{
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

        }


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

    protected ArrayList makeMapList(ResultSet rs)
    {
        ArrayList list = new ArrayList();
        try {
            while (rs.next()) {
                list.add(makeMap(rs));

            }
        }catch(Exception e){}
        return list;
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
