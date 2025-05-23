package br.com.capitalearn.capitalearnweb.factory;

import br.com.capitalearn.capitalearnweb.dao.*;
import br.com.capitalearn.capitalearnweb.dao.impl.*;

import java.sql.Connection;

public class DaoFactory {

    private Connection conn;

    public DaoFactory(Connection conn) {
        this.conn = conn;
    }

    public BalanceDao getBalanceDao() {
        return new OracleBalanceDao(conn);
    }

    public GoalDao getGoalDao(){
        return new OracleGoalDao(conn);
    }

    public InvestmentDao getInvestmentDao(){
        return new OracleInvestmentDao(conn);
    }

    public RecurringPaymentsDao getRecurringPaymentsDao(){
        return new OracleRecurringPayments(conn);
    }

    public TransactionDao getTransactionDao() {
        return new OracleTransactionDao(conn);
    }

    public TransactionGoalDao getTransactionGoalDao() {
        return new OracleTransactionGoalDao(conn);
    }

    public TransactionInvestmentDao getTransactionInvestmentDao() {
        return new OracleTransactionInvestmentDao(conn);
    }

    public TransactionRecurringDao getTransactionRecurringDao() {
        return new OracleTransactionRecurringDao(conn);
    }

    public UserDao getUserDao() {
        return new OracleUserDao(conn);
    }

}
