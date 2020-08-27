package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.model.User;

import java.time.LocalDateTime;

public class PsqlMain {
    public static void main(String[] args) {
        Store store = PsqlStore.instOf();
        store.save(new Post(0, "Java Job", "desc1", LocalDateTime.now()));
        store.save(new Post(0, "Job 2222", "desc2", LocalDateTime.now()));
        store.save(new Candidate(0, "Ivan", "address1", "pos1", LocalDateTime.now(), ""));
        store.save(new Candidate(0, "Daniel ", "address2", "pos2", LocalDateTime.now(), ""));
        store.save(new User(1, "User1", "user1@gmail.com", "user1password"));
        store.save(new User(2, "User2", "user1@gmail.com", "user2password"));
        store.save(new User(3, "User3", "user2@gmail.com", "user3password"));
        for (User user : store.findAllUsers()) {
            System.out.println(user.getId() + " " + user.getName() + " " + user.getEmail() + " " + user.getPassword());
        }
        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName() + " " + post.getDescription());
        }
        for (Candidate post : store.findAllCandidates()) {
            System.out.println(post.getId() + " " + post.getName() + " " + post.getAddress() + " " + post.getPosition());
        }
    }
}
