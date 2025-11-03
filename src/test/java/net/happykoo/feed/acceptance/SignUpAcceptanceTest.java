package net.happykoo.feed.acceptance;

import net.happykoo.feed.acceptance.util.AcceptanceTestTemplate;
import net.happykoo.feed.auth.application.dto.SendEmailRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static net.happykoo.feed.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static org.junit.jupiter.api.Assertions.*;

class SignUpAcceptanceTest extends AcceptanceTestTemplate {
    private final String email = "rudals4549@gmail.com";

    @BeforeEach
    public void setup() {
        super.cleanup();
    }

    @Test
    @DisplayName("Email 보냈을 때, token이 DB에 저장되어야 함")
    public void test1() {
        //given
        SendEmailRequestDto dto = SendEmailRequestDto.builder()
                .email(email)
                .build();

        //when
        Integer code = requestSendEmail(dto);
        String token = this.getEmailToken(email);

        assertEquals(200, code);
        assertNotNull(token);
    }

    @Test
    @DisplayName("유효하지 않은 Email 보냈을 때, 에러가 발생")
    public void test2() {
        //given
        SendEmailRequestDto dto = SendEmailRequestDto.builder()
                .email("test")
                .build();

        //when
        Integer code = requestSendEmail(dto);
        assertEquals(400, code);
    }
}
