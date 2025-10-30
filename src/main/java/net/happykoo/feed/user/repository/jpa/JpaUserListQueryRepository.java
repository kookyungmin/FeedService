package net.happykoo.feed.user.repository.jpa;

import net.happykoo.feed.user.application.dto.GetUserListResponseDto;
import net.happykoo.feed.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "select new net.happykoo.feed.user.application.dto.GetUserListResponseDto(u.name, u.profileImageUrl) " +
            "from UserRelationEntity ur " +
            "inner join UserEntity u on ur.id.followerUserId = u.id " +
            "where ur.id.followingUserId = :userId")
    List<GetUserListResponseDto> getFollowingUserList(Long userId);

    @Query(value = "select new net.happykoo.feed.user.application.dto.GetUserListResponseDto(u.name, u.profileImageUrl) " +
            "from UserRelationEntity ur " +
            "inner join UserEntity u on ur.id.followingUserId = u.id " +
            "where ur.id.followerUserId = :userId")
    List<GetUserListResponseDto> getFollowerUserList(Long userId);
}
