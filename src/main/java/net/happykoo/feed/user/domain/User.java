package net.happykoo.feed.user.domain;

import net.happykoo.feed.common.domain.PositiveIntegerCounter;

import java.util.Objects;

public class User {
    private final Long id;
    private final UserInfo userInfo;
    private final PositiveIntegerCounter followerCounter;
    private final PositiveIntegerCounter followingCounter;

    public User(Long id, UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.userInfo = userInfo;
        this.followerCounter = new PositiveIntegerCounter();
        this.followingCounter = new PositiveIntegerCounter();
    }

    public void follow(User targetUser) {
        if (this.equals(targetUser)) {
            //자기 자신은 팔로우 할 수 없음
            throw new IllegalArgumentException();
        }
        followingCounter.increase();
        targetUser.increaseFollowerCount();
    }

    public void unfollow(User targetUser) {
        if (this.equals(targetUser)) {
            //자기 자신은 언팔로우 할 수 없음
            throw new IllegalArgumentException();
        }
        followingCounter.decrease();
        targetUser.decreaseFollowerCount();
    }

    public int followerCount() {
        return this.followerCounter.getCount();
    }

    public int followingCount() {
        return this.followingCounter.getCount();
    }

    public Long getId() {
        return id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    private void increaseFollowerCount() {
        this.followerCounter.increase();
    }

    private void decreaseFollowerCount() {
        this.followerCounter.decrease();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
