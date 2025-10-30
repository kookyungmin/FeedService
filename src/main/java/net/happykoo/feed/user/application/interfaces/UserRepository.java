package net.happykoo.feed.user.application.interfaces;

import net.happykoo.feed.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    User findById(Long id);
}
