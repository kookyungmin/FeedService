package net.happykoo.feed.post.repository;

import net.happykoo.feed.post.repository.entity.post.PostEntity;
import net.happykoo.feed.post.repository.post_queue.UserPostQueueRedisRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("test")
public class FakeUserPostQueueRedisRepository implements UserPostQueueRedisRepository {
    private static final Map<Long, Set<PostEntity>> redisStore = new HashMap<>();

    @Override
    public void publishPostToFollowerUserList(PostEntity postEntity, List<Long> userIdList) {
        for(Long userId : userIdList) {
            redisStore.computeIfAbsent(userId, (t) -> new HashSet<>()).add(postEntity);
        }
    }

    @Override
    public void publishPostListToFollowingUser(List<PostEntity> postEntityList, Long userId) {
        redisStore.computeIfAbsent(userId, (t) -> new HashSet<>()).addAll(postEntityList);
    }

    @Override
    public void deleteFeed(Long userId, Long targetId) {
        if (redisStore.containsKey(userId)) {
            redisStore.get(userId).removeIf(post -> post.getAuthor().getId().equals(targetId));
        }
    }

    public List<PostEntity> getPostListByUserId(Long userId) {
        return List.copyOf(redisStore.get(userId));
    }
}
