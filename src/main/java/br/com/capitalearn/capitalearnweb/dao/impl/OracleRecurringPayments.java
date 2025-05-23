package br.com.capitalearn.capitalearnweb.dao.impl;

import br.com.capitalearn.capitalearnweb.dao.ConnectionManager;
import br.com.capitalearn.capitalearnweb.dao.RecurringPaymentsDao;
import br.com.capitalearn.capitalearnweb.dao.base.BaseDao;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.RecurringPayments;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleRecurringPayments extends BaseDao implements RecurringPaymentsDao {

    public OracleRecurringPayments(Connection conn) {
        super(conn);
    }

    @Override
    public int register(RecurringPayments payment) throws DBException {
        PreparedStatement stmt = null;
        try{
            String sql = "INSERT INTO t_cl_recurring_payments " +
                    "(recurring_payment_id, amount, description, cycle, next_payment_date, status, created_at, updated_at, user_id) " +
                    "VALUES (seq_recurring_payments_id.NEXTVAL, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, payment.getAmount());
            stmt.setString(2, payment.getDescription());
            stmt.setString(3, payment.getCycle());
            stmt.setDate(4, Date.valueOf(payment.getNextPaymentDate()));
            stmt.setString(5, payment.getStatus());
            stmt.setInt(6, payment.getUserId());

            stmt.executeUpdate();

            return getCurrval("seq_recurring_payments_id");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Não foi possível registrar o pagamento");
        }finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<RecurringPayments> findAllByUserId(int userId) throws DBException {
        List<RecurringPayments> payments = new ArrayList<RecurringPayments>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            String sql = "SELECT * FROM t_cl_recurring_payments WHERE user_id = ? ORDER BY created_at DESC";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                RecurringPayments payment = new RecurringPayments();
                payment.setRecurringPaymentId(rs.getInt("recurring_payment_id"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setDescription(rs.getString("description"));
                payment.setCycle(rs.getString("cycle"));
                payment.setNextPaymentDate(rs.getDate("next_payment_date").toLocalDate());
                payment.setStatus(rs.getString("status"));
                payment.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate());
                payment.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime().toLocalDate());
                payment.setUserId(rs.getInt("user_id"));
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Não foi possível encontrar os pagamentos");
        }finally {
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return payments;
    }

    @Override
    public RecurringPayments findById(int id) throws DBException {
        return null;
    }

    @Override
    public void update(RecurringPayments payment) throws DBException {
        PreparedStatement stmt = null;

        try{
            String sql = "UPDATE t_cl_recurring_payments " +
                    "SET amount = ?, " +
                    "description = ?, " +
                    "cycle = ?, " +
                    "next_payment_date = ?, " +
                    "status = ?, " +
                    "updated_at = CURRENT_TIMESTAMP " +
                    "WHERE recurring_payment_id = ? AND user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, payment.getAmount());
            stmt.setString(2, payment.getDescription());
            stmt.setString(3, payment.getCycle());
            stmt.setDate(4, Date.valueOf(payment.getNextPaymentDate()));
            stmt.setString(5, payment.getStatus());
            stmt.setInt(6, payment.getRecurringPaymentId());
            stmt.setInt(7, payment.getUserId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Não foi possível atualizar o pagamento");
        }finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(RecurringPayments recurringPayments) throws DBException {
        PreparedStatement stmt = null;

        try{
            String sql = "DELETE FROM t_cl_recurring_payments WHERE recurring_payment_id = ? AND user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, recurringPayments.getRecurringPaymentId());
            stmt.setInt(2, recurringPayments.getUserId());
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
