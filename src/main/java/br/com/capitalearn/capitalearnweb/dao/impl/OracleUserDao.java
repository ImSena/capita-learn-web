package br.com.capitalearn.capitalearnweb.dao.impl;

import br.com.capitalearn.capitalearnweb.dao.ConnectionManager;
import br.com.capitalearn.capitalearnweb.dao.UserDao;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OracleUserDao implements UserDao {

    private Connection conn;

    @Override
    public void register(User user, double initialBalance) throws DBException {
        PreparedStatement balanceStmt = null;
        PreparedStatement getBalanceIdStmt = null;
        PreparedStatement userStmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionManager.getInstance().getConnection();

            conn.setAutoCommit(false);

            String sqlBalance = "INSERT INTO t_cl_balance(balance_id, balance_amount, created_at, updated_at) " +
                    "VALUES (seq_balance_id.NEXTVAL, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
            balanceStmt = conn.prepareStatement(sqlBalance);
            balanceStmt.setDouble(1, initialBalance);
            balanceStmt.executeUpdate();

            String sqlGetBalanceId = "SELECT seq_balance_id.CURRVAL FROM dual";
            getBalanceIdStmt = conn.prepareStatement(sqlGetBalanceId);
            rs = getBalanceIdStmt.executeQuery();
            int balanceId = -1;
            if (rs.next()) {
                balanceId = rs.getInt(1);
            }

            String sqlUser = "INSERT INTO t_cl_user (user_id, balance_id, email, password_hash, full_name, dt_birth, phone_number, created_at, updated_at, is_active, role) " +
                    "VALUES (seq_user_id.NEXTVAL, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?)";
            userStmt = conn.prepareStatement(sqlUser);
            userStmt.setInt(1, balanceId);
            userStmt.setString(2, user.getEmail());
            userStmt.setString(3, user.getPassword());
            userStmt.setString(4, user.getFullName());
            userStmt.setDate(5, java.sql.Date.valueOf(user.getDateOfBirth()));
            userStmt.setString(6, user.getPhoneNumber());
            userStmt.setString(7, user.isActive() ? "Y" : "N");
            userStmt.setString(8, user.getRole());

            userStmt.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            }catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            throw new DBException("Erro ao cadastrar usuario");
        } finally {
            try {
                if (rs != null) rs.close();
                if (balanceStmt != null) balanceStmt.close();
                if (getBalanceIdStmt != null) getBalanceIdStmt.close();
                if (userStmt != null) userStmt.close();
                if (conn != null) conn.setAutoCommit(true);
                conn.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User findByEmail(String email) throws DBException {
        return null;
    }

    @Override
    public User findById(int id) throws DBException {
        return null;
    }

    @Override
    public List<User> findAll() throws DBException {
        return List.of();
    }

    @Override
    public void update(User user) throws DBException {

    }

    @Override
    public void delete(int id) throws DBException {

    }
}
