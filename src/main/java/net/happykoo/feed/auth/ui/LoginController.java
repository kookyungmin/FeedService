package net.happykoo.feed.auth.ui;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.auth.application.UserAccountService;
import net.happykoo.feed.auth.application.dto.LoginRequestDto;
import net.happykoo.feed.auth.application.dto.UserAccessTokenResponseDto;
import net.happykoo.feed.common.ui.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {
    private final UserAccountService userAccountService;

    @PostMapping
    public Response<UserAccessTokenResponseDto> login(@RequestBody LoginRequestDto dto) {
        return Response.ok(userAccountService.login(dto));
    }
}
