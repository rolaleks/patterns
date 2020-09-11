package ru.geekbrains.atm.repo;

import org.springframework.stereotype.Service;
import ru.geekbrains.atm.entity.User;

import java.security.Identity;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserMapper extends IdentityMap<User> {


    public User findById(Long id) {

        User user = super.findById(id);
        if (user != null) {
            return user;
        }

        ResultSet resultSet = DbConnection.getInstance().executeQuery("SELECT * FROM users WHERE id = ? LIMIT 1", id);
        try {
            if (resultSet.next()) {
                user = new User();
                this.prepareModel(resultSet, user);
                putEntity(id, user);

                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return null;
    }

    public void prepareModel(ResultSet resultSet, User user) throws SQLException {
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
    }
}
