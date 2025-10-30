package net.happykoo.feed.user.application.dto;

import net.happykoo.feed.user.domain.User;

public record GetUserResponseDto(Long id, String name, String profileImage, Integer followingCount, Integer followerCount) {
    public GetUserResponseDto(User user) {
        this(user.getId(), user.getName(), user.getProfileImageUrl(), user.followingCount(), user.followerCount());
    }
}
