package br.com.capitalearn.capitalearnweb.service;

import br.com.capitalearn.capitalearnweb.dao.BalanceDao;
import br.com.capitalearn.capitalearnweb.dao.UserDao;
import br.com.capitalearn.capitalearnweb.exception.AuthenticationException;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.factory.DaoFactory;
import br.com.capitalearn.capitalearnweb.model.Balance;
import br.com.capitalearn.capitalearnweb.model.User;
import br.com.capitalearn.capitalearnweb.service.base.BaseService;
import org.mindrot.jbcrypt.BCrypt;

public class UserService extends BaseService {

    public void registerWithBalance(User user) throws DBException {
        executeInTransactionVoid(conn -> {
            DaoFactory daoFactory = new DaoFactory(conn);
            UserDao userDao = daoFactory.getUserDao();
            BalanceDao balanceDao = daoFactory.getBalanceDao();

            int userId = userDao.register(user);

            Balance balance = new Balance();
            balance.setUserId(userId);
            balance.setBalanceAmount(user.getAmount());
            balanceDao.register(balance);

        });
    }

    public User loginWithPassword(String email, String password) throws DBException, AuthenticationException {
        User user =  executeInTransaction(conn -> {
            DaoFactory daoFactory = new DaoFactory(conn);
            UserDao userDao = daoFactory.getUserDao();

            return userDao.findByEmail(email);
        });

        if(user == null || !BCrypt.checkpw(password,user.getPassword())){
            throw new AuthenticationException("Usuário ou senha inválidos");
        }

        return user;
    }

    public void update(User user) throws DBException {
        executeInTransactionVoid(conn -> {
            DaoFactory daoFactory = new DaoFactory(conn);
            UserDao userDao = daoFactory.getUserDao();
            userDao.update(user);
        });
    }

}
