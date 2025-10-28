package net.happykoo.feed.post.application.interfaces;

import net.happykoo.feed.post.domain.comment.Comment;

import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
}
