package net.happykoo.feed.user.repository.jpa;

import net.happykoo.feed.user.repository.entity.UserRelationEntity;
import net.happykoo.feed.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {
    @Query(value = "SELECT u.id.followerUserId FROM UserRelationEntity u WHERE u.id.followingUserId = :userId")
    List<Long> findFollowers(Long userId);
}
