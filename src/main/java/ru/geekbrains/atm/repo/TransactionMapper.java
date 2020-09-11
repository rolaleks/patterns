package ru.geekbrains.atm.repo;

import org.springframework.stereotype.Service;
import ru.geekbrains.atm.entity.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class TransactionMapper extends IdentityMap<Transaction> {


    public Transaction findById(Long id) {

        Transaction transaction = super.findById(id);
        if (transaction != null) {
            return transaction;
        }

        ResultSet resultSet = DbConnection.getInstance().executeQuery("SELECT * FROM transactions WHERE id = ? LIMIT 1", id);
        try {
            if (resultSet.next()) {
                transaction = new Transaction();
                this.prepareModel(resultSet, transaction);
                putEntity(id, transaction);

                return transaction;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return null;
    }

    public void prepareModel(ResultSet resultSet, Transaction transaction) throws SQLException {
        transaction.setId(resultSet.getLong("id"));
        transaction.setAmount(resultSet.getFloat("amount"));
    }
}
