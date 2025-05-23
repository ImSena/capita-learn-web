package br.com.capitalearn.capitalearnweb.dao.impl;

import br.com.capitalearn.capitalearnweb.dao.ConnectionManager;
import br.com.capitalearn.capitalearnweb.dao.TransactionGoalDao;
import br.com.capitalearn.capitalearnweb.dao.TransactionInvestmentDao;
import br.com.capitalearn.capitalearnweb.dao.base.BaseDao;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.TransactionInvestment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleTransactionInvestmentDao extends BaseDao implements TransactionInvestmentDao {
    public OracleTransactionInvestmentDao(Connection conn) {
        super(conn);
    }

    @Override
    public int register(TransactionInvestment investment) throws DBException {
        PreparedStatement stmt = null;
        PreparedStatement stmtId = null;
        try {
            String sql = "INSERT INTO t_cl_transaction_investment (transaction_investment_id, transaction_id, investment_id) " +
                    "VALUES (seq_transaction_investment_id.NEXTVAL, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, investment.getTransactionId());
            stmt.setInt(2, investment.getInvestmentId());
            stmt.executeUpdate();

            return getCurrval("seq_transaction_investment_id");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Não foi possível registrar a transação de investimento");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (stmtId != null) stmtId.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<TransactionInvestment> findByTransactionId(int transactionId) throws DBException {
        List<TransactionInvestment> investments = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM t_cl_transaction_investment WHERE transaction_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, transactionId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                TransactionInvestment investment = new TransactionInvestment();
                investment.setTransactionInvestmentId(rs.getInt("transaction_investment_id"));
                investment.setTransactionId(rs.getInt("transaction_id"));
                investment.setInvestmentId(rs.getInt("investment_id"));
                investments.add(investment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao buscar transações por investimento");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return investments;
    }

    @Override
    public List<TransactionInvestment> findByInvestmentId(int investmentId) throws DBException {
        List<TransactionInvestment> investments = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM t_cl_transaction_investment WHERE investment_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, investmentId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                TransactionInvestment investment = new TransactionInvestment();
                investment.setTransactionInvestmentId(rs.getInt("transaction_investment_id"));
                investment.setTransactionId(rs.getInt("transaction_id"));
                investment.setInvestmentId(rs.getInt("investment_id"));
                investments.add(investment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao buscar investimentos por transação");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return investments;
    }

    @Override
    public void delete(int transactionInvestmentId) throws DBException {
        PreparedStatement stmt = null;
        try {
            String sql = "DELETE FROM t_cl_transaction_investment WHERE transaction_investment_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, transactionInvestmentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao excluir vínculo transação-investimento");
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
