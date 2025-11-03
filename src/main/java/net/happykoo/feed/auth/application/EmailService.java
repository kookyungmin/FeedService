package net.happykoo.feed.auth.application;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.auth.application.dto.SendEmailRequestDto;
import net.happykoo.feed.auth.application.interfaces.EmailSendRepository;
import net.happykoo.feed.auth.application.interfaces.EmailVerificationRepository;
import net.happykoo.feed.auth.domain.Email;
import net.happykoo.feed.auth.domain.RandomTokenGenerator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailVerificationRepository emailVerificationRepository;
    private final EmailSendRepository emailSendRepository;

    public void sendEmailToken(SendEmailRequestDto dto) {
        Email email = Email.createEmail(dto.getEmail());
        String token = RandomTokenGenerator.generateToken();

        emailSendRepository.sendToken(email, token);
        emailVerificationRepository.createEmailVerification(email, token);
    }
}
