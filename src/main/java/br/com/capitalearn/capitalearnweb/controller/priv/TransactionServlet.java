package br.com.capitalearn.capitalearnweb.controller.priv;

import br.com.capitalearn.capitalearnweb.controller.base.BaseServlet;
import br.com.capitalearn.capitalearnweb.dao.*;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.factory.DaoFactory;
import br.com.capitalearn.capitalearnweb.model.*;
import br.com.capitalearn.capitalearnweb.service.TransactionService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.InvalidParameterException;

@WebServlet("/transaction")
public class TransactionServlet extends BaseServlet {

    private TransactionService transactionService;

    public TransactionServlet() {
        super(true);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        transactionService = new TransactionService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String redirectTo = req.getParameter("redirectTo");

        if(redirectTo == null || redirectTo.isEmpty()) {
            redirectTo = "/dashboard";
        }

        try{
            if(action == null || action.isEmpty() || (!action.equals("ADD") && !action.equals("REMOVE"))){
                throw new InvalidParameterException("Tipo de transação inválida");
            }

            handleTransaction(req, resp, action);
            req.setAttribute("success", "Transação realizada com sucesso");
        }catch(DBException e){
            e.printStackTrace();
            req.setAttribute("error", "Não foi possível realizar a transação");
        }catch(InvalidParameterException e){
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        }

        req.getRequestDispatcher(redirectTo).forward(req, resp);
        resp.sendRedirect(redirectTo);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    private void handleTransaction(HttpServletRequest req, HttpServletResponse resp, String action) throws ServletException, IOException, DBException, InvalidParameterException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String category = req.getParameter("category");
        String transactionType = action;
        String amountStr = req.getParameter("amount");

        if (title == null || category == null || amountStr == null || title.isBlank() || category.isBlank() || amountStr.isBlank()) {
            throw new InvalidParameterException("Parâmetros obrigatórios ausentes");
        }

        Double amount = Double.valueOf(amountStr);
        if (amount <= 0) {
            throw new InvalidParameterException("Valor inválido");
        }

        User user = getUser(req);
        Transaction transaction = new Transaction(amount, category, description, user.getId(), transactionType, title);

        Balance balance = transactionService.registerTransaction(transaction);

        req.getSession().setAttribute("balance", balance.getBalanceAmount());
    }
}
