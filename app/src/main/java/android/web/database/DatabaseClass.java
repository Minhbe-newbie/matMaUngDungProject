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
    public static int updatePassword(String hashPass, String MSV){
        int rs;
        Connection connect;
        try {
            ConnectionHelper connHelper = new ConnectionHelper();
            connect = connHelper.ConnectionClass();
            if (connect != null) {
                Statement st = connect.createStatement();
                String query = "update sinh_vien set Pass = '"+hashPass+"' where MaSinhVien = '"+MSV+"'";
                System.out.println("Query: "+ query);
                rs = st.executeUpdate(query);
                return rs;
            }
        }catch (Exception e){
            Log.e("Error when connect SQL", e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }
    public static int updateInfor(String MSV, String phone, String email, String address){
        int rs;
        Connection connect;
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.ConnectionClass();
            if(connect != null){
                Statement st = connect.createStatement();
                String query = "update sinh_vien set SoDienThoai='"+phone+"', Email='"+email+"', QueQuan='"+address+"' where MaSinhVien = '"+ MSV+"'";
                System.out.println("Query: "+ query);
                rs = st.executeUpdate(query);
                return rs;
            }
        }catch (Exception e){
            Log.e("Error when connect SQL", e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }
}
