package ru.geekbrains.atm.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.atm.entity.Transaction;
import ru.geekbrains.atm.repo.TransactionRepository;
import ru.geekbrains.atm.service.interdafaces.TransactionServerInterface;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements TransactionServerInterface {

    private TransactionRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Transaction> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void save(Transaction transaction) {
        repository.save(transaction);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Transaction> findById(long id) {
        return repository.findById(id);
    }
}
