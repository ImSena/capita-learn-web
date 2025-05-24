package br.com.capitalearn.capitalearnweb.controller.priv;

import br.com.capitalearn.capitalearnweb.controller.base.BaseServlet;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.model.Goal;
import br.com.capitalearn.capitalearnweb.model.User;
import br.com.capitalearn.capitalearnweb.service.GoalService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(value = "/goals")
public class GoalsServlet extends BaseServlet {
    public GoalsServlet() {
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

            GoalService goalService = new GoalService();
            List<Goal> goals = goalService.getGoals(user.getId());
            req.setAttribute("goals", goals);
        }catch(DBException e){
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        }
        req.getRequestDispatcher("/goals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = getUser(req);

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
            goal.setTitle(title);
            goal.setDescription(description);
            goal.setGoalAmount(goalAmount);
            goal.setGoalAmountContain(goalAmountContain);
            goal.setDueDate(dueDate);
            goal.setPriority(priority);
            goal.setUserId(user.getId());

            GoalService goalService = new GoalService();
            goalService.save(goal);

            List<Goal> goals = goalService.getGoals(user.getId());
            req.setAttribute("goals", goals);

            req.setAttribute("success", "Meta cadastrada com sucesso!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
        }

        req.getRequestDispatcher("/goals.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
