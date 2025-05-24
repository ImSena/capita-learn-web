package br.com.capitalearn.capitalearnweb.service.base;

import br.com.capitalearn.capitalearnweb.dao.ConnectionManager;
import br.com.capitalearn.capitalearnweb.exception.DBException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class BaseService {
    protected <T> T executeInTransaction(FunctionWithException<Connection, T> action) throws DBException {
        Connection conn = null;
        try {

            conn = ConnectionManager.getInstance().getConnection();
            conn.setAutoCommit(false);

            T result = action.apply(conn);
            conn.commit();
            return result;
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new DBException("Erro ao executar transação", e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void executeInTransactionVoid(ConnectionConsumer action) throws DBException {
        executeInTransaction(conn -> {
            try {
                action.accept(conn);
                return null;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
