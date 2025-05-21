package br.com.capitalearn.capitalearnweb.controller.publ;

import br.com.capitalearn.capitalearnweb.dao.UserDao;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.factory.DaoFactory;
import br.com.capitalearn.capitalearnweb.model.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {

    private UserDao dao;
    public LoginServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = DaoFactory.getUserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            User user = dao.findByEmail(email);

            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                req.getSession().setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/dashboard.jsp"); // ou dashboard
            } else {
                req.setAttribute("error", "Usuário ou senha inválidos");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } catch (DBException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro no servidor");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
