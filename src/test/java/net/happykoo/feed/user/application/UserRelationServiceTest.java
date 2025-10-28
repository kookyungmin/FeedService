package net.happykoo.feed.user.application;

import net.happykoo.feed.user.application.dto.CreateUserRequestDto;
import net.happykoo.feed.user.application.dto.FollowUserRequestDto;
import net.happykoo.feed.user.application.interfaces.UserRelationRepository;
import net.happykoo.feed.user.application.interfaces.UserRepository;
import net.happykoo.feed.user.application.repository.FakeUserRelationRepository;
import net.happykoo.feed.user.application.repository.FakeUserRepository;
import net.happykoo.feed.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRelationServiceTest {
    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    private final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private final UserRelationService userRelationService = new UserRelationService(userService, userRelationRepository);

    private User user1;
    private User user2;
    private FollowUserRequestDto requestDto;

    @BeforeEach
    void setup() {
        CreateUserRequestDto dto = new CreateUserRequestDto("happykoo", "");
        this.user1 = userService.createUser(dto);
        this.user2 = userService.createUser(dto);
        this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void givenCreateTwoUser_whenFollow_thenUserFollowSaved() {
        //when
        userRelationService.follow(requestDto);

        //then
        assertEquals(1, user1.followingCount());
        assertEquals(1, user2.followerCount());
        assertTrue(userRelationRepository.isAlreadyFollow(user1, user2));
    }

    @Test
    void givenCreateTwoUserFollowed_whenFollow_thenUserThrowError() {
        //given
        userRelationService.follow(requestDto);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenFollow_thenUserThrowError() {
        //given
        FollowUserRequestDto sameUserRequestDto = new FollowUserRequestDto(user1.getId(), user1.getId());

        //when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUserRequestDto));
    }

    @Test
    void givenCreateTwoUserAndFollowed_whenUnfollow_thenUserUnfollowSaved() {
        //given
        userRelationService.follow(requestDto);

        //when
        userRelationService.unfollow(requestDto);

        //then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user2.followerCount());
        assertFalse(userRelationRepository.isAlreadyFollow(user1, user2));
    }

    @Test
    void givenCreateTwoUser_whenUnfollow_thenUserThrowError() {
        //when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenUnfollow_thenUserThrowError() {
        //given
        FollowUserRequestDto sameUserRequestDto = new FollowUserRequestDto(user1.getId(), user1.getId());

        //when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(sameUserRequestDto));
    }
}
