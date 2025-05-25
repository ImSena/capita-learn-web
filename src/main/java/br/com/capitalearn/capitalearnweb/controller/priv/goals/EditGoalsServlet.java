package br.com.capitalearn.capitalearnweb.controller.priv.goals;

import br.com.capitalearn.capitalearnweb.controller.base.BaseServlet;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.Goal;
import br.com.capitalearn.capitalearnweb.model.User;
import br.com.capitalearn.capitalearnweb.service.GoalService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(value = "/edit-goal")

public class EditGoalsServlet extends BaseServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            User user = getUser(req);

            int goalId = Integer.parseInt(req.getParameter("goalId"));
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String priority = req.getParameter("priority");
            String dueDateStr = req.getParameter("dueDate");

            double goalAmount = Double.parseDouble(req.getParameter("goalAmount"));
            String containStr = req.getParameter("goalAmountContain");
            double goalAmountContain = containStr == null || containStr.isEmpty() ? 0.0 : Double.parseDouble(containStr);

            if (title == null || title.isEmpty() ||
                    description == null || description.isEmpty() ||
                    priority == null || priority.equals("Escolher") ||
                    goalAmount < 0 ||
                    goalAmountContain < 0 ||
                    dueDateStr == null || dueDateStr.isEmpty()) {
                throw new IllegalArgumentException("Campos obrigatórios!");
            }

            LocalDate dueDate = LocalDate.parse(dueDateStr);

            Goal goal = new Goal();
            goal.setGoalId(goalId);
            goal.setTitle(title);
            goal.setDescription(description);
            goal.setGoalAmount(goalAmount);
            goal.setGoalAmountContain(goalAmountContain);
            goal.setDueDate(dueDate);
            goal.setPriority(priority);
            goal.setUserId(user.getId());

            GoalService goalService = new GoalService();
            goalService.update(goal);
            req.setAttribute("success", "Meta editada com sucesso!");
            List<Goal> goals = goalService.getGoals(user.getId());
            req.setAttribute("goals", goals);
        }catch(DBException e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        }catch(InvalidParameterException e){
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        }

        req.getRequestDispatcher("/goals.jsp").forward(req, resp);
    }
}
