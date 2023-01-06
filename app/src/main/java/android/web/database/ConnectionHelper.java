package android.web.database;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    public Connection ConnectionClass (){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            ConnectionURL = "jdbc:mysql://us-cdbr-east-06.cleardb.net/heroku_f330b4f895ee47c?useUnicode=true&characterEncoding=UTF-8";
            connection = DriverManager.getConnection(ConnectionURL, "b86b0972a3c618", "231b7e11");
        }catch (Exception e){
            Log.e("Error ", e.getMessage());
        }

        return connection;
    }

}
