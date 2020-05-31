package com.prepared.statement.people.jdbc;

import java.sql.*;
import java.sql.Driver;

public class MySQLConnection {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost/person?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "";
    private static Driver driver = null;

    public static synchronized Connection getConnection() throws SQLException{
        if (driver == null){
            try {
                Class JdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) JdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            }catch (Exception e){
                System.out.println("Wrong loading driver");
                e.printStackTrace();
            }
        }
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    public static void close(Connection conn){
        try {
            if (conn == null){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement statement){
        try {
            if (statement == null){
                statement.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void close(ResultSet resultSet){
        try {
            if (resultSet == null){
                resultSet.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
