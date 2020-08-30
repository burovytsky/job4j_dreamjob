package ru.job4j.dreamjob.servlet;

import org.postgresql.util.PSQLException;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = new User(0, req.getParameter("name"),
                req.getParameter("email"), req.getParameter("password"));
        try {
            PsqlStore.instOf().save(user);
        } catch (PSQLException psqlException) {
            req.setAttribute("error", "Пользователь уже существует");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
        HttpSession sc = req.getSession();
        sc.setAttribute("user", PsqlStore.instOf().findUserByEmail(req.getParameter("email")));
        resp.sendRedirect(req.getContextPath() + "/post/posts.do");
    }
}
