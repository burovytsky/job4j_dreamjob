package ru.job4j.dreamjob.servlet;

import org.json.simple.JSONObject;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class CityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        Collection<City> cities = PsqlStore.instOf().findAllCities();
        JSONObject jsonObject = new JSONObject();
        for (City city : cities){
            jsonObject.put(city.getId(), city.getName());
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.println(jsonObject);
        writer.flush();
    }
}
