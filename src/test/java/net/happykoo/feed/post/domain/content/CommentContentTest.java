package net.happykoo.feed.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentContentTest {
    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent() {
        //given
        String text = "I'm happy";

        //when
        CommentContent commentContent = new CommentContent(text);

        //then
        assertEquals(text, commentContent.getContent());
    }

    @Test
    void givenContentLengthIsMaxBoundary_whenCreated_thenReturnTextContent() {
        //given
        String text = "a".repeat(100);

        //when
        CommentContent commentContent = new CommentContent(text);

        //then
        assertEquals(text, commentContent.getContent());
    }

    @Test
    void givenContentLengthIsOver_whenCreated_thenReturnThrowError() {
        //given
        String text = "a".repeat(101);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭", "국"})
    void givenContentLengthIsOverAndKorean_whenCreated_thenReturnThrowError(String koreanWord) {
        //given
        String text = koreanWord.repeat(101);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(text));
    }

    @Test
    void givenShortContent_whenCreated_thenReturnTextContent() {
        //given
        String text = "a";

        //when
        CommentContent commentContent = new CommentContent(text);

        //then
        assertEquals(text, commentContent.getContent());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsNullOrEmpty_whenCreated_thenReturnThrowError(String text) {
        //when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(text));
    }

    @Test
    void givenContentLengthIsOk_whenUpdated_thenReturnTextContent() {
        //given
        String text = "I'm happy";
        CommentContent commentContent = new CommentContent(text);

        //when
        String newText = "I'm not happy";
        commentContent.updateContent(newText);

        //then
        assertEquals(newText, commentContent.getContent());
    }

    @Test
    void givenContentLengthIsMaxBoundary_whenUpdated_thenReturnTextContent() {
        //given
        CommentContent commentContent = new CommentContent("seed");

        //when
        String newText = "a".repeat(100);
        commentContent.updateContent(newText);

        //then
        assertEquals(newText, commentContent.getContent());
    }

    @Test
    void givenContentLengthIsOver_whenUpdated_thenReturnThrowError() {
        //given
        String text = "I'm happy";
        CommentContent commentContent = new CommentContent(text);

        //when, then
        String newText = "a".repeat(101);
        assertThrows(IllegalArgumentException.class, () -> commentContent.updateContent(newText));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsNullOrEmpty_whenUpdated_thenReturnThrowError(String newText) {
        //given
        String text = "I'm happy";
        CommentContent commentContent = new CommentContent(text);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> commentContent.updateContent(newText));
    }
}
