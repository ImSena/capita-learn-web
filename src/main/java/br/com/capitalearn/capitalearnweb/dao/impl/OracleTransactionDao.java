package br.com.capitalearn.capitalearnweb.dao.impl;

import br.com.capitalearn.capitalearnweb.dao.ConnectionManager;
import br.com.capitalearn.capitalearnweb.dao.TransactionDao;
import br.com.capitalearn.capitalearnweb.dao.base.BaseDao;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OracleTransactionDao extends BaseDao implements TransactionDao {

    public OracleTransactionDao(Connection conn) {
        super(conn);
    }

    @Override
    public int register(Transaction transaction) throws DBException {
        PreparedStatement stmt = null;

        try {

            String sql = "INSERT INTO t_cl_transaction" +
                    "(transaction_id, amount, category, description, created_at, updated_at, user_id, transaction_type, title) " +
                    "VALUES(seq_transaction_id.NEXTVAL, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?, ?)";

            stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, transaction.getAmount());
            stmt.setString(2, transaction.getCategory());
            stmt.setString(3, transaction.getDescription());
            stmt.setInt(4, transaction.getUserId());
            stmt.setString(5, transaction.getTransactionType());
            stmt.setString(6, transaction.getTitle());

            stmt.executeUpdate();

            return getCurrval("seq_transaction_id");

        } catch (SQLException e) {
            throw new DBException("Erro ao registrar transação");
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void findById(Transaction transaction) throws DBException {

    }

    @Override
    public List<Transaction> findAll(int userId) throws DBException {
        List<Transaction> transactions = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            String sql = "SELECT * FROM t_cl_transaction WHERE user_id = ? ORDER BY created_at DESC";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            while(rs.next()){
                int transactionId = rs.getInt("transaction_id");
                double amount = rs.getDouble("amount");
                String category = rs.getString("category");
                String description = rs.getString("description");
                LocalDate createdAt = rs.getDate("created_at").toLocalDate();
                LocalDate updatedAt = rs.getDate("updated_at").toLocalDate();
                int user = rs.getInt("user_id");
                String transactionType = rs.getString("transaction_type");
                String title = rs.getString("title");

                Transaction transaction = new Transaction(transactionId, amount, category, description, createdAt, updatedAt, user, transactionType, title);

                transactions.add(transaction);
            }

        }catch(SQLException e){
            e.printStackTrace();
            throw new DBException("Erro ao resgatar transações.");
        } finally {
            try {
                if(rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return transactions;
    }

    @Override
    public List<Transaction> findByMonthYear(int userId, String month, String year) throws DBException {
        List<Transaction> list = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            String sql = "SELECT * FROM t_cl_transaction WHERE user_id = ? AND TO_CHAR(created_at, 'MM') = ? AND TO_CHAR(created_at, 'YYYY') = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setString(2, month);
            stmt.setString(3, year);

            rs = stmt.executeQuery();

            while(rs.next()){
                Transaction t = new Transaction();
                t.setTransactionId(rs.getInt("transaction_id"));
                t.setTitle(rs.getString("title"));
                t.setAmount(rs.getDouble("amount"));
                t.setCategory(rs.getString("category"));
                t.setDescription(rs.getString("description"));
                t.setTransactionType(rs.getString("transaction_type"));
                t.setCreatedAt(LocalDate.from(rs.getTimestamp("created_at").toLocalDateTime()));
                t.setUpdatedAt(LocalDate.from(rs.getTimestamp("updated_at").toLocalDateTime()));
                t.setUserId(rs.getInt("user_id"));
                list.add(t);
            }

        }catch(SQLException e){
            throw new DBException("Erro ao buscar transações por mês e ano", e);
        }finally {
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return list;
    }

    @Override
    public void update(Transaction transaction) throws DBException {
        PreparedStatement stmt = null;

        try{
            String sql = "UPDATE t_cl_transaction " +
                    "SET amount = ?, " +
                    "category = ?, " +
                    "description = ?, " +
                    "updated_at = CURRENT_DATE, " +
                    "transaction_type = ?, " +
                    "title = ? " +
                    "WHERE transaction_id = ? AND user_id = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, transaction.getAmount());
            stmt.setString(2, transaction.getCategory());
            stmt.setString(3, transaction.getDescription());
            stmt.setString(4, transaction.getTransactionType());
            stmt.setString(5, transaction.getTitle());
            stmt.setInt(6, transaction.getTransactionId());
            stmt.setInt(7, transaction.getUserId());

            stmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
            throw new DBException("Erro ao atualizar transação");
        }finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Transaction transaction) throws DBException {
        PreparedStatement stmt = null;

        try{
            conn = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM t_cl_transaction WHERE transaction_id = ? AND user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, transaction.getTransactionId());
            stmt.setInt(2, transaction.getUserId());

            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new DBException("Erro ao deletar transação");
        }finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
