package net.happykoo.feed.acceptance.util;

import jakarta.persistence.PersistenceContext;
import net.happykoo.feed.user.application.dto.CreateUserRequestDto;
import net.happykoo.feed.user.application.dto.FollowUserRequestDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static net.happykoo.feed.acceptance.steps.UserAcceptanceSteps.createUser;
import static net.happykoo.feed.acceptance.steps.UserAcceptanceSteps.followUser;

@Profile("test")
@Component
public class DataLoader {
    public void loadData() {
        createUser(new CreateUserRequestDto("happykoo1", ""));
        createUser(new CreateUserRequestDto("happykoo2", ""));
        createUser(new CreateUserRequestDto("happykoo3", ""));

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }
}
