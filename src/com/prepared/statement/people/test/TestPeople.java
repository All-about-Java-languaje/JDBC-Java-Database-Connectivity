package com.prepared.statement.people.test;

import java.sql.SQLException;

import com.prepared.statement.people.dto.PersonDTO;
import com.prepared.statement.people.jdbc.PersonDAO;
import com.prepared.statement.people.jdbc.PersonDaoJDBC;

public class TestPeople {
    public static void main(String[] args) {
        PersonDAO personDAO = new PersonDaoJDBC();

        PersonDTO personDTO = new PersonDTO();
        personDTO.setName("Miguel");
        personDTO.setLastName("Feo");

        try {
            //We are using the personDAO (An interface) 'cause in some time we need change the implementation and don´t wanna
            //have to change more code lines
            personDAO.insert(personDTO);
        }catch (SQLException e){
            System.out.println("Error inserting new register");
            e.printStackTrace();
        }
    }
}
