package com.prepared.statement.laboratory.dao;

import com.prepared.statement.laboratory.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDAO{

    private Connection useConn;

    private static final String INSERT = "INSERT INTO person(name, last_name) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE person SET name = ?, last_name = ? WHERE id_user = ?";
    private static final String DELETE = "DELETE FROM person WHERE id_user = ?";
    private static final String SELECT = "SELECT * FROM person";

    public UserDaoImpl(){}

    public UserDaoImpl(Connection useConn) {
        this.useConn = useConn;
    }

    @Override
    public int insert(UserDTO userDTO) throws SQLException {

        Connection conn = null;
        PreparedStatement statement = null;

        int rows = 0;

        try {
            conn = (this.useConn != null) ? this.useConn : MySQLUserConnection.getConnection();
            statement = conn.prepareStatement(INSERT);

            int index = 1;

            statement.setString(index++, userDTO.getName());
            statement.setString(index, userDTO.getLastName());

            rows = statement.executeUpdate();
        }finally {
            MySQLUserConnection.close(statement);
            if (this.useConn == null){
                MySQLUserConnection.close(conn);
            }
        }
        return rows;
    }

    @Override
    public int update(UserDTO userDTO) throws SQLException {

        Connection conn = null;
        PreparedStatement statement = null;

        int rows = 0;

        try {
            conn = (this.useConn != null) ? this.useConn : MySQLUserConnection.getConnection();
            statement = conn.prepareStatement(UPDATE);

            int index = 1;

            statement.setString(index++, userDTO.getName());
            statement.setString(index++, userDTO.getLastName());
            statement.setInt(index, userDTO.getId_user());

            rows = statement.executeUpdate();
        }finally {
            MySQLUserConnection.close(statement);
            if (this.useConn == null){
                MySQLUserConnection.close(conn);
            }
        }

        return rows;
    }

    @Override
    public int delete(UserDTO userDTO) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;

        int rows = 0;

        try {
            conn = (this.useConn != null) ? this.useConn : MySQLUserConnection.getConnection();
            statement = conn.prepareStatement(DELETE);

            statement.setInt(1, userDTO.getId_user());

            rows = statement.executeUpdate();
        }finally {
            MySQLUserConnection.close(statement);
            if (this.useConn == null){
                MySQLUserConnection.close(conn);
            }
        }
        return rows;
    }

    @Override
    public List<UserDTO> select() throws SQLException {

        List<UserDTO> userDTOList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        UserDTO userDTO = null;

        try {
            conn = (this.useConn != null) ? this.useConn : MySQLUserConnection.getConnection();
            statement = conn.prepareStatement(SELECT);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id_user = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);

                userDTO = new UserDTO(id_user, name, lastName);

                userDTOList.add(userDTO);
            }

        }finally {
            MySQLUserConnection.close(resultSet);
            MySQLUserConnection.close(statement);
            if (this.useConn == null){
                MySQLUserConnection.close(conn);
            }
        }
        return userDTOList;
    }
}
