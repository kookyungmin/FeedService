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
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity author = postEntity.getAuthor();
        List<Long> followerIds = jpaUserRelationRepository.findFollowers(author.getId());

        List<UserPostQueueEntity> userPostQueueEntities = followerIds.stream()
                .map(userId -> new UserPostQueueEntity(userId, postEntity.getId(), author.getId()))
                .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        List<Long> postIds = jpaPostRepository.findAllPostIdsByAuthorId(targetId);
        List<UserPostQueueEntity> userPostQueueEntities = postIds.stream()
                .map(postId -> new UserPostQueueEntity(userId, postId, targetId))
                .toList();
        jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
    }

    @Override
    @Transactional
    public void deleteUnfollowPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
    }
}
