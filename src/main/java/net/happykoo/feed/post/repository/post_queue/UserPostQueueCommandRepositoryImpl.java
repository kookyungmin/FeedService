package net.happykoo.feed.post.repository.post_queue;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.post.repository.entity.post.PostEntity;
import net.happykoo.feed.post.repository.entity.post.UserPostQueueEntity;
import net.happykoo.feed.post.repository.jpa.JpaPostRepository;
import net.happykoo.feed.post.repository.jpa.JpaUserPostQueueRepository;
import net.happykoo.feed.user.repository.entity.UserEntity;
import net.happykoo.feed.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {
    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
//    대용량으로 데이터가 저장되고 많은 트래픽을 고려하여 사용자 별로 피드들을 Redis 에 저장하게 변경 -> 실제로 구현하는 건 아니고 Fake Repository 만들어서 인수테스트
//    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;
    private final UserPostQueueRedisRepository userPostQueueRedisRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity author = postEntity.getAuthor();
        List<Long> followerIds = jpaUserRelationRepository.findFollowers(author.getId());

//        List<UserPostQueueEntity> userPostQueueEntities = followerIds.stream()
//                .map(userId -> new UserPostQueueEntity(userId, postEntity.getId(), author.getId()))
//                .toList();
//
//        jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
        userPostQueueRedisRepository.publishPostToFollowerUserList(postEntity, followerIds);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
//        List<Long> postIdList = jpaPostRepository.findAllPostIdByAuthorId(targetId);
//        List<UserPostQueueEntity> userPostQueueEntities = postIds.stream()
//                .map(postId -> new UserPostQueueEntity(userId, postId, targetId))
//                .toList();
//        jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
        List<PostEntity> postList = jpaPostRepository.findAllPostByAuthorId(targetId);
        userPostQueueRedisRepository.publishPostListToFollowingUser(postList, userId);
    }

    @Override
    @Transactional
    public void deleteUnfollowPost(Long userId, Long targetId) {
//        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
        userPostQueueRedisRepository.deleteFeed(userId, targetId);
    }
}
