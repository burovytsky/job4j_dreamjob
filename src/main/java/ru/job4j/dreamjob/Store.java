package ru.job4j.dreamjob;

import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Store {
    private static final Store INST = new Store();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private Store() {
        posts.put(1, new Post(1, "Junior Java Job", "Description Junior Java Job", LocalDateTime.now()));
        posts.put(2, new Post(2, "Middle Java Job", "Descriptiom Middle Java Job", LocalDateTime.now()));
        posts.put(3, new Post(3, "Senior Java Job", "Description Senior Job", LocalDateTime.now()));
    }

    public static Store instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}
