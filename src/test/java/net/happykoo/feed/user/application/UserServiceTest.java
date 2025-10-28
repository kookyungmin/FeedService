package net.happykoo.feed.user.application;

import net.happykoo.feed.fake.FakeObjectFactory;
import net.happykoo.feed.user.application.dto.CreateUserRequestDto;
import net.happykoo.feed.user.application.interfaces.UserRepository;
import net.happykoo.feed.user.application.repository.FakeUserRepository;
import net.happykoo.feed.user.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {
    private final UserService userService = FakeObjectFactory.userService();

    @Test
    void givenUserInfoDto_whenCreateUser_thenCanFindUser() {
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("happykoo", "");

        //when
        User savedUser = userService.createUser(dto);

        //then
        User foundUser = userService.getUser(savedUser.getId());

        assertEquals(savedUser.getId(), foundUser.getId());
        assertEquals(dto.name(), foundUser.getUserInfo().getName());

    }
}
