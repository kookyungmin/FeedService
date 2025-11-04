package net.happykoo.feed.post.ui;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.common.idempotency.Idempotent;
import net.happykoo.feed.common.ui.Response;
import net.happykoo.feed.post.application.PostService;
import net.happykoo.feed.post.application.dto.CreatePostRequestDto;
import net.happykoo.feed.post.application.dto.LikeRequestDto;
import net.happykoo.feed.post.application.dto.UpdatePostRequestDto;
import net.happykoo.feed.post.domain.Post;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public Response<Long> createPost(@RequestBody CreatePostRequestDto dto) {
        Post post = postService.createPost(dto);

        return Response.ok(post.getId());
    }

    @PatchMapping("{postId}")
    public Response<Long> updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequestDto dto) {
        postService.updatePost(postId, dto);

        return Response.ok(postId);

    }

    @Idempotent
    @PostMapping("/like")
    public Response<Void> likePost(@RequestBody LikeRequestDto dto) {
        postService.likePost(dto);
        return Response.ok();
    }

    @PostMapping("/unlike")
    public Response<Void> unlikePost(@RequestBody LikeRequestDto dto) {
        postService.unlikePost(dto);
        return Response.ok();
    }
}
