package net.happykoo.feed.post.ui;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.common.ui.Response;
import net.happykoo.feed.post.application.CommentService;
import net.happykoo.feed.post.application.dto.CreateCommentRequestDto;
import net.happykoo.feed.post.application.dto.LikeRequestDto;
import net.happykoo.feed.post.application.dto.UpdateCommentRequestDto;
import net.happykoo.feed.post.domain.comment.Comment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public Response<Long> createComment(@RequestBody CreateCommentRequestDto dto) {
        Comment comment = commentService.createComment(dto);
        return Response.ok(comment.getId());
    }

    @PatchMapping("/{commentId}")
    public Response<Long> updateComment(@PathVariable Long commentId, @RequestBody UpdateCommentRequestDto dto) {
        Comment comment = commentService.updateComment(commentId, dto);
        return Response.ok(comment.getId());
    }

    @PostMapping("/like")
    public Response<Void> likePost(@RequestBody LikeRequestDto dto) {
        commentService.like(dto);
        return Response.ok();
    }

    @PostMapping("/unlike")
    public Response<Void> unlikePost(@RequestBody LikeRequestDto dto) {
        commentService.unlike(dto);
        return Response.ok();
    }
}
