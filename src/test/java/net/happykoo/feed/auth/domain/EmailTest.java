package net.happykoo.feed.auth.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailTest {
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Email이 빈 값이거나 null이면 에러 발생")
    void test1(String email) {
        assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"testnaver.com", "tet/@ab", "안녕@tt..com"})
    @DisplayName("Email이 형식이 잘못되었으면 에러 발생")
    void test2(String email) {
        assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));
    }
}
