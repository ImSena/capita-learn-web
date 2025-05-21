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
import java.time.LocalDate;

@WebServlet(value = "/cadastro")
public class RegisterServlet extends HttpServlet {

    private UserDao dao;

    public RegisterServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = DaoFactory.getUserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("cadastro.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Chamou a servlet");
        try{

            String username = req.getParameter("username");
            String dt_birth = req.getParameter("dt_birth");
            String email = req.getParameter("email");
            String contato = req.getParameter("contato");
            Double saldo = Double.valueOf(req.getParameter("saldo"));
            String password = req.getParameter("password");
            String confirmaPassword = req.getParameter("confirmPassword");

            if (!password.equals(confirmaPassword)) {
                resp.sendRedirect("cadastro.jsp?status=error&msg=Senhas não coincidem");
                return;
            }

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

            LocalDate dataNascimento = LocalDate.parse(dt_birth);

            User user = new User(username, dataNascimento, email, contato, saldo, hashedPassword);
            user.setRole("CLIENT");
            user.setActive(true);

            dao.register(user);

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/cadastro.jsp?status=success");

        }catch (DBException e){
            e.printStackTrace();
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/cadastro.jsp?status=error");
        }catch(Exception e){
            e.printStackTrace();
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/cadastro.jsp?status=error");
            System.out.println("ERRO NO CADASTRO:");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
