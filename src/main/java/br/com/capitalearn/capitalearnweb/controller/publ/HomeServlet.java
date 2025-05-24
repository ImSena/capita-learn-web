package br.com.capitalearn.capitalearnweb.controller.publ;

import br.com.capitalearn.capitalearnweb.controller.base.BaseServlet;
import br.com.capitalearn.capitalearnweb.model.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/")
public class HomeServlet extends BaseServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUser(req);
        if(user != null){
            resp.sendRedirect(req.getContextPath() + "/dashboard");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
