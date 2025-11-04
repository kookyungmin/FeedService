package net.happykoo.feed.auth.domain;

import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static net.happykoo.feed.auth.domain.UserRole.ADMIN;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TokenProviderTest {
    private final String secretKey = "abcd".repeat(40);
    private final TokenProvider tokenProvider = new TokenProvider(secretKey);

    @Test
    @DisplayName("Token 생성 테스트 :: 유저와 role 을 정상적으로 token 으로 생성한 경우")
    void test1() {
        //given
        Long userId = 1L;
        String role = ADMIN.name();

        //when
        String token = tokenProvider.createToken(userId, role);

        //then
        assertNotNull(token);
        assertEquals(userId, tokenProvider.getUserId(token));
        assertEquals(role, tokenProvider.getUserRole(token));
    }

    @Test
    @DisplayName("Token 생성 테스트 :: 올바르지 않은 token을 전달한 경우 에러 발생")
    void test2() {
        //given
        String invalidToken = "invalid token";

        //when, then
        assertThrows(MalformedJwtException.class, () -> tokenProvider.getUserId(invalidToken));
    }
}
