package net.happykoo.feed.post.application.dto;

import net.happykoo.feed.post.domain.content.PostPublicationState;

public record CreatePostRequestDto(Long authorId, String content, PostPublicationState state) {
}
