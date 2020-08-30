package ru.job4j.dreamjob.servlet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.store.MemStore;
import ru.job4j.dreamjob.store.PsqlStore;
import ru.job4j.dreamjob.store.Store;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)

public class RegServletTest {
    private HttpServletResponse response;
    private HttpServletRequest request;
    private HttpSession session;
    private Store store;

    @Before
    public void init() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        PowerMockito.mockStatic(PsqlStore.class);
        store = MemStore.instOf();
        Mockito.when(PsqlStore.instOf()).thenReturn(store);
    }

    @Test
    public void whenAddAndFindUserThenReturnIt() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn("Ivan3");
        when(request.getParameter("email")).thenReturn("user_email");
        when(request.getParameter("password")).thenReturn("pas");
        when(request.getSession()).thenReturn(session);
        new RegServlet().doPost(request, response);
        User expected = new User(5, "Ivan3", "user_email", "pas");
        User actual = store.findUserByEmail("user_email");
        assertThat(expected, is(actual));
    }

    @Test
    public void whenUserExistsShouldForwardToLogin() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn("User2");
        when(request.getParameter("email")).thenReturn("user2@gmail.com");
        when(request.getParameter("password")).thenReturn("user2password");
        when(request.getSession()).thenReturn(session);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("login.jsp")).thenReturn(rd);
        new RegServlet().doPost(request, response);
        verify(request, atLeastOnce()).getRequestDispatcher("login.jsp");
    }
}
