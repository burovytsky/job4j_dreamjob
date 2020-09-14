package ru.job4j.dreamjob.servlet;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IndexServletTest {

    @Test
    public void startIndex() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("index.jsp")).thenReturn(rd);
        new IndexServlet().doGet(request, response);
        verify(request, atLeastOnce()).getRequestDispatcher("index.jsp");
    }
}