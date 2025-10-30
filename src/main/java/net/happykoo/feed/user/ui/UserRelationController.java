package net.happykoo.feed.user.ui;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.common.ui.Response;
import net.happykoo.feed.user.application.UserRelationService;
import net.happykoo.feed.user.application.dto.FollowUserRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-relation")
@RequiredArgsConstructor
public class UserRelationController {
    private final UserRelationService userRelationService;

    @PostMapping("/follow")
    public Response<Void> followUser(@RequestBody FollowUserRequestDto dto) {
        userRelationService.follow(dto);

        return Response.ok(null);
    }

    @PostMapping("/unfollow")
    public Response<Void> unfollowUser(@RequestBody FollowUserRequestDto dto) {
        userRelationService.unfollow(dto);

        return Response.ok(null);
    }
}
