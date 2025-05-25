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
import java.time.LocalDate;
import java.util.List;

@WebServlet(value = "/edit-recurrents")
public class EditRecurrentsServlet extends BaseServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{

            User user = getUser(req);
            RecurringPayments recurringPayments = new RecurringPayments();
            RecurrentsService recurrentsService = new RecurrentsService();

            int recurrentsId = Integer.parseInt(req.getParameter("recurrentsId"));
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            Double amount = Double.valueOf(req.getParameter("amount"));
            String cycle = req.getParameter("cycle");
            LocalDate nextPaymentDate = LocalDate.parse(req.getParameter("next_payment_date"));

            recurringPayments.setRecurringPaymentId(recurrentsId);
            recurringPayments.setTitle(title);
            recurringPayments.setDescription(description);
            recurringPayments.setAmount(amount);
            recurringPayments.setCycle(cycle);
            recurringPayments.setNextPaymentDate(nextPaymentDate);
            recurringPayments.setUserId(user.getId());

            recurrentsService.edit(recurringPayments);

            req.setAttribute("success", "Pagamento recorrente editado com sucesso!");
            List<RecurringPayments> recurrents = recurrentsService.getRecurrents(user.getId());

            req.setAttribute("recurrents", recurrents);

        }catch (DBException e){
            req.setAttribute("error", e.getMessage());
            e.printStackTrace();
        }

        req.getRequestDispatcher("recurrents.jsp").forward(req, resp);
    }
}
