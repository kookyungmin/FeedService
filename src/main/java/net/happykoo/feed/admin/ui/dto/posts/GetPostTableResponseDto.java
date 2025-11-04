package net.happykoo.feed.admin.ui.dto.posts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.happykoo.feed.common.utils.TimeCalculator;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class GetPostTableResponseDto {
    @Getter
    private Long postId;
    @Getter
    private Long authorId;
    @Getter
    private String authorName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getContent() {
        if (content.length() > 10) {
            return content.substring(0, 10) + "...";
        }
        return content;
    }

    public String getCreatedAt() {
        return TimeCalculator.getFormattedDate(createdAt);
    }

    public String getUpdateAt() {
        return TimeCalculator.getFormattedDate(updatedAt);
    }
}
