package User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAOInterface {
    void InsertUser(User user) throws SQLException;
    void UpdateUser(User user) throws SQLException;
    void DeleteUser(int ID) throws SQLException;
    List<User> listUser() throws SQLException;
    User listUserByID(int ID) throws SQLException;
}
