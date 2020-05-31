package com.cursor;

import com.callable.statement.data.OracleConnection;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;

import java.sql.*;

public class TestCursors {
    public static void main(String[] args){
        //Instanciamos esta clase de oracle para poder procesar el cursor que regresa la función de oracle
        OracleCallableStatement oracleCallableStatement = null;
        OracleResultSet oracleResultSet = null;

        try {
            Connection connection = OracleConnection.getConnection();
            //Llamamos la función que creamos dentro del cursor en Oracle
            oracleCallableStatement = (OracleCallableStatement) connection.prepareCall("{? = call ref_cursor_package.get_dept_ref_cursor(?)}");

            //Indicamos el tipo de regreso, en este caso un cursor
            oracleCallableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            oracleCallableStatement.setInt(2, 200);
            oracleCallableStatement.execute();

            //Recuperamos el resultSet y lo convertimos a un tipo Oracle
            oracleResultSet = (OracleResultSet) oracleCallableStatement.getCursor(1);

            while (oracleResultSet.next()){
                System.out.println("Id_departament: " + oracleResultSet.getInt(1));
                System.out.println("Name_departament: " + oracleResultSet.getString(2));
                System.out.println("Location: " + oracleResultSet.getString(3) + "\n");
            }
            oracleCallableStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
