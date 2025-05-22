package br.com.capitalearn.capitalearnweb.dao;

import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.TransactionInvestment;

import java.util.List;

public interface TransactionInvestmentDao {
    void register(TransactionInvestment investment) throws DBException;
    List<TransactionInvestment> findByTransactionId(int transactionId) throws DBException;
    List<TransactionInvestment> findByInvestmentId(int investmentId) throws DBException;
    void delete(int transactionInvestmentId) throws DBException;
}
