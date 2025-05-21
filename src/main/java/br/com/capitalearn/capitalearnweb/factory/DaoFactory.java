package br.com.capitalearn.capitalearnweb.factory;

import br.com.capitalearn.capitalearnweb.dao.UserDao;
import br.com.capitalearn.capitalearnweb.dao.impl.OracleUserDao;

public class DaoFactory {
    public static UserDao getUserDao() {
        return new OracleUserDao();
    }
}
