package br.com.capitalearn.capitalearnweb.dao;

import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.Investment;

import java.sql.Connection;
import java.util.List;

public interface InvestmentDao {
    int register(Investment investment) throws DBException;
    List<Investment> findAllByUserId(int userId) throws DBException;
    Investment findById(int id) throws DBException;
    void update(Investment investment) throws DBException;
    void delete(Investment investment) throws DBException;
}
