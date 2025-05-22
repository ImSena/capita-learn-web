package br.com.capitalearn.capitalearnweb.dao;

import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.Balance;

public interface BalanceDao{
    void update(Balance balance) throws DBException;
}
