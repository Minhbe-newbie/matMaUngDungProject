package android.web.database;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseClass {
    public static Object getUserByMSV(String MSV){
        ResultSet rs = null;
        Connection connect;
        try {
            ConnectionHelper connHelper = new ConnectionHelper();
            connect = connHelper.ConnectionClass();
            if (connect != null) {
                Statement st = connect.createStatement();
                String query = "select *  from sinh_vien sv where MaSinhVien = '"+MSV+"'";
                rs = st.executeQuery(query);
            }
        }catch (Exception e){
            Log.e("Error when connect SQL", e.getMessage());
        }
        return rs;
    }

}
