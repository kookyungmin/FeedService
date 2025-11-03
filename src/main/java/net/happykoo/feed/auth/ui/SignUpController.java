package net.happykoo.feed.auth.ui;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.auth.application.EmailService;
import net.happykoo.feed.auth.application.UserAccountService;
import net.happykoo.feed.auth.application.dto.CreateUserAccountRequestDto;
import net.happykoo.feed.auth.application.dto.SendEmailRequestDto;
import net.happykoo.feed.auth.application.dto.VerifyEmailRequestDto;
import net.happykoo.feed.common.ui.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signup")
@RequiredArgsConstructor
public class SignUpController {
    private final EmailService emailService;
    private final UserAccountService userAccountService;

    @PostMapping("/send-verification-email")
    public Response<Void> sendEmail(@RequestBody SendEmailRequestDto dto) {
        emailService.sendEmailToken(dto);
        return Response.ok();
    }

    @PostMapping("/verify-token")
    public Response<Void> verifyToken(@RequestBody VerifyEmailRequestDto dto) {
        emailService.verifyEmail(dto);
        return Response.ok();
    }

    @PostMapping("/register")
    public Response<Long> register(@RequestBody CreateUserAccountRequestDto dto) {
        Long userId = userAccountService.registerUser(dto);
        return Response.ok(userId);
    }

}
