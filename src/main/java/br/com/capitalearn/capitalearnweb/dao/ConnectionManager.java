package br.com.capitalearn.capitalearnweb.dao;

import br.com.capitalearn.capitalearnweb.exception.DBException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ConnectionManager connectionManager;

    private ConnectionManager() {

    }

    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }

    public Connection getConnection() throws DBException {
        Connection connection = null;

        try{

            Class.forName("oracle.jdbc.driver.OracleDriver");

            connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl",
                    "RM559252", "210105");

        }catch(ClassNotFoundException | SQLException e){
            throw new DBException("Erro ao obter conexão com o banco de dados.");
        }

        return connection;
    }
}
