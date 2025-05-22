package br.com.capitalearn.capitalearnweb.dao;

import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.TransactionGoal;

import java.util.List;

public interface TransactionGoalDao{
    void register(TransactionGoal goal) throws DBException;
    List<TransactionGoal> findByTransactionId(int transactionId) throws DBException;
    List<TransactionGoal> findByGoalId(int goalId) throws DBException;
    void delete(int transactionGoalId) throws DBException;

}
