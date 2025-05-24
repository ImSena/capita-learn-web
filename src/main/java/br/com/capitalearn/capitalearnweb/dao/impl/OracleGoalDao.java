package br.com.capitalearn.capitalearnweb.dao.impl;

import br.com.capitalearn.capitalearnweb.dao.ConnectionManager;
import br.com.capitalearn.capitalearnweb.dao.GoalDao;
import br.com.capitalearn.capitalearnweb.dao.base.BaseDao;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.Goal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleGoalDao extends BaseDao implements GoalDao {

    public OracleGoalDao(Connection conn) {
        super(conn);
    }

    @Override
    public int register(Goal goal) throws DBException {
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO t_cl_goal " +
                    "(goal_id, user_id, title, description, goal_amount, due_date, status, created_at, updated_at, priority, goal_amount_contain) " +
                    "VALUES (seq_goal_id.NEXTVAL, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?)";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, goal.getUserId());
            stmt.setString(2, goal.getTitle());
            stmt.setString(3, goal.getDescription());
            stmt.setDouble(4, goal.getGoalAmount());
            stmt.setDate(5, Date.valueOf(goal.getDueDate()));
            stmt.setString(6, goal.getStatus());
            stmt.setString(7, goal.getPriority());
            stmt.setDouble(8, goal.getGoalAmountContain());

            stmt.executeUpdate();

            return getCurrval("seq_goal_id");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Não foi possível registrar Meta");
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Goal> findAllByUserId(int userId) throws DBException {
        List<Goal> goals = new ArrayList<Goal>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM t_cl_goal WHERE user_id = ? ORDER BY due_date ASC";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Goal goal = new Goal();
                goal.setGoalId(rs.getInt("goal_id"));
                goal.setUserId(rs.getInt("user_id"));
                goal.setTitle(rs.getString("title"));
                goal.setDescription(rs.getString("description"));
                goal.setGoalAmount(rs.getDouble("goal_amount"));
                goal.setDueDate(rs.getDate("due_date").toLocalDate());
                goal.setStatus(rs.getString("status"));
                goal.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate());
                goal.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime().toLocalDate());
                goal.setPriority(rs.getString("priority"));
                goal.setGoalAmountContain(rs.getDouble("goal_amount_contain"));

                goals.add(goal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Não foi possível achar metas");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return goals;
    }

    @Override
    public Goal findById(int id) throws DBException {
        return null;
    }

    @Override
    public void update(Goal goal) throws DBException {
        PreparedStatement stmt = null;

        try {
            String sql = "UPDATE t_cl_goal " +
                    "SET title = ?, " +
                    "description = ?, " +
                    "goal_amount = ?, " +
                    "due_date = ?, " +
                    "status = ?, " +
                    "updated_at = CURRENT_TIMESTAMP, " +
                    "priority = ?, " +
                    "goal_amount_contain = ? " +
                    "WHERE goal_id = ?" +
                    "AND user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, goal.getTitle());
            stmt.setString(2, goal.getDescription());
            stmt.setDouble(3, goal.getGoalAmount());
            stmt.setDate(4, Date.valueOf(goal.getDueDate()));
            stmt.setString(5, goal.getStatus());
            stmt.setString(6, goal.getPriority());
            stmt.setDouble(7, goal.getGoalAmountContain());
            stmt.setInt(8, goal.getGoalId());
            stmt.setInt(9, goal.getUserId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Não foi possível editar meta");
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Goal goal) throws DBException {
        PreparedStatement stmt = null;

        try {
            String sql = "DELETE FROM t_cl_goal WHERE goal_id = ? AND user_id = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, goal.getGoalId());
            stmt.setInt(2, goal.getUserId());
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new DBException("Erro ao excluir meta");
        }finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
