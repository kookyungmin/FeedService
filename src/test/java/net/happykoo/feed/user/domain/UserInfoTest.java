package net.happykoo.feed.user.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserInfoTest {
    @Test
    void givenNameAndProfileImageUrl_whenCreated_thenThrowNothing() {
        //given
        String name = "happykoo";
        String profileImageUrl = "https://www.happykoo.net";

        //when, then
        assertDoesNotThrow(() -> new UserInfo(name, profileImageUrl));
    }

    @Test
    void givenBlankNameAndProfileImageUrl_whenCreated_thenThrow() {
        //given
        String name = "";
        String profileImageUrl = "https://www.happykoo.net";

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new UserInfo(name, profileImageUrl));
    }
}
