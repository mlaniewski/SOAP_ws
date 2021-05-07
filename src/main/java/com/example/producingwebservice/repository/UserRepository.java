package com.example.producingwebservice.repository;

import com.bialystok.event.ws.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private final Map<Integer, User> users = new HashMap<>();

    @PostConstruct
    public void initData() {
        User admin = new User();
        admin.setId(0);
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setAdmin(true);

        users.put(admin.getId(), admin);
    }

    public User get(String username, String password) {
        Optional<User> optionalUser = users.values()
                .stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
        return optionalUser.orElse(null);
    }

    public User save(String username, String password) {
        boolean isUserPresent = users.values().stream().anyMatch(user -> user.getUsername().equals(username));
        if (isUserPresent) {
            return null;
        }
        final int id = users.size();
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setAdmin(false);

        users.put(id, user);
        return user;
    }
}
