package com.callable.statement.data;

import java.sql.*;
import java.util.*;

public class OracleConnection {
    private static String JDBC_DRIVER;
    private static String JDBC_URL;
    private static String JDBC_USER;
    private static String JDBC_PASS;
    private static Driver driver = null;

    //A diferencia de la conexion que realizamos con MySQL, se proporcionan los datos de conexion en un archivo distinto
    private static String JDBC_FILE_NAME = "jdbc";

    /**Para hacer el proceso de extraccion de los datos dentro del archivo, creamos el metodo loadProperties
     * Cabe aclarar que este metodo debe recibir un archivo de tipo .properties
     * @param file
     * @return
     */
    public static Properties loadProperties(String file){
        //Este objeto de tipo Properties es similar a un Map, pues contiene una llave y un valor
        Properties properties = new Properties();
        ResourceBundle bundle = ResourceBundle.getBundle(file);
        Enumeration e = bundle.getKeys();
        String key = null;
        //Este ciclo se va a iterar hasta que no haya más valores
        while (e.hasMoreElements()){
            key = (String) e.nextElement();
            properties.put(key, bundle.getObject(key));
        }
        JDBC_DRIVER = properties.getProperty("driver");
        JDBC_URL = properties.getProperty("url");
        JDBC_USER = properties.getProperty("user");
        JDBC_PASS = properties.getProperty("pass");

        return properties;
    }

    /**
     * This method return one Object of kind "Connection" that we gonna use to connect to Oracle Database
     * @return Connection
     * @throws SQLException
     */
    public static synchronized Connection getConnection() throws SQLException{
        if (driver == null){
            try {
                //Cargamos las propiedades para acceder a la base de datos
                loadProperties(JDBC_FILE_NAME);

                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);

            }catch (Exception e){
                System.out.println("Fail loading Driver");
                e.printStackTrace();
            }
        }
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    /**
     *
     * @param resultSet
     */
    public static void close(ResultSet resultSet){
        try {
            if (resultSet != null){
                resultSet.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param statement
     */
    public static void close(PreparedStatement statement){
        try {
            if (statement != null){
                statement.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param connection
     */
    public static void close(Connection connection){
        try {
            if (connection != null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
