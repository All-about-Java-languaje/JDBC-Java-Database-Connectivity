package com.prepared.statement.laboratory.dao;

import com.prepared.statement.laboratory.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    public int insert(UserDTO userDTO) throws SQLException;
    public int update(UserDTO userDTO) throws SQLException;
    public int delete(UserDTO userDTO) throws SQLException;
    public List<UserDTO> select() throws SQLException;
}
