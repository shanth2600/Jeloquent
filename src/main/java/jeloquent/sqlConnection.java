package jeloquent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by anthony on 4/12/16.
 */
public class sqlConnection {
    public String dbms ="mysql";
    public String serverName = "opg.local";
    public String dbName="jeloquent";
    public Integer portNumber = 3306;
    protected Connection conn = null;

    public sqlConnection(String connectionType){
        this.startConnection(connectionType);
    }

    private void startConnection(String connectionType){
        try {
            switch (connectionType) {
                case "mySQL":
                    this.mySQL();
                    break;
                case "sqLite":
                    System.out.println("Error: No Connection Type Defined For SQLITE");
                    break;
            }
        } catch (Exception e){
            System.out.println("Connection type unavailable");
        }

    }

    private void sqLite(){
        //TODO add sqlite connection
    }

    private void mySQL(){
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
                    System.out.println("Connection failed.");
            }

        }
    }
}

