package com.prepared.statement.people.jdbc;

import com.prepared.statement.people.dto.PersonDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoJDBC implements PersonDAO{

    private Connection userConn;

    private final String STRING_INSERT = "INSERT INTO person(name, last_name) VALUES(?, ?)";
    private final String STRING_UPDATE = "UPDATE person SET name, las_name WHERE id_user = ?";
    private final String STRING_DELETE = "DELETE FROM person WHERE id_user = ?";
    private final String STRING_SELECT = "SELECT * FROM person";

    public PersonDaoJDBC(){

    }

    public PersonDaoJDBC(Connection userConn) {
        this.userConn = userConn;
    }

    @Override
    public int insert(PersonDTO personDTO) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;

        int rows = 0;
        try{
            conn = (this.userConn != null) ? this.userConn : MySQLConnection.getConnection();
            statement = conn.prepareStatement(STRING_INSERT);

            int index = 1;
            statement.setString(index++, personDTO.getName());
            statement.setString(index, personDTO.getLastName());
            rows = statement.executeUpdate();

        }finally {
            MySQLConnection.close(statement);
            if (this.userConn == null){
                MySQLConnection.close(conn);
            }
        }
        return rows;
    }

    @Override
    public int update(PersonDTO personDTO) throws SQLException {
        Connection conn =  null;
        PreparedStatement statement = null;

        int rows = 0;

        try {
            conn = (this.userConn != null) ? this.userConn : MySQLConnection.getConnection();
            statement = conn.prepareStatement(STRING_UPDATE);

            int index = 1;
            statement.setString(index++, personDTO.getName());
            statement.setString(index++, personDTO.getLastName());
            statement.setInt(index, personDTO.getId_person());

            rows = statement.executeUpdate();
        }finally {
            MySQLConnection.close(statement);
            if (this.userConn == null){
                MySQLConnection.close(conn);
            }
        }

        return rows;
    }

    @Override
    public int delete(PersonDTO personDTO) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;

        int rows = 0;

        try {
            conn = (this.userConn != null) ? this.userConn : MySQLConnection.getConnection();
            statement = conn.prepareStatement(STRING_DELETE);
            statement.setInt(1, personDTO.getId_person());

            rows = statement.executeUpdate();
        }finally {
            MySQLConnection.close(statement);
            if (this.userConn == null){
                MySQLConnection.close(conn);
            }
        }

        return rows;
    }

    @Override
    public List<PersonDTO> select() throws SQLException {
        List<PersonDTO> personDTOList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        PersonDTO personDTO = null;

        try {
            conn = (this.userConn !=  null) ? this.userConn : MySQLConnection.getConnection();
            statement = conn.prepareStatement(STRING_SELECT);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id_user = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);

                personDTO = new PersonDTO();
                personDTO.setId_person(id_user);
                personDTO.setName(name);
                personDTO.setName(lastName);

                personDTOList.add(personDTO);
            }

        }finally {
            MySQLConnection.close(resultSet);
            MySQLConnection.close(statement);
            if (this.userConn == null){
                MySQLConnection.close(conn);
            }
        }
        return personDTOList;
    }
}
