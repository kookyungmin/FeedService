package net.happykoo.feed.post.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import net.happykoo.feed.post.application.interfaces.LikeRepository;
import net.happykoo.feed.post.domain.Post;
import net.happykoo.feed.post.domain.comment.Comment;
import net.happykoo.feed.post.repository.entity.comment.CommentEntity;
import net.happykoo.feed.post.repository.entity.like.LikeEntity;
import net.happykoo.feed.post.repository.entity.post.PostEntity;
import net.happykoo.feed.post.repository.jpa.JpaCommentRepository;
import net.happykoo.feed.post.repository.jpa.JpaLikeRepository;
import net.happykoo.feed.post.repository.jpa.JpaPostRepository;
import net.happykoo.feed.user.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private final JpaPostRepository jpaPostRepository;
    private final JpaCommentRepository jpaCommentRepository;
    private final JpaLikeRepository jpaLikeRepository;

    @Override
    public boolean checkLike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
//        save 를 하면 id 를 기반으로 있는지 없는지 체크 쿼리 실행됨
//        jpaLikeRepository.save(likeEntity);
        entityManager.persist(likeEntity);
        jpaPostRepository.updateLikeCount(new PostEntity(post));
    }

    @Override
    @Transactional
    public void unlike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaPostRepository.updateLikeCount(new PostEntity(post));
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
//        jpaLikeRepository.save(likeEntity);
        entityManager.persist(likeEntity);
        jpaCommentRepository.updateLikeCount(new CommentEntity(comment));

    }

    @Override
    @Transactional
    public void unlike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaCommentRepository.updateLikeCount(new CommentEntity(comment));
    }
}
