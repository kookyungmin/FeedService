package net.happykoo.feed.admin.ui.dto.posts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.happykoo.feed.common.domain.Pageable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetPostTableRequestDto extends Pageable {
    private Long postId;

}
