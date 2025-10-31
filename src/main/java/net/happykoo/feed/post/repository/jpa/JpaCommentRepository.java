package net.happykoo.feed.post.repository.jpa;

import net.happykoo.feed.post.repository.entity.comment.CommentEntity;
import net.happykoo.feed.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {
    @Modifying
    @Query(value = "UPDATE CommentEntity c " +
            "SET c.content = :#{#commentEntity.content} ," +
            "c.updatedAt = current_timestamp " +
            "WHERE c.id = :#{#commentEntity.id}")
    void updateCommentEntity(CommentEntity commentEntity);

    @Modifying
    @Query(value = "UPDATE CommentEntity c " +
            "SET c.likeCount = :#{#commentEntity.likeCount}," +
            "c.updatedAt = current_timestamp " +
            "WHERE c.id = :#{#commentEntity.id}")
    void updateLikeCount(CommentEntity commentEntity);
}
