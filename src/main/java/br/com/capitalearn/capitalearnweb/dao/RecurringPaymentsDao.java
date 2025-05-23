package br.com.capitalearn.capitalearnweb.dao;

import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.RecurringPayments;

import java.util.List;

public interface RecurringPaymentsDao {
    int register(RecurringPayments payment) throws DBException;
    List<RecurringPayments> findAllByUserId(int userId) throws DBException;
    RecurringPayments findById(int id) throws DBException;
    void update(RecurringPayments payment) throws DBException;
    void delete(RecurringPayments payment) throws DBException;
}
