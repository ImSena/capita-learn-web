package br.com.capitalearn.capitalearnweb.dao;

import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.User;

import java.util.List;

public interface UserDao {
    void register(User user, double initialBalance) throws DBException;
    User findByEmail(String email) throws DBException;
    User findById(int id) throws DBException;
    List<User> findAll() throws DBException;
    void update(User user) throws DBException;
    void delete(int id) throws DBException;
}
