package br.com.capitalearn.capitalearnweb.controller.priv;

import br.com.capitalearn.capitalearnweb.controller.base.BaseServlet;
import br.com.capitalearn.capitalearnweb.dao.*;
import br.com.capitalearn.capitalearnweb.factory.DaoFactory;
import br.com.capitalearn.capitalearnweb.model.*;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.InvalidParameterException;

@WebServlet("/transaction")
public class TransactionServlet extends BaseServlet {

    private TransactionDao transactionDao;
    private TransactionRecurringDao transactionRecurringDao;
    private TransactionGoalDao transactionGoalDao;
    private TransactionInvestmentDao transactionInvestmentDao;
    private BalanceDao balanceDao;

    public TransactionServlet() {
        super(true);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        String action = req.getParameter("action");
        switch (action) {
            case "ADD":
                doTransaction(req, resp, action);
            break;
            case "REMOVE":
                doTransaction(req, resp, action);
            default:
                req.setAttribute("error", "Tipo de transação inválida");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    private void doTransaction(HttpServletRequest req, HttpServletResponse resp, String action) throws ServletException, IOException {
        try{
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String transactionType = action;
            String category = req.getParameter("category");
            Double amount = Double.valueOf(req.getParameter("amount"));

            if (title == null || transactionType == null || category == null) {
                throw new InvalidParameterException("Parâmetros obrigatórios ausentes");
            }

            if (amount <= 0) throw new InvalidParameterException("Valor inválido");

            User user = getUser(req);
            Transaction transaction = new Transaction(amount, category, description, user.getId(), transactionType, title);

            switch(category.toUpperCase()){
                case "NORMAL":
                    transactionDao.register(transaction);
                    break;
                case "GOAL":
                    TransactionGoal transactionGoal = new TransactionGoal();
                    transactionGoalDao.register(transactionGoal);
                    break;
                case "INVESTMENT":
                    TransactionInvestment transactionInvestment = new TransactionInvestment();
                    transactionInvestmentDao.register(transactionInvestment);
                    break;
                case "RECURRING":
                    TransactionRecurring transactionRecurring = new TransactionRecurring();
                    transactionRecurringDao.register(transactionRecurring);
                    break;
                default:
                    throw new InvalidParameterException("Categoria inválida");
            }

            req.setAttribute("success", "Transação realizada com sucesso");
        }catch(Exception e){
            e.printStackTrace();
            req.setAttribute("error", "Não foi possível realizar transação");
        }

    }
}
