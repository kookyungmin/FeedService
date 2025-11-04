package net.happykoo.feed.admin.ui.dto.users;

import lombok.*;
import net.happykoo.feed.common.domain.Pageable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserTableRequestDto extends Pageable {
    private String name;
}
