package net.happykoo.feed.auth.application.interfaces;

import net.happykoo.feed.auth.domain.Email;

public interface EmailVerificationRepository {
    void createEmailVerification(Email email, String token);
    void verifyEmail(Email email, String token);
    boolean isVerified(Email email);
}
