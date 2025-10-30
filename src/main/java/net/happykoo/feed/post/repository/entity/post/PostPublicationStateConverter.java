package net.happykoo.feed.post.repository.entity.post;

import jakarta.persistence.AttributeConverter;
import net.happykoo.feed.post.domain.content.PostPublicationState;

public class PostPublicationStateConverter implements AttributeConverter<PostPublicationState, String> {
    @Override
    public String convertToDatabaseColumn(PostPublicationState state) {
        return state.name();
    }

    @Override
    public PostPublicationState convertToEntityAttribute(String name) {
        return PostPublicationState.valueOf(name);
    }
}
