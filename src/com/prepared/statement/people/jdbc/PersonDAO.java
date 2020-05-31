package com.prepared.statement.people.jdbc;


import com.prepared.statement.people.dto.PersonDTO;
import java.sql.SQLException;
import java.util.List;

public interface PersonDAO {
    /**This interface have the basic operations for interact with a DDBB
     * @author Miguel Feo
     */

    public int insert(PersonDTO personDTO) throws SQLException;
    public int update(PersonDTO personDTO) throws SQLException;
    public int delete(PersonDTO personDTO) throws SQLException;
    public List<PersonDTO> select() throws SQLException;
}
