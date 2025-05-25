package br.com.capitalearn.capitalearnweb.controller.priv.recurrents;

import br.com.capitalearn.capitalearnweb.controller.base.BaseServlet;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.RecurringPayments;
import br.com.capitalearn.capitalearnweb.model.User;
import br.com.capitalearn.capitalearnweb.service.RecurrentsService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/delete-recurrents")
public class DeleteRecurrentsServlet extends BaseServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            User user = getUser(req);
            int id = Integer.parseInt(req.getParameter("id"));
            RecurringPayments recurringPayments = new RecurringPayments();
            recurringPayments.setRecurringPaymentId(id);
            recurringPayments.setUserId(user.getId());

            RecurrentsService recurrentsService = new RecurrentsService();
            recurrentsService.delete(recurringPayments);
            req.setAttribute("success", "Pagamento deletado com sucesso!");

            List<RecurringPayments> recurrents = recurrentsService.getRecurrents(user.getId());
            req.setAttribute("recurrents", recurrents);
        }catch (DBException e){
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        }

        req.getRequestDispatcher("/recurrents.jsp").forward(req, resp);
    }
}
