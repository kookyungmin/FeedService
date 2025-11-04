package net.happykoo.feed.post.repository.post_queue;

import net.happykoo.feed.post.repository.entity.post.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserPostQueueRedisRepositoryImpl implements UserPostQueueRedisRepository {
    @Override
    public void publishPostToFollowerUserList(PostEntity postEntity, List<Long> userIdList) {

    }

    @Override
    public void publishPostListToFollowingUser(List<PostEntity> postEntityList, Long userId) {

    }

    @Override
    public void deleteFeed(Long userId, Long targetId) {

    }
}
