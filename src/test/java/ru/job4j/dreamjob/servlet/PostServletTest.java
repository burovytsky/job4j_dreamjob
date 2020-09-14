package ru.job4j.dreamjob.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.store.MemStore;
import ru.job4j.dreamjob.store.PsqlStore;
import ru.job4j.dreamjob.store.Store;

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

public class PostServletTest {
    @Test
    public void whenAddPostThenReturnIt() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        PowerMockito.mockStatic(PsqlStore.class);
        Store store = MemStore.instOf();
        Mockito.when(PsqlStore.instOf()).thenReturn(store);
        when(request.getParameter("id")).thenReturn("5");
        when(request.getParameter("name")).thenReturn("java job");
        when(request.getParameter("description")).thenReturn("some desc");
        new PostServlet().doPost(request, response);
        Post expected = new Post(5, "java job", "some desc");
        Post actual = store.findPostById(5);
        assertThat(expected, is(actual));
    }
}
