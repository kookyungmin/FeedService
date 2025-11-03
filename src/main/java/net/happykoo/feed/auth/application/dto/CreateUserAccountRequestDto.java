package net.happykoo.feed.auth.application.dto;

public record CreateUserAccountRequestDto(String email, String password, String role, String name, String profileImg) {
}
