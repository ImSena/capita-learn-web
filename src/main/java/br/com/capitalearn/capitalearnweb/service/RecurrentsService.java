package br.com.capitalearn.capitalearnweb.service;

import br.com.capitalearn.capitalearnweb.dao.RecurringPaymentsDao;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.factory.DaoFactory;
import br.com.capitalearn.capitalearnweb.model.RecurringPayments;
import br.com.capitalearn.capitalearnweb.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;

public class RecurrentsService extends BaseService {

    public List<RecurringPayments> getRecurrents(int userId) throws DBException {
        return executeInTransaction(conn->{
            DaoFactory daoFactory = new DaoFactory(conn);
            RecurringPaymentsDao recurringPaymentsDao = daoFactory.getRecurringPaymentsDao();
            return recurringPaymentsDao.findAllByUserId(userId);
        });
    }

    public void save(RecurringPayments recurringPayments) throws DBException {
        executeInTransactionVoid(conn->{
            DaoFactory daoFactory = new DaoFactory(conn);
            RecurringPaymentsDao recurringPaymentsDao = daoFactory.getRecurringPaymentsDao();
            recurringPaymentsDao.register(recurringPayments);
        });
    }

    public void delete(RecurringPayments recurringPayments) throws DBException {
        executeInTransactionVoid(conn->{
            DaoFactory daoFactory = new DaoFactory(conn);
            RecurringPaymentsDao recurringPaymentsDao = daoFactory.getRecurringPaymentsDao();
            recurringPaymentsDao.delete(recurringPayments);
        });
    }

    public void edit(RecurringPayments recurringPayments) throws DBException {
        executeInTransactionVoid(conn->{
            DaoFactory daoFactory = new DaoFactory(conn);
            RecurringPaymentsDao recurringPaymentsDao = daoFactory.getRecurringPaymentsDao();
            recurringPaymentsDao.update(recurringPayments);
        });
    }

}
