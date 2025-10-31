package net.happykoo.feed.post.repository;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.post.repository.entity.post.PostEntity;
import net.happykoo.feed.post.repository.post_queue.UserPostQueueQueryRepository;
import net.happykoo.feed.post.ui.dto.GetPostContentResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Profile("test")
@Repository
public class FakeUserPostQueueQueryRepository implements UserPostQueueQueryRepository {
    private final FakeUserPostQueueRedisRepository fakeUserPostQueueRedisRepository;

    public FakeUserPostQueueQueryRepository(FakeUserPostQueueRedisRepository fakeUserPostQueueRedisRepository) {
        this.fakeUserPostQueueRedisRepository = fakeUserPostQueueRedisRepository;
    }

    @Override
    public List<GetPostContentResponseDto> getPostList(Long userId, Long lastPostId) {
        List<PostEntity> postEntityList = fakeUserPostQueueRedisRepository.getPostListByUserId(userId);

        return postEntityList.stream()
                .map(postEntity -> GetPostContentResponseDto.builder()
                        .id(postEntity.getId())
                        .build())
                .collect(Collectors.toList());
    }
}
