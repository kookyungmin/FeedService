package net.happykoo.feed.admin.ui;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.admin.ui.dto.GetTableListResponse;
import net.happykoo.feed.admin.ui.dto.posts.GetPostTableRequestDto;
import net.happykoo.feed.admin.ui.dto.posts.GetPostTableResponseDto;
import net.happykoo.feed.admin.ui.dto.users.GetDailyRegisterUserResponseDto;
import net.happykoo.feed.admin.ui.dto.users.GetUserTableRequestDto;
import net.happykoo.feed.admin.ui.dto.users.GetUserTableResponseDto;
import net.happykoo.feed.admin.ui.query.AdminTableQueryRepository;
import net.happykoo.feed.admin.ui.query.UserStatsQueryRepository;
import net.happykoo.feed.common.ui.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserStatsQueryRepository userStatsQueryRepository;
    private final AdminTableQueryRepository adminTableQueryRepository;

    @GetMapping("/user-stats")
    public Response<List<GetDailyRegisterUserResponseDto>> getDailyRegisterUserCount(@RequestParam int beforeDays) {
        return Response.ok(userStatsQueryRepository.getDailyRegisterUserStats(beforeDays));
    }

    @GetMapping("/users")
    public Response<GetTableListResponse<GetUserTableResponseDto>> getUserTableList(GetUserTableRequestDto dto) {
        return Response.ok(adminTableQueryRepository.getUserTableData(dto));
    }


    public Response<GetTableListResponse<GetPostTableResponseDto>> getUserTableList(GetPostTableRequestDto dto) {
        return Response.ok(adminTableQueryRepository.getPostTableData(dto));
    }

}
