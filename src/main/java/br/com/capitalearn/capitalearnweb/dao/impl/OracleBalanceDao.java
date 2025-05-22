package br.com.capitalearn.capitalearnweb.dao.impl;

import br.com.capitalearn.capitalearnweb.dao.BalanceDao;
import br.com.capitalearn.capitalearnweb.dao.ConnectionManager;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.Balance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OracleBalanceDao implements BalanceDao {
    private Connection conn;

    @Override
    public void update(Balance balance) throws DBException {
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.getInstance().getConnection();
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
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
