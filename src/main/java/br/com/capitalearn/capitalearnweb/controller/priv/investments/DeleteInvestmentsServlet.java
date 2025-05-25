package br.com.capitalearn.capitalearnweb.controller.priv.investments;

import br.com.capitalearn.capitalearnweb.controller.base.BaseServlet;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.Investment;
import br.com.capitalearn.capitalearnweb.model.User;
import br.com.capitalearn.capitalearnweb.service.InvestmentService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/delete-investment")
public class DeleteInvestmentsServlet extends BaseServlet {
    public DeleteInvestmentsServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{

            User user = getUser(req);
            int investmentId = Integer.parseInt(req.getParameter("id"));
            Investment investment = new Investment();
            investment.setUserId(user.getId());
            investment.setInvestmentId(investmentId);

            InvestmentService investmentService = new InvestmentService();
            investmentService.delete(investment);
            req.setAttribute("success", "Investimento deletado com sucesso!");
            List<Investment> investments = investmentService.findAll(user.getId());
            req.setAttribute("investments", investments);

        }catch(DBException e){
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        }

        req.getRequestDispatcher("/investments.jsp").forward(req, resp);
    }
}
