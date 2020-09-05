package ru.geekbrains.atm.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.atm.entity.Transaction;
import ru.geekbrains.atm.entity.User;
import ru.geekbrains.atm.service.interdafaces.TransactionServerInterface;
import ru.geekbrains.atm.service.interdafaces.UserServerInterface;

import java.util.Optional;

@Service
public class UserTransactionPolicy {

    @Autowired
    private UserServerInterface userService;

    @Autowired
    private TransactionServerInterface transactionService;


    public void createWithdrawTransaction(long userId, float amount) {
        if (amount < 0) {
            throw new RuntimeException("Invalid amount");
        }

        User user = this.findUser(userId);
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(-1 * amount);
        transactionService.save(transaction);
    }

    public void createDepositTransaction(long userId, float amount) {

        if (amount < 0) {
            throw new RuntimeException("Invalid amount");
        }

        User user = this.findUser(userId);
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(amount);
        transactionService.save(transaction);
    }


    private User findUser(long userId) {

        Optional<User> user = userService.findById(userId);

        return user.orElseThrow();
    }
}
