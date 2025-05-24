package br.com.capitalearn.capitalearnweb.service;

import br.com.capitalearn.capitalearnweb.dao.*;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.factory.DaoFactory;
import br.com.capitalearn.capitalearnweb.model.*;
import br.com.capitalearn.capitalearnweb.service.base.BaseService;

import java.util.List;

public class TransactionService extends BaseService {

    public Balance registerTransaction(Transaction transaction) throws DBException, IllegalArgumentException {
        return executeInTransaction(conn -> {
            DaoFactory daoFactory = new DaoFactory(conn);
            TransactionDao transactionDao = daoFactory.getTransactionDao();
            TransactionGoalDao transactionGoalDao = daoFactory.getTransactionGoalDao();
            TransactionInvestmentDao transactionInvestmentDao = daoFactory.getTransactionInvestmentDao();
            TransactionRecurringDao transactionRecurringDao = daoFactory.getTransactionRecurringDao();
            BalanceDao balanceDao = daoFactory.getBalanceDao();

            int transactionId = transactionDao.register(transaction);

            switch(transaction.getCategory().toUpperCase()){
                case "GOAL":
                    TransactionGoal goal = new TransactionGoal();
                    goal.setTransactionId(transactionId);
                    transactionGoalDao.register(goal);
                    break;
                case "INVESTMENT":
                    TransactionInvestment investment = new TransactionInvestment();
                    investment.setTransactionId(transactionId);
                    transactionInvestmentDao.register(investment);
                    break;
                case "RECURRING":
                    TransactionRecurring recurring = new TransactionRecurring();
                    recurring.setTransactionId(transactionId);
                    transactionRecurringDao.register(recurring);
                    break;
                case "NORMAL":
                    break;
                default:
                    throw new IllegalArgumentException("Categoria inválida: " + transaction.getCategory());
            }

            Balance balance = balanceDao.findByUserId(transaction.getUserId());
            double updatedAmount = balance.getBalanceAmount();

            if ("ADD".equalsIgnoreCase(transaction.getTransactionType())) {
                updatedAmount += transaction.getAmount();
            } else if ("REMOVE".equalsIgnoreCase(transaction.getTransactionType())) {
                updatedAmount -= transaction.getAmount();
            } else {
                throw new IllegalArgumentException("Tipo de transação inválido: " + transaction.getTransactionType());
            }

            balance.setBalanceAmount(updatedAmount);
            balanceDao.update(balance);
            return balance;
        });

    }

    public List<Transaction> getTransactions(int userId) throws DBException
    {
        return executeInTransaction(conn -> {
            DaoFactory daoFactory = new DaoFactory(conn);
            TransactionDao transactionDao = daoFactory.getTransactionDao();

            return transactionDao.findAll(userId);
        });
    }

    public List<Transaction> getTransactionByMonth(int userId, String month, String year) throws DBException
    {
        return executeInTransaction(conn -> {
            DaoFactory daoFactory = new DaoFactory(conn);
            TransactionDao transactionDao = daoFactory.getTransactionDao();
            return transactionDao.findByMonthYear(userId, month, year);
        });
    }

}
