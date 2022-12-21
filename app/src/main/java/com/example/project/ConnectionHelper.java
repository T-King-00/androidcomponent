package com.example.project;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionHelper  {
    private static final String TAG = "ConnectionHelper";

    String _user="root";
    String _pass="147258369";
    String _DB="appToDoList";
    String url="jdbc:mysql://192.168.1.113:1432/";
    Connection connect=null;

    private void CreateConnect() {
        try {
            //my codes to connect

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();


           this.connect= DriverManager.getConnection(url,_user,_pass);
            Log.d(TAG, "CreateConnect: connected successfully ");
        }catch ( ClassNotFoundException ex){
            Log.d(TAG,"CreateConnect:  "+ ex.getMessage() );
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    public  Connection getConnection() throws SQLException {
        if (this.connect == null) {
            Log.d(TAG, "DB-getConnection: db obj is null ,created fo first time ");
            CreateConnect();
            Log.d(TAG, "DB-getConnection: db obj is created for first time successfully");
        } else {
            return this.connect;
        }
        return this.connect;
    }


}
