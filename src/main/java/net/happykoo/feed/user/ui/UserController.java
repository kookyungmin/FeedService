package net.happykoo.feed.user.ui;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.common.ui.Response;
import net.happykoo.feed.user.application.UserService;
import net.happykoo.feed.user.application.dto.CreateUserRequestDto;
import net.happykoo.feed.user.application.dto.GetUserListResponseDto;
import net.happykoo.feed.user.application.dto.GetUserResponseDto;
import net.happykoo.feed.user.domain.User;
import net.happykoo.feed.user.repository.jpa.JpaUserListQueryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    //Repository 도 저수준, Contoller도 저수준 컴포넌트이기에 직접 연관관계를 맺어도 큰 문제 X (단, 일반적으로 단순한 조회용도만)
    private final JpaUserListQueryRepository userListQueryRepository;

    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto) {
        User user = userService.createUser(dto);

        return Response.ok(user.getId());
    }

    @GetMapping("/{userId}")
    public Response<GetUserResponseDto> getUserProfile(@PathVariable Long userId) {
        GetUserResponseDto result = userService.getUserProfile(userId);
        return Response.ok(result);
    }

    @GetMapping("/{userId}/follower")
    public Response<List<GetUserListResponseDto>> getFollowerList(@PathVariable Long userId) {
        List<GetUserListResponseDto> result = userListQueryRepository.getFollowerUserList(userId);

        return Response.ok(result);
    }

    @GetMapping("/{userId}/following")
    public Response<List<GetUserListResponseDto>> getFollowingList(@PathVariable Long userId) {
        List<GetUserListResponseDto> result = userListQueryRepository.getFollowingUserList(userId);

        return Response.ok(result);
    }
}
