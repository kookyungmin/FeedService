package net.happykoo.feed.acceptance;

import net.happykoo.feed.acceptance.util.AcceptanceTestTemplate;
import net.happykoo.feed.auth.application.dto.CreateUserAccountRequestDto;
import net.happykoo.feed.auth.application.dto.SendEmailRequestDto;
import net.happykoo.feed.auth.application.dto.VerifyEmailRequestDto;
import net.happykoo.feed.auth.domain.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static net.happykoo.feed.acceptance.steps.SignUpAcceptanceSteps.*;
import static org.junit.jupiter.api.Assertions.*;

class SignUpAcceptanceTest extends AcceptanceTestTemplate {
    private final String email = "rudals4549@gmail.com";

    @BeforeEach
    public void setup() {
        super.cleanup();
    }

    @Test
    @DisplayName("Email Token 전송 :: Email 보냈을 때, token이 DB에 저장되어야 함")
    public void test1() {
        //given
        SendEmailRequestDto dto = new SendEmailRequestDto(email);

        //when
        Integer code = requestSendEmail(dto);
        String token = this.getEmailToken(email);

        assertEquals(200, code);
        assertNotNull(token);
    }

    @Test
    @DisplayName("Email Token 전송 :: 유효하지 않은 Email 보냈을 때, 에러가 발생")
    public void test2() {
        //given
        SendEmailRequestDto dto = new SendEmailRequestDto("test");

        //when
        Integer code = requestSendEmail(dto);
        assertEquals(400, code);
    }

    @Test
    @DisplayName("Token 검증 :: 정상적인 경우")
    public void test3() {
        //given
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);

        //when
        Integer code = requestVerifyEmail(new VerifyEmailRequestDto(email, token));

        //then
        boolean isVerified = isEmailVerified(email);
        assertEquals(200, code);
        assertTrue(isVerified);
    }

    @Test
    @DisplayName("Token 검증 :: 잘못된 토큰을 전달한 경우 에러 발생")
    public void test4() {
        //given
        requestSendEmail(new SendEmailRequestDto(email));

        //when
        Integer code = requestVerifyEmail(new VerifyEmailRequestDto(email, "wrong token"));

        //then
        boolean isVerified = isEmailVerified(email);
        assertEquals(400, code);
        assertFalse(isVerified);
    }

    @Test
    @DisplayName("Token 검증 :: Token 검증을 여러번 하면 에러 발생")
    public void test5() {
        //given
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(new VerifyEmailRequestDto(email, token));

        //when
        Integer code = requestVerifyEmail(new VerifyEmailRequestDto(email, token));

        //then
        assertEquals(400, code);
    }

    @Test
    @DisplayName("Token 검증 :: 잘못된 Email, Token으로 검증하면 에러 발생")
    public void test6() {
        //given
        requestSendEmail(new SendEmailRequestDto(email));

        //when
        Integer code = requestVerifyEmail(new VerifyEmailRequestDto("nnn", "wrong token"));

        //then
        assertEquals(400, code);
    }

    @Test
    @DisplayName("회원가입 :: Email 인증 후 정상 등록된 경우")
    public void test7() {
        //given
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(new VerifyEmailRequestDto(email, token));

        //when
        CreateUserAccountRequestDto dto = new CreateUserAccountRequestDto(email, "password", UserRole.USER.name(), "happykoo", "");
        Integer code = requestRegisterUser(dto);

        assertEquals(200, code);
        Long userId = getUserId(email);
        assertEquals(1L, userId);

    }

    @Test
    @DisplayName("회원가입 :: Email 인증 하지않으면 에러 발생")
    public void test8() {
        //given
        requestSendEmail(new SendEmailRequestDto(email));

        //when
        CreateUserAccountRequestDto dto = new CreateUserAccountRequestDto(email, "password", UserRole.USER.name(), "happykoo", "");
        Integer code = requestRegisterUser(dto);

        assertEquals(400, code);
    }
}
