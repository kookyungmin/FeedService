package net.happykoo.feed.post.application.dto;

public record CreateCommentRequestDto(Long postId, Long authorId, String content) {
}
