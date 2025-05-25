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
import java.util.List;

@WebServlet(value = "/deletar-goal")
public class DeleteGoalsServlet extends BaseServlet {
    public DeleteGoalsServlet() {
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

            int idGoal = Integer.parseInt(req.getParameter("id"));

            Goal goal = new Goal();
            goal.setGoalId(idGoal);
            goal.setUserId(user.getId());

            GoalService goalService = new GoalService();
            goalService.delete(goal);
            List<Goal> goals = goalService.getGoals(user.getId());

            req.setAttribute("success", "Meta deletada com sucesso!");
            req.setAttribute("goals", goals);
        }catch(DBException e){
            e.printStackTrace();
            req.setAttribute("error", "Erro ao excluir o goal!");
        }

        req.getRequestDispatcher("/goals.jsp").forward(req, resp);
    }
}
