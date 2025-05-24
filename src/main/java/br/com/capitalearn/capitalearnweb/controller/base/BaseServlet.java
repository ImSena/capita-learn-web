package br.com.capitalearn.capitalearnweb.controller.base;

import br.com.capitalearn.capitalearnweb.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public abstract class BaseServlet extends HttpServlet {

    private boolean validateSession = true;

    public BaseServlet() {
        this(true);
    }

    public BaseServlet(boolean validateSession) {
        this.validateSession = validateSession;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.matches(".*(\\.css|\\.js|\\.png|\\.jpg|\\.jpeg|\\.gif|\\.svg|\\.woff2?|\\.ttf)$")) {
            super.service(req, resp);
            return;
        }

        HttpSession session = req.getSession(false);

        if (validateSession) {
            if (session == null || session.getAttribute("user") == null) {
                resp.sendRedirect(req.getContextPath() + "/login");
                return;
            }
        }

        super.service(req, resp);
    }

    protected User getUser(HttpServletRequest req) throws ServletException, IOException {
        return (User) req.getSession().getAttribute("user");
    }
}
