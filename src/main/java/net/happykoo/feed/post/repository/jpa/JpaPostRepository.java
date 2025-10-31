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
//    TODO: 다음 주석 쿼리는 동시성 문제가 발생할 수 있음
//    -> 단순 카운팅 변경이지만 동시성 문제가 있는 경우는 DB 쿼리 단에서 update (DB row lock 을 이용) ex) 아래 active query
//    -> 도메인 객체 상태나 복잡한 비즈니스 로직은 도메인 계층에서 처리
//    도메인에서 처리하는데 동시성 문제가 발생한다면, 낙관적 락(@Version) 또는 비관적 락(@Lock(LockModeType.PESSIMISTIC_WRITE)) 사용
//    @Query(value = "UPDATE PostEntity p " +
//            "SET p.likeCount = :#{#postEntity.likeCount}," +
//            "p.updatedAt = current_timestamp " +
//            "WHERE p.id = :#{#postEntity.id}")
    @Query(value = "UPDATE PostEntity p " +
            "SET p.likeCount = p.likeCount + :likeCount," +
            "p.updatedAt = current_timestamp " +
            "WHERE p.id = :postId")
    void updateLikeCount(Long postId, Integer likeCount);

    //mysql 의 경우 update 문 실행 시 id 기반으로 row 단위 lock 걸음
    @Modifying
    @Query(value = "UPDATE PostEntity p " +
            "SET p.commentCount = p.commentCount + 1," +
            "p.updatedAt = current_timestamp " +
            "WHERE p.id = :#{#id}")
    void increaseCommentCount(Long id);

    @Query(value = "SELECT p.id FROM PostEntity p WHERE p.author.id = :authorId")
    List<Long> findAllPostIdsByAuthorId(Long authorId);

    @Query(value = "SELECT p FROM PostEntity p WHERE p.author.id = :authorId")
    List<PostEntity> findAllPostByAuthorId(Long authorId);
}
