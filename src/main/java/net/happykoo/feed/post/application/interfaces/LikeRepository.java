package net.happykoo.feed.post.application.interfaces;

import net.happykoo.feed.user.domain.User;

public interface LikeRepository<T> {
    boolean checkLike(T target, User user);
    void like(T target, User user);
    void unlike(T target, User user);
}
