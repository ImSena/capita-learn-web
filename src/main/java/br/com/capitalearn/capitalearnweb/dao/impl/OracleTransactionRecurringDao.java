package br.com.capitalearn.capitalearnweb.dao.impl;

import br.com.capitalearn.capitalearnweb.dao.ConnectionManager;
import br.com.capitalearn.capitalearnweb.dao.TransactionRecurringDao;
import br.com.capitalearn.capitalearnweb.dao.base.BaseDao;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.TransactionRecurring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleTransactionRecurringDao extends BaseDao implements TransactionRecurringDao {
    public OracleTransactionRecurringDao(Connection conn) {
        super(conn);
    }

    @Override
    public int register(TransactionRecurring recurring) throws DBException {
        PreparedStatement stmt = null;
        PreparedStatement stmtId = null;
        try {
            String sql = "INSERT INTO t_cl_transaction_recurring (transaction_recurring_id, transaction_id, recurring_id) " +
                    "VALUES (seq_transaction_recurring_id.NEXTVAL, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, recurring.getTransactionId());
            stmt.setInt(2, recurring.getRecurringId());
            stmt.executeUpdate();

            return getCurrval("seq_transaction_recurring");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Não foi possível registrar a transação recorrente");
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
    public List<TransactionRecurring> findByTransactionId(int transactionId) throws DBException {
        List<TransactionRecurring> recurrents = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM t_cl_transaction_recurring WHERE transaction_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, transactionId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                TransactionRecurring recurring = new TransactionRecurring();
                recurring.setTransactionRecurringId(rs.getInt("transaction_recurring_id"));
                recurring.setTransactionId(rs.getInt("transaction_id"));
                recurring.setRecurringId(rs.getInt("recurring_id"));
                recurrents.add(recurring);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao buscar transações recorrentes por transação");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return recurrents;
    }

    @Override
    public List<TransactionRecurring> findByRecurringId(int recurringId) throws DBException {
        List<TransactionRecurring> recurrents = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM t_cl_transaction_recurring WHERE recurring_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, recurringId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                TransactionRecurring recurring = new TransactionRecurring();
                recurring.setTransactionRecurringId(rs.getInt("transaction_recurring_id"));
                recurring.setTransactionId(rs.getInt("transaction_id"));
                recurring.setRecurringId(rs.getInt("recurring_id"));
                recurrents.add(recurring);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao buscar transações por recorrência");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return recurrents;
    }

    @Override
    public void delete(int transactionRecurringId) throws DBException {
        PreparedStatement stmt = null;
        try {
            String sql = "DELETE FROM t_cl_transaction_recurring WHERE transaction_recurring_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, transactionRecurringId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao excluir vínculo transação-recorrente");
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
