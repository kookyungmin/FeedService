package net.happykoo.feed.post.application.repository;

import net.happykoo.feed.post.application.interfaces.LikeRepository;
import net.happykoo.feed.post.domain.Post;
import net.happykoo.feed.post.domain.comment.Comment;
import net.happykoo.feed.user.domain.User;

import java.util.*;

public class FakeLikeRepository implements LikeRepository {
    private final Map<Post, Set<User>> postLikes = new HashMap<>();
    private final Map<Comment, Set<User>> commentLikes = new HashMap<>();

    @Override
    public boolean checkLike(Post post, User user) {
        return Optional.ofNullable(postLikes.get(post))
                .map(s -> s.contains(user))
                .isPresent();
    }

    @Override
    public void like(Post post, User user) {
        postLikes.computeIfAbsent(post,  (k) -> new HashSet<>()).add(user);
    }

    @Override
    public void unlike(Post post, User user) {
        postLikes.computeIfAbsent(post, (k) -> new HashSet<>()).remove(user);
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        return Optional.ofNullable(commentLikes.get(comment))
                .map(s -> s.contains(user))
                .isPresent();
    }

    @Override
    public void like(Comment comment, User user) {
        commentLikes.computeIfAbsent(comment, (k) -> new HashSet<>()).add(user);
    }

    @Override
    public void unlike(Comment comment, User user) {
        commentLikes.computeIfAbsent(comment, (k) -> new HashSet<>()).remove(user);
    }
}
