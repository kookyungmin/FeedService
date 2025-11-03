package net.happykoo.feed.auth.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

class RandomTokenGeneratorTest {
    @Test
    @DisplayName("토큰이 정상적으로 생성된 경우 :: null 값이 아니고 16자리")
    void test1() {
        //when
        String token = RandomTokenGenerator.generateToken();

        //then
        assertNotNull(token);
        assertEquals(16, token.length());
    }

    @Test
    @DisplayName("토큰이 정상적으로 생성된 경우 :: 대소문자, 숫자로 구성된 16자리 값")
    void test2() {
        //when
        String token = RandomTokenGenerator.generateToken();

        //then
        assertNotNull(token);
        assertTrue(token.matches("[0-9A-Za-z]{16}"));
    }

    @Test
    @DisplayName("토큰을 연속해서 생성한 경우 :: 서로의 값이 같지 않다.")
    void test3() {
        //when
        String token1 = RandomTokenGenerator.generateToken();
        String token2 = RandomTokenGenerator.generateToken();

        //then
        assertNotNull(token1);
        assertNotNull(token2);
        assertNotEquals(token1, token2);
    }
}
