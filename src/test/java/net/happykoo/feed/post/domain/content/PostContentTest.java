package net.happykoo.feed.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostContentTest {
    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent() {
        //given
        String text = "I'm happy";

        //when
        PostContent postContent = new PostContent(text);

        //then
        assertEquals(text, postContent.getContent());
    }

    @Test
    void givenContentLengthIsOver_whenCreated_thenReturnThrowError() {
        //given
        String text = "a".repeat(501);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭", "국"})
    void givenContentLengthIsOverAndKorean_whenCreated_thenReturnThrowError(String koreanWord) {
        //given
        String text = koreanWord.repeat(501);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @Test
    void givenContentLengthIsUnder_whenCreated_thenReturnThrowError() {
        //given
        String text = "a".repeat(4);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsNullOrEmpty_whenCreated_thenReturnThrowError(String text) {
        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @Test
    void givenContentLengthIsOk_whenUpdated_thenReturnTextContent() {
        //given
        String text = "I'm happy";
        PostContent postContent = new PostContent(text);

        //when
        String newText = "I'm not happy";
        postContent.updateContent(newText);

        //then
        assertEquals(newText, postContent.getContent());
    }

    @Test
    void givenContentLengthIsOver_whenUpdated_thenReturnThrowError() {
         //given
        String text = "I'm happy";
        PostContent postContent = new PostContent(text);


        //when, then
        String newText = "a".repeat(501);
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(newText));
    }

    @Test
    void givenContentLengthIsUnder_whenUpdated_thenReturnThrowError() {
        //given
        String text = "I'm happy";
        PostContent postContent = new PostContent(text);

        //when, then
        String newText = "a".repeat(4);
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(newText));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsNullOrEmpty_whenUpdated_thenReturnThrowError(String newText) {
        //given
        String text = "I'm happy";
        PostContent postContent = new PostContent(text);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(newText));
    }
}
