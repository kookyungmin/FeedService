package net.happykoo.feed.admin.ui;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.admin.ui.dto.GetDailyRegisterUserResponseDto;
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

    @GetMapping("/user-stats")
    public Response<List<GetDailyRegisterUserResponseDto>> getDailyRegisterUserCount(@RequestParam int beforeDays) {
        return Response.ok(userStatsQueryRepository.getDailyRegisterUserStats(beforeDays));
    }

}
