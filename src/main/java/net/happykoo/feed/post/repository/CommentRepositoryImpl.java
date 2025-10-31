package net.happykoo.feed.post.repository;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.post.application.interfaces.CommentRepository;
import net.happykoo.feed.post.domain.comment.Comment;
import net.happykoo.feed.post.repository.entity.comment.CommentEntity;
import net.happykoo.feed.post.repository.jpa.JpaCommentRepository;
import net.happykoo.feed.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
    private final JpaCommentRepository jpaCommentRepository;
    private final JpaPostRepository jpaPostRepository;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        CommentEntity commentEntity = new CommentEntity(comment);
        if (comment.getId() != null) {
            jpaCommentRepository.updateCommentEntity(commentEntity);
        } else {
            jpaCommentRepository.save(commentEntity);
            jpaPostRepository.increaseCommentCount(commentEntity.getPost().getId());
        }
        return commentEntity.toComment();
    }

    @Override
    public Comment findById(Long id) {
        return jpaCommentRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new)
                .toComment();
    }
}
