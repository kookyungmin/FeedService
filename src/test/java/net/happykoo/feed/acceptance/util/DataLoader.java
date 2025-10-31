package net.happykoo.feed.acceptance.util;

import net.happykoo.feed.user.application.dto.CreateUserRequestDto;
import net.happykoo.feed.user.application.dto.FollowUserRequestDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static net.happykoo.feed.acceptance.steps.UserAcceptanceSteps.requestCreateUser;
import static net.happykoo.feed.acceptance.steps.UserAcceptanceSteps.requestFollowUser;

@Profile("test")
@Component
public class DataLoader {
    public void loadData() {
        requestCreateUser(new CreateUserRequestDto("happykoo1", ""));
        requestCreateUser(new CreateUserRequestDto("happykoo2", ""));
        requestCreateUser(new CreateUserRequestDto("happykoo3", ""));

        requestFollowUser(new FollowUserRequestDto(1L, 2L));
        requestFollowUser(new FollowUserRequestDto(1L, 3L));
    }
}
