package ru.job4j.dreamjob.store;

import org.postgresql.util.PSQLException;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.model.User;

import java.sql.SQLException;
import java.util.Collection;

public interface Store {
    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    Collection<User> findAllUsers();

    void save(Post post);

    void save(Candidate candidate);

    void save(User user) throws PSQLException;

    void delete(int id);

    Post findPostById(int id);

    Candidate findCandidateById(int id);

    User findUserByEmail(String email);
}
