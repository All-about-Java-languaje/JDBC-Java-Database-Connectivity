package com.prepared.statement.laboratory.dao;

import java.sql.*;

public class MySQLUserConnection {

    private static final String JDBC_URL = "jdbc:mysql://localhost/person?useSS=false&ServerTimeZone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static Driver driver = null;

    public static synchronized Connection getConnection() throws SQLException{
        if (driver == null){
            try {
                Class JdbcClassDriver = Class.forName(JDBC_DRIVER);
                driver = (Driver) JdbcClassDriver.newInstance();
                DriverManager.registerDriver(driver);
            }catch (Exception e){
                System.out.println("Wrong loading Driver");
                e.printStackTrace();
            }
        }
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    public static void close(PreparedStatement statement){
        try{
            if (statement == null){
                statement.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public static void close(ResultSet resultSet){
        try{
            if (resultSet == null){
                resultSet.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void close(Connection connection){
        try{
            if (connection == null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
