package br.com.capitalearn.capitalearnweb.dao;

import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.Balance;

import java.sql.Connection;

public interface BalanceDao{
    void update(Balance balance) throws DBException;
    Balance findByUserId(int userId) throws DBException;
    int register(Balance balance) throws DBException;
}
