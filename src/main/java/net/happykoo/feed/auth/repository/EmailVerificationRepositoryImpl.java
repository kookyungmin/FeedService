package net.happykoo.feed.auth.repository;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.auth.application.interfaces.EmailVerificationRepository;
import net.happykoo.feed.auth.domain.Email;
import net.happykoo.feed.auth.repository.entity.EmailVerificationEntity;
import net.happykoo.feed.auth.repository.jpa.JpaEmailVerificationRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {
    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;

    @Override
    @Transactional
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

    @Override
    @Transactional
    public void verifyEmail(Email email, String token) {
        String emailAddress = email.getEmailAddress();
        EmailVerificationEntity entity = jpaEmailVerificationRepository.findByEmail(emailAddress)
                .orElseThrow(() -> new IllegalArgumentException("인증 요하지 않은 이메일 입니다."));

        if (entity.isVerified()) {
            throw new IllegalArgumentException("이미 인증된 이메일입니다.");
        }

        if (!entity.hasSameToken(token)) {
            throw new IllegalArgumentException("토큰 값이 유효하지 않습니다.");
        }

        entity.verify();
    }

    @Override
    public boolean isVerified(Email email) {
        return jpaEmailVerificationRepository.findByEmail(email.getEmailAddress())
                .orElseThrow(() -> new IllegalArgumentException("인증 요청을 하지 않은 이메일입니다."))
                .isVerified();
    }
}
