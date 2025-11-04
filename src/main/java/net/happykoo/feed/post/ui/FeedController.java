package net.happykoo.feed.post.ui;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.common.principal.AuthPrincipal;
import net.happykoo.feed.common.principal.UserPrincipal;
import net.happykoo.feed.common.ui.Response;
import net.happykoo.feed.post.repository.post_queue.UserPostQueueQueryRepository;
import net.happykoo.feed.post.ui.dto.GetPostContentResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class FeedController {
    private final UserPostQueueQueryRepository queueQueryRepository;

    @GetMapping
    public Response<List<GetPostContentResponseDto>> getPostFeed(@AuthPrincipal UserPrincipal userPrincipal,
                                                                 @RequestParam(required = false) Long lastPostId) {
        List<GetPostContentResponseDto> result = queueQueryRepository.getPostList(userPrincipal.getUserId(), lastPostId);
        return Response.ok(result);
    }
}
