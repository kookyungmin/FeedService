package net.happykoo.feed.admin.ui.dto;

import java.time.LocalDate;

public record GetDailyRegisterUserResponseDto(LocalDate date, Long count) {
}
