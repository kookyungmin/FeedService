package net.happykoo.feed.acceptance;

import net.happykoo.feed.acceptance.util.AcceptanceTestTemplate;
import net.happykoo.feed.auth.application.dto.LoginRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static net.happykoo.feed.acceptance.steps.LoginAcceptanceSteps.*;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginAcceptanceTest extends AcceptanceTestTemplate {
    private final String email = "rudals4549@naver.com";

    @BeforeEach
    public void setup() {
        this.cleanup();
        this.createUser(email);
    }

    @Test
    @DisplayName("로그인 테스트 :: 정상적으로 로그인되어 Access Token 받는 경우")
    void test1() {
        //given
        LoginRequestDto dto = new LoginRequestDto(email, "password");

        //when
        String token = requestLoginGetAccessToken(dto);

        //then
        assertNotNull(token);
    }

    @Test
    @DisplayName("로그인 테스트 :: 비밀번호가 틀린 경우 에러 발생")
    void test2() {
        //given
        LoginRequestDto dto = new LoginRequestDto(email, "password11");

        //when
        Integer code = requestLoginResponseCode(dto);

        //then
        assertEquals(400, code);
    }
}
