package net.happykoo.feed.auth.repository;

import net.happykoo.feed.auth.application.interfaces.EmailSendRepository;
import net.happykoo.feed.auth.domain.Email;
import org.springframework.stereotype.Repository;

@Repository
public class EmailSendRepositoryImpl implements EmailSendRepository {

    @Override
    public void sendToken(Email email, String token) {

    }
}
