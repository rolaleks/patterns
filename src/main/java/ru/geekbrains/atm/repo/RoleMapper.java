package ru.geekbrains.atm.repo;

import org.springframework.stereotype.Service;
import ru.geekbrains.atm.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class RoleMapper extends IdentityMap<Role> {


    public Role findById(Long id) {

        Role role = super.findById(id);
        if (role != null) {
            return role;
        }

        ResultSet resultSet = DbConnection.getInstance().executeQuery("SELECT * FROM roles WHERE id = ? LIMIT 1", id);
        try {
            if (resultSet.next()) {
                role = new Role();
                this.prepareModel(resultSet, role);
                putEntity(id, role);

                return role;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return null;
    }

    public void prepareModel(ResultSet resultSet, Role role) throws SQLException {
        role.setId(resultSet.getLong("id"));
        role.setName(resultSet.getString("name"));
    }
}
