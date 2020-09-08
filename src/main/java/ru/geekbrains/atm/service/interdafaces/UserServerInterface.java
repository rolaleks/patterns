package ru.geekbrains.atm.service.interdafaces;

import ru.geekbrains.atm.entity.User;

import java.util.List;
import java.util.Optional;

interface UserServerInterface {

    public List<User> findAll() ;

    public void save(User user);

    public Optional<User> findById(long id) ;
}
