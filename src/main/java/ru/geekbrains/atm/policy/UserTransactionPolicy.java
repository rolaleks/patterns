package ru.geekbrains.atm.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.atm.entity.Transaction;
import ru.geekbrains.atm.entity.User;
import ru.geekbrains.atm.notify.HttpNotifier;
import ru.geekbrains.atm.notify.LogManager;
import ru.geekbrains.atm.notify.SlackNotifier;
import ru.geekbrains.atm.service.interdafaces.TransactionServerInterface;
import ru.geekbrains.atm.service.interdafaces.UserServerInterface;

import java.util.Optional;

@Service
public class UserTransactionPolicy {

    @Autowired
    private UserServerInterface userService;

    @Autowired
    private TransactionServerInterface transactionService;

    private LogManager logManager;

    @Autowired
    public UserTransactionPolicy(LogManager logManager) {
        this.logManager = logManager;
        logManager.subscribe("error", new SlackNotifier());
        logManager.subscribe("warning", new HttpNotifier());
    }


    public void createWithdrawTransaction(long userId, float amount) {
        if (amount < 0) {
            logManager.notify("Invalid amount", "error");
            throw new RuntimeException("Invalid amount");
        }

        User user = this.findUser(userId);
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(-1 * amount);
        transactionService.save(transaction);
        logManager.notify("Transaction saved", "warning");
    }

    public void createDepositTransaction(long userId, float amount) {

        if (amount < 0) {
            logManager.notify("Invalid amount", "error");
            throw new RuntimeException("Invalid amount");
        }

        User user = this.findUser(userId);
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(amount);
        transactionService.save(transaction);
        logManager.notify("Transaction saved", "warning");
    }


    private User findUser(long userId) {

        Optional<User> user = userService.findById(userId);

        return user.orElseThrow();
    }
}
