package br.com.capitalearn.capitalearnweb.dao;

import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.Transaction;

import java.util.List;

public interface TransactionDao {
    int register(Transaction transaction) throws DBException;
    void findById(Transaction transaction) throws DBException;
    List<Transaction> findAll(int userId) throws DBException;
    void update(Transaction transaction) throws DBException;
    void delete(Transaction transaction) throws DBException;
    List<Transaction> findByMonthYear(int userId, String month, String year) throws DBException;
}
