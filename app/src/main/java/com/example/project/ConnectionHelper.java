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



    String _user = "USERaPP";
    String _pass = "tony123...";
    String _DB = "todolistapp";
    String _server = "192.168.1.113:1432";
    //root pass 147258369
   // myPC
    //tony147

    String url="jdbc:mysql://" + _server+"/"+_DB +"?characterEncoding=latin1";



    Connection connect=null;
    // Declare the JDBC objects.
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    private void CreateConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            this.connect= DriverManager.getConnection(url,_user,_pass);
            // com.microsoft.sqlserver.jdbc.SQLServerDriver


            Log.i("CodeDebug", "DB-createConnec: connected successfully ");
        }catch (SQLException | ClassNotFoundException ex){
            Log.e("CodeDebug","DB-createConnec:  "+ ex.getMessage() );
        }


    }

    public  Connection getConnection() throws SQLException {
        if (this.connect == null) {

            Log.e("CodeDebug", "DB-getConnection: db obj is null ,created fo first time ");
            CreateConnect();
            Log.i("CodeDebug", "DB-getConnection: db obj is created for first time successfully");
        } else {
            return connect;
        }
        return connect;
    }


}
