package net.happykoo.feed.auth.application.interfaces;

import net.happykoo.feed.auth.domain.Email;

public interface EmailSendRepository {
    void sendToken(Email email, String token);
}
