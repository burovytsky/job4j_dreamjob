package ru.job4j.dreamjob.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.dreamjob.PsqlStore;
import ru.job4j.dreamjob.model.Candidate;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class CandidateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("candidates", PsqlStore.instOf().findAllCandidates());
        req.getRequestDispatcher("candidates.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("C:\\tools\\apache-tomcat-9.0.37\\bin\\images\\photo_id");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        Candidate candidate = PsqlStore.instOf().findCandidateById(Integer.parseInt(req.getParameter("id")))
                == null ? new Candidate(0, "", "", "", LocalDateTime.now(), "")
                : PsqlStore.instOf().findCandidateById(Integer.parseInt(req.getParameter("id")));
        File file;
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("photo_id");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    file = new File(folder + File.separator + item.getName());
                    if (!item.getContentType().equals("application/octet-stream")) {
                        candidate.setPhotoId(item.getName());
                        try (FileOutputStream out = new FileOutputStream(file)) {
                            out.write(item.getInputStream().readAllBytes());
                        }
                    }
                } else {
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();
                    switch (fieldName) {
                        case "name" -> candidate.setName(fieldValue);
                        case "address" -> candidate.setAddress(fieldValue);
                        case "position" -> candidate.setPosition(fieldValue);
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        PsqlStore.instOf().save(candidate);
        resp.sendRedirect(req.getContextPath() + "/candidate/candidates.do");
    }
}
