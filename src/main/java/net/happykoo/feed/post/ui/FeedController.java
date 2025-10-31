package net.happykoo.feed.post.ui;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.common.ui.Response;
import net.happykoo.feed.post.repository.post_queue.UserPostQueueQueryRepository;
import net.happykoo.feed.post.ui.dto.GetPostContentResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class FeedController {
    private final UserPostQueueQueryRepository queueQueryRepository;

    @RequestMapping("/{userId}")
    public Response<List<GetPostContentResponseDto>> getPostFeed(@PathVariable Long userId,
                                                                 @RequestParam(required = false) Long lastPostId) {
        List<GetPostContentResponseDto> result = queueQueryRepository.getPostList(userId, lastPostId);
        return Response.ok(result);
    }
}
