package net.happykoo.feed.post.application.dto;

public record UpdateCommentRequestDto(Long commentId, Long userId, String content) {
}
