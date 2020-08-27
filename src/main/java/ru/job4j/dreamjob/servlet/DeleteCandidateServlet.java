package ru.job4j.dreamjob.servlet;

import ru.job4j.dreamjob.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DeleteCandidateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imageName = PsqlStore.instOf()
                .findCandidateById(Integer.parseInt(req.getParameter("id"))).getPhotoId();
        File file = new File("C:/tools/apache-tomcat-9.0.37/bin/photo_id/" + imageName);
        file.delete();
        PsqlStore.instOf().delete(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("candidates", PsqlStore.instOf().findAllCandidates());
        req.getRequestDispatcher("/candidate/candidates.jsp").forward(req, resp);
    }
}
