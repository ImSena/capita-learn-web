package br.com.capitalearn.capitalearnweb.dao.impl;

import br.com.capitalearn.capitalearnweb.dao.ConnectionManager;
import br.com.capitalearn.capitalearnweb.dao.UserDao;
import br.com.capitalearn.capitalearnweb.dao.base.BaseDao;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OracleUserDao extends BaseDao implements UserDao {

    public OracleUserDao(Connection conn){
        super(conn);
    }

    @Override
    public int register(User user) throws DBException {
        PreparedStatement userStmt = null;

        try {
            String sqlUser = "INSERT INTO t_cl_user (user_id, email, password_hash, full_name, dt_birth, phone_number, created_at, updated_at, is_active, role) " +
                    "VALUES (seq_user_id.NEXTVAL, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?)";

            userStmt = conn.prepareStatement(sqlUser);
            userStmt.setString(1, user.getEmail());
            userStmt.setString(2, user.getPassword());
            userStmt.setString(3, user.getFullName());
            userStmt.setDate(4, java.sql.Date.valueOf(user.getDateOfBirth()));
            userStmt.setString(5, user.getPhoneNumber());
            userStmt.setString(6, user.isActive() ? "Y" : "N");
            userStmt.setString(7, user.getRole());

            userStmt.executeUpdate();

            return getCurrval("seq_user_id");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao cadastrar usuário");
        } finally {
            try {
                if (userStmt != null) userStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User findByEmail(String email) throws DBException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            String sql = "SELECT u.user_id, u.email, u.password_hash, u.full_name, u.dt_birth, u.phone_number, " +
                    "u.is_active, u.role, b.balance_amount " +
                    "FROM t_cl_user u " +
                    "JOIN t_cl_balance b ON u.user_id = b.user_id " +
                    "WHERE u.email = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("user_id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password_hash"));
                user.setFullName(rs.getString("full_name"));
                user.setDateOfBirth(rs.getDate("dt_birth").toLocalDate());
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setActive("Y".equals(rs.getString("is_active")));
                user.setRole(rs.getString("role"));
                user.setAmount(rs.getDouble("balance_amount"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao buscar usuário");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
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
        PreparedStatement stmt = null;

        try {
            String sql = "UPDATE t_cl_user SET full_name = ?, email = ?, phone_number = ?, password_hash = ?, updated_at = CURRENT_TIMESTAMP WHERE user_id = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhoneNumber());
            stmt.setString(4, user.getPassword()); // atenção aqui
            stmt.setInt(5, user.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar usuário");
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) throws DBException {

    }
}
