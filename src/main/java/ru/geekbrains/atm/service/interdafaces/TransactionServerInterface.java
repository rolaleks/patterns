package ru.geekbrains.atm.service.interdafaces;

import ru.geekbrains.atm.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionServerInterface {

    public List<Transaction> findAll() ;

    public void save(Transaction transaction);

    public Optional<Transaction> findById(long id) ;
}
