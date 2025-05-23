package br.com.capitalearn.capitalearnweb.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDao {
    protected Connection conn;

    public BaseDao(Connection connection) {
        this.conn = connection;
    }

    protected int getCurrval(String sequence) throws SQLException{
        String sql = "SELECT "+sequence+".CURRVAL FROM dual";

        try(
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ){
            if(rs.next()){
                return rs.getInt(1);
            }else{
                throw new SQLException("CURRVAL não disponível para a sequência: "+sequence);
            }
        }
    }
}
