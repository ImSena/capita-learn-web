package br.com.capitalearn.capitalearnweb.controller.priv.investments;

import br.com.capitalearn.capitalearnweb.controller.base.BaseServlet;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.Investment;
import br.com.capitalearn.capitalearnweb.model.User;
import br.com.capitalearn.capitalearnweb.service.InvestmentService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;

@WebServlet(value = "/investments")

public class InvestmentsServlet extends BaseServlet {
    public InvestmentsServlet() {
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
            InvestmentService investmentService = new InvestmentService();
            List<Investment> investments = investmentService.findAll(user.getId());

            req.setAttribute("investments", investments);
        }catch(DBException e){
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        }

        req.getRequestDispatcher("/investments.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            User user = getUser(req);
            String investmentType = req.getParameter("investmentType");
            Double amount = Double.valueOf(req.getParameter("amount"));
            String isRecurring = req.getParameter("isRecurring");

            if(investmentType.isEmpty() || amount <= 0 || isRecurring.isEmpty()){
                throw new InvalidParameterException("Campos obrigatórios");
            }

            Investment investment = new Investment();
            investment.setInvestmentType(investmentType);
            investment.setAmount(amount);
            investment.setIsRecurring(isRecurring);
            investment.setUserId(user.getId());

            InvestmentService investmentService = new InvestmentService();
            investmentService.save(investment);
            req.setAttribute("success", "Ivestimento cadastrado com sucesso!");

            List<Investment> investments = investmentService.findAll(user.getId());
            req.setAttribute("investments", investments);
        }catch(DBException e){
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        }catch(InvalidParameterException e){
            req.setAttribute("error", e.getMessage());
            e.printStackTrace();
        }

        req.getRequestDispatcher("/investments.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
