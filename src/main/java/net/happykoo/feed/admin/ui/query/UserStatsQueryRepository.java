package net.happykoo.feed.admin.ui.query;

import net.happykoo.feed.admin.ui.dto.users.GetDailyRegisterUserResponseDto;

import java.util.List;

public interface UserStatsQueryRepository {
    List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays);
}
