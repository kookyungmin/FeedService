package net.happykoo.feed.post.repository.jpa;

import net.happykoo.feed.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {
    @Modifying //트랜잭션 내에서만 사용 가능
    @Query(value = "UPDATE PostEntity p " +
            "SET p.content = :#{#postEntity.content}, " +
            "p.state = :#{#postEntity.state}, " +
            "p.updatedAt = current_timestamp " +
            "WHERE p.id = :#{#postEntity.id}")
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p " +
            "SET p.likeCount = :#{#postEntity.likeCount}," +
            "p.updatedAt = current_timestamp " +
            "WHERE p.id = :#{#postEntity.id}")
    void updateLikeCount(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p " +
            "SET p.commentCount = p.commentCount + 1," +
            "p.updatedAt = current_timestamp " +
            "WHERE p.id = :#{#id}")
    void increaseCommentCount(Long id);

    @Query(value = "SELECT p.id FROM PostEntity p WHERE p.author.id = :authorId")
    List<Long> findAllPostIdsByAuthorId(Long authorId);
}
