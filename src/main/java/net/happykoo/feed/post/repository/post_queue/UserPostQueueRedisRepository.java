package net.happykoo.feed.post.repository.post_queue;

import net.happykoo.feed.post.repository.entity.post.PostEntity;

import java.util.List;

public interface UserPostQueueRedisRepository {
    void publishPostToFollowerUserList(PostEntity postEntity, List<Long> userIdList);
    void publishPostListToFollowingUser(List<PostEntity> postEntityList, Long userId);
    void deleteFeed(Long userId, Long targetId);
}
