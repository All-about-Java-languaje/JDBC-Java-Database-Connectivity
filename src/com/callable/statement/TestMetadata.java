package com.callable.statement;

import com.callable.statement.data.OracleConnection;
import java.sql.*;
import oracle.jdbc.OracleResultSetMetaData;
public class TestMetadata {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = OracleConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM employees");

            //Extraemos los metadatos de nuestra consulta en este caso a la tabla employees
            OracleResultSetMetaData resultSetMetaData = (OracleResultSetMetaData) resultSet.getMetaData();

            if (resultSetMetaData == null){
                System.out.println("Metadata not found");
            }else {
                //Get the quantity of columns into this table
                int columnCount = resultSetMetaData.getColumnCount();

                System.out.println("Columns number: " + columnCount);

                for (int i = 1; i <= columnCount; i++){
                    //Get the column name
                    System.out.println("Column name: " + resultSetMetaData.getColumnName(i));

                    //Get column type
                    System.out.println("Column type: " + resultSetMetaData.getColumnTypeName(i));

                    switch (resultSetMetaData.isNullable(i)){
                        case OracleResultSetMetaData.columnNoNulls:
                            System.out.println("Not allow null values");
                            break;
                        case OracleResultSetMetaData.columnNullable:
                            System.out.println("Accept null values");
                            break;
                        case OracleResultSetMetaData.columnNullableUnknown:
                            System.out.println("Null allow unknown");
                    }
                    System.out.println("");
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            OracleConnection.close(resultSet);
            OracleConnection.close(connection);
        }

    }
}
