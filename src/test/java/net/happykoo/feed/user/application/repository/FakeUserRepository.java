package net.happykoo.feed.user.application.repository;

import net.happykoo.feed.user.application.interfaces.UserRepository;
import net.happykoo.feed.user.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeUserRepository implements UserRepository {
    private final Map<Long, User> store = new HashMap<>();
    private Long sequence = 1L;

    @Override
    public User save(User user) {
        User newUser = user.getId() == null ? new User(sequence++, user.getUserInfo())
                : new User(user.getId(), user.getUserInfo());
        store.put(newUser.getId(), newUser);

        return newUser;
    }

    @Override
    public User findById(Long id) {
        return store.get(id);
    }
}
