package br.com.capitalearn.capitalearnweb.controller.priv;

import br.com.capitalearn.capitalearnweb.controller.base.BaseServlet;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.Transaction;
import br.com.capitalearn.capitalearnweb.model.User;
import br.com.capitalearn.capitalearnweb.service.TransactionService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/dashboard")

public class DashboardServlet extends BaseServlet {
    public DashboardServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            User user = getUser(req);
            TransactionService transactionService = new TransactionService();
            List<Transaction> transactions = transactionService.getTransactions(user.getId());
            req.setAttribute("transactions", transactions);
        }catch(DBException e){
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        }
        req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            User user = getUser(req);
            String mes = req.getParameter("mes");
            String ano = req.getParameter("ano");

            TransactionService transactionService = new TransactionService();
            List<Transaction> transactions = transactionService.getTransactionByMonth(user.getId(), mes, ano);
            double totalEntradas = 0;
            double totalSaidas = 0;

            for(Transaction t : transactions){
                if("ADD".equals(t.getTransactionType())){
                    totalEntradas += t.getAmount();
                }else{
                    totalSaidas += t.getAmount();
                }
            }

            double saldoFinal = totalEntradas - totalSaidas;

            req.setAttribute("transactions", transactionService.getTransactions(user.getId())); // histórico
            req.setAttribute("relatorioMes", mes);
            req.setAttribute("relatorioAno", ano);
            req.setAttribute("totalEntradas", totalEntradas);
            req.setAttribute("totalSaidas", totalSaidas);
            req.setAttribute("saldoFinal", saldoFinal);

            req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);

        }catch(DBException e){
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
