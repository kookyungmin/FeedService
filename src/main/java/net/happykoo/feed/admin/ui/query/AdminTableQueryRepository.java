package net.happykoo.feed.admin.ui.query;

import net.happykoo.feed.admin.ui.dto.GetTableListResponse;
import net.happykoo.feed.admin.ui.dto.posts.GetPostTableRequestDto;
import net.happykoo.feed.admin.ui.dto.posts.GetPostTableResponseDto;
import net.happykoo.feed.admin.ui.dto.users.GetUserTableRequestDto;
import net.happykoo.feed.admin.ui.dto.users.GetUserTableResponseDto;

public interface AdminTableQueryRepository {
    GetTableListResponse<GetUserTableResponseDto> getUserTableData(GetUserTableRequestDto dto);

    GetTableListResponse<GetPostTableResponseDto> getPostTableData(GetPostTableRequestDto dto);
}
