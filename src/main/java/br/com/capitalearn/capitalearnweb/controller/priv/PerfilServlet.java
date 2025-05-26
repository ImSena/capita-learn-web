package br.com.capitalearn.capitalearnweb.controller.priv;

import br.com.capitalearn.capitalearnweb.controller.base.BaseServlet;
import br.com.capitalearn.capitalearnweb.dao.UserDao;
import br.com.capitalearn.capitalearnweb.exception.DBException;
import br.com.capitalearn.capitalearnweb.factory.DaoFactory;
import br.com.capitalearn.capitalearnweb.model.User;
import br.com.capitalearn.capitalearnweb.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet(value = "/perfil")
public class PerfilServlet extends BaseServlet {
    public PerfilServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession(false);
        if (session != null) {
            var user = session.getAttribute("user");
            req.setAttribute("user", user); // Envia o user como atributo pro JSP
        }

        req.getRequestDispatcher("/perfil.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("/login");
            return;
        }

        User user = getUser(req);

        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String telefone = req.getParameter("telefone");
        String senha = req.getParameter("senha");

        user.setFullName(nome);
        user.setEmail(email);
        user.setPhoneNumber(telefone);

        if (senha != null && !senha.trim().isEmpty()) {
            String hashSenha = BCrypt.hashpw(senha, BCrypt.gensalt(12));
            user.setPassword(hashSenha);
        } else {
            user.setPassword(((User) session.getAttribute("user")).getPassword());
        }

        try {
            UserService userService = new UserService();
            userService.update(user);
            session.setAttribute("user", user);
            req.setAttribute("user", user);
            req.setAttribute("success", "Perfil atualizado com sucesso.");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("error", "Erro ao atualizar o perfil.");
        }

        req.getRequestDispatcher("/perfil.jsp").forward(req, resp);

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
