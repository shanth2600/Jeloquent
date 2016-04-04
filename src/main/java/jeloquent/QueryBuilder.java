package jeloquent;

import java.util.ArrayList;
import java.util.List;

/**
 * QueryBuilder class with methods:
 * Select
 * Where
 * Compile
 */
public class QueryBuilder {
    private String select = "SELECT *";
    private List<String[]> where = new ArrayList<>(0);
    protected String table;

    //Builds SELECT statement and returns this
    private QueryBuilder select(String[] str){
        if(str.length == 0)
            return this;
        else {
            select = "SELECT ";
            for(int i = 0; i < str.length; i++){
                if(i == str.length - 1)
                    select += str[i];
                else
                    select += str[i] + ", ";
             }
        }
        select += "FROM " + table + " ";
        return this;
    }
    //
    private void where(String field, String operator, String value){
        String[] whereClause = {field,operator,value};
        where.add(whereClause);

    }
    //Returns select statement with where's chained by ands.
    private String compile(){
        String whereChained = "WHERE ";
        //iterate through List
        for(int i = 0; i < where.size(); i++){
            for(int j = 0; j < 3; j++) {
                //surround string with single quotes
                if(j == 2){
                    whereChained += "'"; whereChained += where.get(i)[j]; whereChained += "' ";
                }
                else
                    whereChained += where.get(i)[j] + " ";
            }
            //Check if last Clause
            if(i != where.size() -1)
                whereChained += "AND ";
            else
                whereChained += ";";
        }
        select += whereChained;
        return select;
    }
}
