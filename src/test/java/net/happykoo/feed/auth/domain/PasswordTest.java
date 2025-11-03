package net.happykoo.feed.auth.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;


class PasswordTest {
    @Test
    @DisplayName("같은 패스워드를 비교했을 때, true 로 나와야 함")
    public void test1() {
        Password password = Password.createEncryptPassword("password");
        assertTrue(password.matchPassword("password"));
    }

    @Test
    @DisplayName("잘못된 패스워드를 비교했을 때, false 로 나와야 함")
    public void test2() {
        Password password = Password.createEncryptPassword("password");
        assertFalse(password.matchPassword("1"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void test3(String password) {
        assertThrows(IllegalArgumentException.class, () -> Password.createEncryptPassword(password));
    }
}
