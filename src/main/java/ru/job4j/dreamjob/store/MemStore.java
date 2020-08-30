package ru.job4j.dreamjob.store;

import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.model.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemStore implements Store {
    private static final MemStore INST = new MemStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private final Map<Integer, User> users = new ConcurrentHashMap<>();
    private static final AtomicInteger POST_ID = new AtomicInteger(4);
    private static final AtomicInteger CANDIDATE_ID = new AtomicInteger(4);
    private static final AtomicInteger USER_ID = new AtomicInteger(4);


    private MemStore() {
        users.put(1, new User(1, "User1", "user1@gmail.com", "user1password"));
        users.put(2, new User(2, "User2", "user2@gmail.com", "user2password"));
        users.put(3, new User(3, "User3", "user2@gmail.com", "user3password"));
    }

    public static MemStore instOf() {
        return INST;
    }

    @Override
    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    @Override
    public Post findPostById(int id) {
        return posts.get(id);
    }

    @Override
    public Candidate findCandidateById(int id) {
        return candidates.get(id);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = null;
        for (Map.Entry<Integer, User> userEntry : users.entrySet()) {
            if (userEntry.getValue().getEmail().equals(email)) {
                user = userEntry.getValue();
                break;
            }
        }
        return user;
    }

    @Override
    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    @Override
    public Collection<User> findAllUsers() {
        return users.values();
    }

    @Override
    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    @Override
    public void save(Candidate candidate) {
        if (candidate.getId() == 0) {
            candidate.setId(CANDIDATE_ID.incrementAndGet());
        }
        candidates.put(candidate.getId(), candidate);
    }

    @Override
    public void save(User user) throws PSQLException {
        if (findUserByEmail(user.getEmail()) != null) {
            throw new PSQLException(new ServerErrorMessage("User already exist"));
        } else {
            if (user.getId() == 0) {
                user.setId(USER_ID.incrementAndGet());
            }
            users.put(user.getId(), user);
        }
    }

    @Override
    public void delete(int id) {
        candidates.remove(id);
    }
}
