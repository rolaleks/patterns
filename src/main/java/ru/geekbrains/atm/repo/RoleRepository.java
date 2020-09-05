package ru.geekbrains.atm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.atm.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}

