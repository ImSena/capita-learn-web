package br.com.capitalearn.capitalearnweb.dao;

import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.Goal;

import java.util.List;

public interface GoalDao {
    int register(Goal goal) throws DBException;
    List<Goal> findAllByUserId(int userId) throws DBException;
    Goal findById(int id) throws DBException;
    void update(Goal goal) throws DBException;
    void delete(Goal goal) throws DBException;
}
