package net.happykoo.feed.common.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PageableTest {
    @Test
    @DisplayName("pageIndex 가 1, pageSize 10 이면, offset 0, limit 10")
    void test1() {
        //given
        Pageable pageable = new Pageable();

        //when
        int offset = pageable.getOffset();
        int limit = pageable.getLimit();

        //then
        assertEquals(0, offset);
        assertEquals(10, limit);
    }

    @Test
    @DisplayName("page 가 2 이면, offset 10, limit 10")
    void test2() {
        //given
        Pageable pageable = new Pageable(2, 10);

        //when
        int offset = pageable.getOffset();
        int limit = pageable.getLimit();

        //then
        assertEquals(10, offset);
        assertEquals(10, limit);
    }
}
