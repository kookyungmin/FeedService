package net.happykoo.feed.auth.ui;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.auth.application.EmailService;
import net.happykoo.feed.auth.application.dto.SendEmailRequestDto;
import net.happykoo.feed.common.ui.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signup")
@RequiredArgsConstructor
public class SignUpController {
    private final EmailService emailService;

    @PostMapping("/send-verification-email")
    public Response<Void> sendEmail(@RequestBody SendEmailRequestDto dto) {
        emailService.sendEmailToken(dto);
        return Response.ok();
    }
}
