package br.com.capitalearn.capitalearnweb.dao.impl;

import br.com.capitalearn.capitalearnweb.dao.ConnectionManager;
import br.com.capitalearn.capitalearnweb.dao.InvestmentDao;
import br.com.capitalearn.capitalearnweb.dao.base.BaseDao;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.Investment;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleInvestmentDao extends BaseDao implements InvestmentDao {
    public OracleInvestmentDao(Connection conn) {
        super(conn);
    }

    @Override
    public int register(Investment investment) throws DBException {
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO t_cl_investment (investment_id, user_id, investment_type, amount, is_recurring, created_at, updated_at) " +
                    "VALUES (seq_investment_id.NEXTVAL, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, investment.getUserId());
            stmt.setString(2, investment.getInvestmentType());
            stmt.setDouble(3, investment.getAmount());
            stmt.setString(4, investment.getIsRecurring());

            stmt.executeUpdate();

            return getCurrval("seq_investment_id");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Não foi possível cadastrar o investimento");
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Investment> findAllByUserId(int userId) throws DBException {
        List<Investment> investments = new ArrayList<Investment>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM t_cl_investment WHERE user_id = ? ORDER BY created_at DESC";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Investment investment = new Investment();
                investment.setInvestmentId(rs.getInt("investment_id"));
                investment.setUserId(rs.getInt("user_id"));
                investment.setInvestmentType(rs.getString("investment_type"));
                investment.setAmount(rs.getDouble("amount"));
                investment.setIsRecurring(rs.getString("is_recurring"));
                investment.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate());
                investment.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime().toLocalDate());
                investments.add(investment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Não foi possível resgatar o investimento");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return investments;
    }

    @Override
    public Investment findById(int id) throws DBException {
        return null;
    }

    @Override
    public void update(Investment investment) throws DBException {
        PreparedStatement stmt = null;
        try {
            String sql = "UPDATE t_cl_investment SET " +
                    "investment_type = ?, " +
                    "amount = ?, " +
                    "is_recurring = ?, " +
                    "updated_at = CURRENT_TIMESTAMP " +
                    "WHERE investment_id = ? AND user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, investment.getInvestmentType());
            stmt.setDouble(2, investment.getAmount());
            stmt.setString(3, investment.getIsRecurring());
            stmt.setInt(4, investment.getInvestmentId());
            stmt.setInt(5, investment.getUserId());
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

    @Override
    public void delete(Investment investment) throws DBException {
        PreparedStatement stmt = null;
        try {
            String sql = "DELETE FROM t_cl_investment WHERE investment_id = ? AND user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, investment.getInvestmentId());
            stmt.setInt(2, investment.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Não foi possível deletar o investimento");
        }finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
