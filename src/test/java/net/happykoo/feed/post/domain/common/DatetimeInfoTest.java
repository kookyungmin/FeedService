package net.happykoo.feed.post.domain.common;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatetimeInfoTest {
    @Test
    void givenCreated_whenUpdated_thenTimeAndStateAreUpdated() {
        //given
        DateTimeInfo dateTimeInfo = new DateTimeInfo();
        LocalDateTime localDateTime = dateTimeInfo.getDateTime();

        //when
        dateTimeInfo.updateDatetime();

        //then
        assertTrue(dateTimeInfo.isEdited());
        assertNotEquals(localDateTime, dateTimeInfo.getDateTime());

    }
}
