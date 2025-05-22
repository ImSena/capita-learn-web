package br.com.capitalearn.capitalearnweb.dao;

import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.TransactionRecurring;

import java.util.List;

public interface TransactionRecurringDao {
    void register(TransactionRecurring recurring) throws DBException;
    List<TransactionRecurring> findByTransactionId(int transactionId) throws DBException;
    List<TransactionRecurring> findByRecurringId(int recurringId) throws DBException;
    void delete(int transactionRecurringId) throws DBException;
}
