package br.com.capitalearn.capitalearnweb.dao.impl;

import br.com.capitalearn.capitalearnweb.dao.BalanceDao;
import br.com.capitalearn.capitalearnweb.dao.ConnectionManager;
import br.com.capitalearn.capitalearnweb.dao.base.BaseDao;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.Balance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleBalanceDao extends BaseDao implements BalanceDao {
    public OracleBalanceDao(Connection conn) {
        super(conn);
    }

    @Override
    public int register(Balance balance) throws DBException {
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO t_cl_balance (balance_id, balance_amount, created_at, updated_at, user_id)" +
                    "VALUES (seq_balance_id.NEXTVAL, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, balance.getBalanceAmount());
            stmt.setInt(2, balance.getUserId());

            stmt.executeUpdate();

            return getCurrval("seq_balance_id");
        }catch(SQLException e){
            e.printStackTrace();
            throw new DBException("Erro ao registrar o saldo.");
        }finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Balance balance) throws DBException {
        PreparedStatement stmt = null;
        try {
            String sql = "UPDATE t_cl_balance SET balance_amount = ?, updated_at = CURRENT_TIMESTAMP WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, balance.getBalanceAmount());
            stmt.setInt(2, balance.getUserId());

            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new DBException("Erro ao atualizar o saldo.");
        }finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void findByUserId(int userId) throws DBException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT * FROM t_cl_balance WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
        }catch(SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao buscar o saldo.");
        }finally {
            try{
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
