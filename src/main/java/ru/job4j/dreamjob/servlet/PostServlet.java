package ru.job4j.dreamjob.servlet;

import ru.job4j.dreamjob.Store;
import ru.job4j.dreamjob.model.Post;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class PostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Store.instOf().save(new Post(Integer.parseInt(req.getParameter("id")), req.getParameter("name"),
                req.getParameter("description"),
                LocalDateTime.now()));
        resp.sendRedirect(req.getContextPath() + "/post/posts.jsp");
    }
}