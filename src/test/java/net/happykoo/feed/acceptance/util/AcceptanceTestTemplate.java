package net.happykoo.feed.acceptance.util;

import net.happykoo.feed.auth.application.dto.LoginRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static net.happykoo.feed.acceptance.steps.LoginAcceptanceSteps.requestLoginGetAccessToken;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AcceptanceTestTemplate {
    @Autowired
    private DatabaseCleaner cleaner;

    @Autowired
    private DataLoader loader;

    protected void setup() {
        cleaner.execute();
        loader.loadData();
    }

    protected void cleanup() {
        cleaner.execute();
    }

    protected String getEmailToken(String email) {
        return loader.getEmailToken(email);
    }

    protected Long getUserId(String email) {
        return loader.getUserId(email);
    }

    protected Boolean isEmailVerified(String email) {
        return loader.isEmailVerified(email);
    }

    protected void createUser(String email) {
        loader.createUser(email);
    }

    protected String login(String email) {
        return requestLoginGetAccessToken(new LoginRequestDto(email, "password", ""));
    }
}
