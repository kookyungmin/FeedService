package net.happykoo.feed.post.application.repository;

import net.happykoo.feed.post.application.interfaces.CommentRepository;
import net.happykoo.feed.post.domain.Post;
import net.happykoo.feed.post.domain.comment.Comment;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeCommentRepository implements CommentRepository {
    private final Map<Long, Comment> store = new HashMap<>();
    private Long sequence = 1L;

    @Override
    public Comment save(Comment comment) {
        Comment newComment = comment.getId() == null ? Comment.createComment(sequence++, comment.getAuthor(), comment.getPost(), comment.getContent())
                : Comment.createComment(comment.getId(), comment.getAuthor(), comment.getPost(), comment.getContent());
        store.put(newComment.getId(), newComment);

        return newComment;
    }

    @Override
    public Comment findById(Long id) {
        return store.get(id);
    }
}
