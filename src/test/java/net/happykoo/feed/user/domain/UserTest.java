package net.happykoo.feed.user.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private final UserInfo userInfo = new UserInfo("happykoo", "");

    @Test
    void givenTwoUserWithDifferentId_whenEquals_thenReturnFalse() {
        //given
        User user1 = new User(1L, userInfo);
        User user2 = new User(2L, userInfo);

        //when, then
        assertFalse(user1.equals(user2));
        assertFalse(user2.equals(user1));
    }

    @Test
    void givenTwoUserWithSameId_whenEquals_thenReturnFalse() {
        //given
        User user1 = new User(1L, userInfo);
        User user2 = new User(1L, userInfo);

        //when, then
        assertTrue(user1.equals(user2));
        assertTrue(user2.equals(user1));
    }

    @Test
    void givenTwoUserWithDifferentId_whenHashCode_thenReturnFalse() {
        //given
        User user1 = new User(1L, userInfo);
        User user2 = new User(2L, userInfo);

        //when, then
        assertFalse(user1.hashCode() == user2.hashCode());
    }

    @Test
    void givenTwoUserWithSameId_whenHashcode_thenReturnFalse() {
        //given
        User user1 = new User(1L, userInfo);
        User user2 = new User(1L, userInfo);

        //when, then
        assertTrue(user1.hashCode() == user2.hashCode());
    }

    @Test
    void givenTwoUser_whenUser1FollowUser2_thenIncreaseUserCount() {
        //given
        User user1 = new User(1L, userInfo);
        User user2 = new User(2L, userInfo);

        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());

        //when
        user1.follow(user2);

        //then
        assertEquals(1, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
        assertEquals(1, user2.getFollowerCount());
    }

    @Test
    void givenTwoUserAndUser1FollowUser2_whenUser1UnfollowUser2_thenDecreaseUserCount() {
        //given
        User user1 = new User(1L, userInfo);
        User user2 = new User(2L, userInfo);
        user1.follow(user2);

        assertEquals(1, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
        assertEquals(1, user2.getFollowerCount());

        //when
        user1.unfollow(user2);

        //then
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
    }

    @Test
    void givenTwoUser_whenUser1UnfollowUser2_thenNotDecreaseUserCount() {
        //given
        User user1 = new User(1L, userInfo);
        User user2 = new User(2L, userInfo);

        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());

        //when
        user1.unfollow(user2);

        //then
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
    }

    @Test
    void givenTwoUserWithSameId_whenUser1followUser2_thenThrow() {
        //given
        User user1 = new User(1L, userInfo);
        User user2 = new User(1L, userInfo);

        assertThrows(IllegalArgumentException.class, () -> user1.follow(user2));
    }
}
