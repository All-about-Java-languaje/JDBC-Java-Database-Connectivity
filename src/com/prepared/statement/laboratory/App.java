package com.prepared.statement.laboratory;

import com.prepared.statement.laboratory.dao.UserDAO;
import com.prepared.statement.laboratory.dao.UserDaoImpl;
import com.prepared.statement.laboratory.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDaoImpl();
        UserDTO userDTO = new UserDTO(1, "Miguel Angel", "Feo Linares");

        try {
            userDAO.select();
            List<UserDTO> userDTOList = userDAO.select();
            for (UserDTO userDTO1: userDTOList){
                System.out.println(userDTO1.getId_user() + " " +
                userDTO1.getName() + " " +
                userDTO1.getLastName() );

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
