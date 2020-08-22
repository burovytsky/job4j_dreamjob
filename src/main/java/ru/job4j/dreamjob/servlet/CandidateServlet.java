package ru.job4j.dreamjob.servlet;

import ru.job4j.dreamjob.Store;
import ru.job4j.dreamjob.model.Candidate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class CandidateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("candidates", Store.instOf().findAllCandidates());
        req.getRequestDispatcher("candidates.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        Store.instOf().save(new Candidate(Integer.parseInt(req.getParameter("id")),
                req.getParameter("name"),
                req.getParameter("address"),
                req.getParameter("position"), LocalDateTime.now().minusMonths(200)));
        resp.sendRedirect(req.getContextPath() + "/candidate/candidates.do");
    }
}
