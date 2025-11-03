package net.happykoo.feed.auth.repository;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.auth.application.interfaces.EmailVerificationRepository;
import net.happykoo.feed.auth.domain.Email;
import net.happykoo.feed.auth.repository.entity.EmailVerificationEntity;
import net.happykoo.feed.auth.repository.jpa.JpaEmailVerificationRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {
    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;

    @Override
    public void createEmailVerification(Email email, String token) {
        String emailAddress = email.getEmailAddress();
        Optional<EmailVerificationEntity> entityOpt = jpaEmailVerificationRepository.findByEmail(emailAddress);

        if (entityOpt.isPresent()) {
            EmailVerificationEntity entity = entityOpt.get();

            if (entity.isVerified()) {
                //이미 인증된 상황
                throw new IllegalArgumentException("이미 인증된 이메일 입니다.");
            }

            entity.updateToken(token);
            return;
        }

        EmailVerificationEntity emailVerificationEntity = new EmailVerificationEntity(emailAddress, token);
        jpaEmailVerificationRepository.save(emailVerificationEntity);
    }
}
