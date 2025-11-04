package net.happykoo.feed.acceptance.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import net.happykoo.feed.auth.application.dto.CreateUserAccountRequestDto;
import net.happykoo.feed.auth.application.dto.SendEmailRequestDto;
import net.happykoo.feed.auth.application.dto.VerifyEmailRequestDto;
import net.happykoo.feed.auth.domain.UserRole;
import net.happykoo.feed.user.application.dto.CreateUserRequestDto;
import net.happykoo.feed.user.application.dto.FollowUserRequestDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static net.happykoo.feed.acceptance.steps.SignUpAcceptanceSteps.*;
import static net.happykoo.feed.acceptance.steps.UserAcceptanceSteps.requestCreateUser;
import static net.happykoo.feed.acceptance.steps.UserAcceptanceSteps.requestFollowUser;

@Profile("test")
@Component
public class DataLoader {
    @PersistenceContext
    private EntityManager entityManager;

    public void loadData() {
        for(int i = 0; i < 4; i++) {
            createUser("happykoo" + i + "@naver.com");
        }

        requestFollowUser(new FollowUserRequestDto(1L, 2L));
        requestFollowUser(new FollowUserRequestDto(1L, 3L));
    }

    public String getEmailToken(String email) {
        return entityManager.createQuery("SELECT e.token FROM EmailVerificationEntity e WHERE e.email = :email", String.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public Boolean isEmailVerified(String email) {
        return entityManager.createQuery("SELECT e.isVerified FROM EmailVerificationEntity e WHERE e.email = :email", Boolean.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public Long getUserId(String email) {
        return entityManager.createQuery("SELECT u.userId FROM UserAccountEntity u WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public void createUser(String email) {
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(new VerifyEmailRequestDto(email, token));
        requestRegisterUser(new CreateUserAccountRequestDto(email, "password", UserRole.USER.name(), "happykoo", ""));
    }
}
