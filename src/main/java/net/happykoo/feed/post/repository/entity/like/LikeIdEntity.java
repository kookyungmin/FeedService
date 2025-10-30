package net.happykoo.feed.post.repository.entity.like;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class LikeIdEntity implements Serializable {
    private Long targetId;
    private Long userId;
    private String targetType;
}
