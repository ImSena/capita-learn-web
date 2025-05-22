package br.com.capitalearn.capitalearnweb.dao;

import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.Transaction;

import java.util.List;

public interface TransactionDao {
    void register(Transaction transaction, int userId) throws DBException;
    void findById(Transaction transaction, int userId) throws DBException;
    List<Transaction> findAll(int userId) throws DBException;
    void update(Transaction transaction, int userId) throws DBException;
    void delete(Transaction transaction, int userId) throws DBException;
}
