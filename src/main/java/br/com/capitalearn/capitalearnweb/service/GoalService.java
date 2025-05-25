package br.com.capitalearn.capitalearnweb.service;

import br.com.capitalearn.capitalearnweb.dao.GoalDao;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.factory.DaoFactory;
import br.com.capitalearn.capitalearnweb.model.Goal;
import br.com.capitalearn.capitalearnweb.service.base.BaseService;

import java.util.List;

public class GoalService extends BaseService {

    public List<Goal> getGoals(int userId) throws DBException {
        return executeInTransaction(conn ->{
            DaoFactory daoFactory = new DaoFactory(conn);
            GoalDao goalDao = daoFactory.getGoalDao();
            return goalDao.findAllByUserId(userId);
        });
    }

    public void save(Goal goal) throws DBException {
        executeInTransactionVoid(conn -> {
            DaoFactory daoFactory = new DaoFactory(conn);
            GoalDao goalDao = daoFactory.getGoalDao();

            goalDao.register(goal);
        });
    }

    public void update(Goal goal) throws DBException {
        executeInTransactionVoid(conn -> {
            DaoFactory daoFactory = new DaoFactory(conn);
            GoalDao goalDao = daoFactory.getGoalDao();
            goalDao.update(goal);
        });
    }

    public void delete(Goal goal) throws DBException {
        executeInTransactionVoid(conn -> {
            DaoFactory daoFactory = new DaoFactory(conn);
            GoalDao goalDao = daoFactory.getGoalDao();
            goalDao.delete(goal);
        });
    }

}
