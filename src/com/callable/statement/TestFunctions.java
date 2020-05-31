package com.callable.statement;

import com.callable.statement.data.OracleConnection;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
public class TestFunctions {
    public static void main(String[] args){
        //Atributos necesarios para realizar las modificaciones o consultas
        int employeeID = 100; //Id del cual deseamos recuperar el salario
        double incrementSalary = 1.1; //Valor a incrementar
        int procedure = 1;//validación de procedimiento

        Connection connection;
        Statement statement = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            if (procedure == 0){

                connection = OracleConnection.getConnection();
                double mouthySalary;

                //The fisrt parameter is our result or the value of mounthySalary, and the second values is the id we want to search
                callableStatement = connection.prepareCall("{ ? = call get_employee_salary(?) }");

                //We register the parameter that gonna receive of the function
                callableStatement.registerOutParameter(1, Types.INTEGER);
                //Register the second parameter or ID
                callableStatement.setInt(2, employeeID);
                callableStatement.execute();
                mouthySalary = callableStatement.getDouble(1);
                callableStatement.close();

                System.out.println("Employee: " + employeeID);
                System.out.println("Salary: " + mouthySalary);
            }else if (procedure == 1){
                connection = OracleConnection.getConnection();
                statement = connection.createStatement();

                //llamamos el Store procedure para incrementar el salario

                System.out.println("Aumento del 10% al salario del empleado: " + employeeID);
                callableStatement = connection.prepareCall("{ call set_employee_salary(?, ?)}");
                callableStatement.setInt(1, employeeID);
                callableStatement.setDouble(2, incrementSalary);
                callableStatement.execute();
                callableStatement.close();

                //Obtenemos el valor del salario con al aumento asignado
                String query = "SELECT first_name, salary FROM employees WHERE employee_id = " + employeeID;
                resultSet = statement.executeQuery(query);
                resultSet.next();
                System.out.println("Empleado: " + resultSet.getString(1));
                System.out.println("Salario: " + resultSet.getFloat(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
