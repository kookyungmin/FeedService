package net.happykoo.feed.admin.ui.dto.users;

import java.time.LocalDate;

public record GetDailyRegisterUserResponseDto(LocalDate date, Long count) {
}
