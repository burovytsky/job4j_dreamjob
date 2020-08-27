package ru.job4j.dreamjob.servlet;

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
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (PsqlStore.instOf().findUserByEmail(email) == null) {
            HttpSession sc = req.getSession();
            User user = new User(0, name, email, password);
            PsqlStore.instOf().save(user);
            sc.setAttribute("user", PsqlStore.instOf().findUserByEmail(email));
            resp.sendRedirect(req.getContextPath() + "/post/posts.do");
        } else {
            req.setAttribute("error", "Пользователь уже существует");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
