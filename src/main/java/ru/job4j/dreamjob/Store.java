package ru.job4j.dreamjob;

import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Store {
    private static final Store INST = new Store();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private static final AtomicInteger POST_ID = new AtomicInteger(4);
    private static final AtomicInteger CANDIDATE_ID = new AtomicInteger(4);


    private Store() {
        posts.put(1, new Post(1, "Junior Java Job", "Description Junior Java Job", LocalDateTime.now()));
        posts.put(2, new Post(2, "Middle Java Job", "Descriptiom Middle Java Job", LocalDateTime.now()));
        posts.put(3, new Post(3, "Senior Java Job", "Description Senior Job", LocalDateTime.now()));
        candidates.put(1, new Candidate("Ivan Ivanov",
                "Ukraine, Odessa",
                "Junior Java Developer",
                LocalDateTime.of(1995, 4, 29, 19, 30, 40)));
        candidates.put(2, new Candidate("Petr Petrov",
                "Russia, Moscow",
                "Middle Java Developer",
                LocalDateTime.of(1990, 11, 12, 2, 30, 40)));
        candidates.put(3, new Candidate("Vasilii Vasin",
                "Ukraine, Kyiv",
                "Trainee Java Developer",
                LocalDateTime.of(1999, 1, 1, 3, 30, 40)));
    }

    public static Store instOf() {
        return INST;
    }

    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    public void save(Post post) {
        post.setId(POST_ID.incrementAndGet());
        posts.put(post.getId(), post);
    }

    public void save(Candidate candidate) {
        candidates.put(CANDIDATE_ID.incrementAndGet(), candidate);
    }
}
