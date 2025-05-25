package br.com.capitalearn.capitalearnweb.service;

import br.com.capitalearn.capitalearnweb.dao.InvestmentDao;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.factory.DaoFactory;
import br.com.capitalearn.capitalearnweb.model.Investment;
import br.com.capitalearn.capitalearnweb.service.base.BaseService;

import java.util.List;

public class InvestmentService extends BaseService {

    public void save(Investment investment) throws DBException {
        executeInTransactionVoid(conn -> {
            DaoFactory daoFactory = new DaoFactory(conn);
            InvestmentDao investmentDao = daoFactory.getInvestmentDao();
            investmentDao.register(investment);
        });
    }

    public List<Investment> findAll(int userId) throws DBException {
        return executeInTransaction(conn -> {
            DaoFactory daoFactory = new DaoFactory(conn);
            InvestmentDao investmentDao = daoFactory.getInvestmentDao();
            return investmentDao.findAllByUserId(userId);
        });
    }

    public void edit(Investment investment) throws DBException {
        executeInTransactionVoid(conn -> {
           DaoFactory daoFactory = new DaoFactory(conn);
           InvestmentDao investmentDao = daoFactory.getInvestmentDao();
           investmentDao.update(investment);
        });
    }

    public void delete(Investment investment) throws DBException {
        executeInTransactionVoid(conn -> {
            DaoFactory daoFactory = new DaoFactory(conn);
            InvestmentDao investmentDao = daoFactory.getInvestmentDao();
            investmentDao.delete(investment);
        });
    }


}
