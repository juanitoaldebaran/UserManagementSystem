package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements UserDAOInterface {

    private static Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void InsertUser(User user) throws SQLException {
        String insertQuery = "INSERT INTO user_table (id, name, email, age, role) VALUES (?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setInt(1, user.getID());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getAge());
            statement.setString(5,user.getRole().name());

            statement.executeUpdate();
        }
    }

    @Override
    public void UpdateUser(User user) throws SQLException {
        String updateQuery = "UPDATE user_table SET name = ?, email = ?, age = ?, role = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getAge());
            statement.setString(4, String.valueOf(user.getRole()));
            statement.setInt(5, user.getID());

            statement.executeUpdate();
        }
    }

    @Override
    public void DeleteUser(int ID) throws SQLException {
        String deleteQuery = "DELETE FROM user_table WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, ID);

            statement.executeUpdate();
        }
    }

    @Override
    public List<User> listUser() throws SQLException {
        List<User> usersList = new ArrayList<>();
        String listUserQuery = "SELECT * FROM user_table";
        try (PreparedStatement statement = connection.prepareStatement(listUserQuery)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Role roleType = Role.valueOf(String.valueOf(rs.getString("role")));
                    if (roleType.equals(Role.ADMIN)) {
                        usersList.add(new AdminRole(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getInt("age")
                        ));
                    } else if (roleType.equals(Role.USER)) {
                        usersList.add(new UserRole(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getInt("age")
                        ));
                    } else if (roleType.equals(Role.GUEST)) {
                        usersList.add(new AdminRole(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getInt("age")
                        ));
                    }
                }
            }
            return usersList;
        }
    }

    @Override
    public User listUserByID(int ID) throws SQLException {
        String listUserByIDQuery = "SELECT * FROM user_table WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(listUserByIDQuery)) {
            statement.setInt(1, ID);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Role userType = Role.valueOf(String.valueOf(rs.getString("role")));
                    if (userType.equals(Role.ADMIN)) {
                        return new AdminRole(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getInt("age")
                        );
                    } else if (userType.equals(Role.USER)) {
                        return new UserRole(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getInt("age")
                        );
                    } else if (userType.equals(Role.GUEST)) {
                        return new GuestRole(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getInt("age")
                        );
                    }
                }
            }
        }
        return null;
    }
}