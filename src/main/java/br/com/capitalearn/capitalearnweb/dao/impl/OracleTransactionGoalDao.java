package br.com.capitalearn.capitalearnweb.dao.impl;

import br.com.capitalearn.capitalearnweb.dao.ConnectionManager;
import br.com.capitalearn.capitalearnweb.dao.TransactionGoalDao;
import br.com.capitalearn.capitalearnweb.dao.base.BaseDao;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.TransactionGoal;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class OracleTransactionGoalDao extends BaseDao implements TransactionGoalDao {
    public OracleTransactionGoalDao(Connection conn) {
        super(conn);
    }

    @Override
    public int register(TransactionGoal goal) throws DBException {
        PreparedStatement stmt = null;
        try{
            String sql = "INSERT INTO t_cl_transaction_goal (transaction_goal_id, transaction_id, goal_id) " +
                    "VALUES (seq_transaction_goal_id.NEXTVAL, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, goal.getTransactionGoalId());
            stmt.setInt(2, goal.getTransactionId());
            stmt.executeUpdate();

            return getCurrval("seq_transaction_goal_id");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Não foi possível registrar a transação da meta");
        }finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<TransactionGoal> findByTransactionId(int transactionId) throws DBException {
        List <TransactionGoal> goals = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT * FROM t_cl_transaction_goal WHERE transaction_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, transactionId);
            rs = stmt.executeQuery();

            while (rs.next()) {

                TransactionGoal goal = new TransactionGoal();
                goal.setTransactionGoalId(rs.getInt("transaction_goal_id"));
                goal.setTransactionId(rs.getInt("transaction_id"));
                goal.setGoal_id(rs.getInt("goal_id"));
                goals.add(goal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Não foi possível econtrar as transações da meta");
        }finally {
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return goals;
    }

    @Override
    public List<TransactionGoal> findByGoalId(int goalId) throws DBException {
        List <TransactionGoal> goals = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT * FROM t_cl_transaction_goal WHERE goal_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, goalId);
            rs = stmt.executeQuery();

            while (rs.next()) {

                TransactionGoal goal = new TransactionGoal();
                goal.setTransactionGoalId(rs.getInt("transaction_goal_id"));
                goal.setTransactionId(rs.getInt("transaction_id"));
                goal.setGoal_id(rs.getInt("goal_id"));
                goals.add(goal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Não foi possível encontrar a transação da meta");
        }finally {
            try {
                if (stmt != null) stmt.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return goals;
    }

    @Override
    public void delete(int transactionGoalId) throws DBException {
        PreparedStatement stmt = null;

        try{
            String sql = "DELETE FROM t_cl_transaction_goal WHERE transaction_goal_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, transactionGoalId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Não foi possível atualizar o investimento");
        }finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
