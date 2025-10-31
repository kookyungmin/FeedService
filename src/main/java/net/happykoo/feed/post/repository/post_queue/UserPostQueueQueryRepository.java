package net.happykoo.feed.post.repository.post_queue;

import net.happykoo.feed.post.ui.dto.GetPostContentResponseDto;

import java.util.List;

public interface UserPostQueueQueryRepository {
    List<GetPostContentResponseDto> getPostList(Long userId, Long lastPostId);
}
