package net.happykoo.feed.post.repository.jpa;

import net.happykoo.feed.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {
    @Modifying //트랜잭션 내에서만 사용 가능
    @Query(value = "UPDATE PostEntity p " +
            "SET p.content = :#{#postEntity.content}, " +
            "p.state = :#{#postEntity.state}, " +
            "p.updateDate = current_timestamp " +
            "WHERE p.id = :#{#postEntity.id}")
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p " +
            "SET p.likeCount = :#{#postEntity.likeCount}," +
            "p.updateDate = current_timestamp " +
            "WHERE p.id = :#{#postEntity.id}")
    void updateLikeCount(PostEntity postEntity);
}
