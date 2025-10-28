package net.happykoo.feed.post.domain;

import net.happykoo.feed.post.domain.content.PostContent;
import net.happykoo.feed.post.domain.content.PostPublicationState;
import net.happykoo.feed.user.domain.User;
import net.happykoo.feed.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostTest {
    private final UserInfo userInfo = new UserInfo("happykoo", "");
    private final UserInfo otherUserInfo = new UserInfo("marco", "");
    private final User user = new User(1L, userInfo);
    private final User otherUser = new User(2L, otherUserInfo);
    private final Post post = new Post(1L, user, new PostContent("test!!"));

    @Test
    void givenPostCreated_whenLike_thenLikeCountShouldBe1() {
        //when
        post.like(otherUser);

        //then
        assertEquals(1, post.likeCount());
    }

    @Test
    void givenPostCreated_whenLikeByOtherUser_thenThrowError() {
        //when
        assertThrows(IllegalArgumentException.class, () -> post.like(user));
    }

    @Test
    void givenPostCreatedAndLike_whenUnlike_thenLikeCountShouldBe0() {
        //given
        post.like(otherUser);

        //when
        post.unlike(otherUser);

        //then
        assertEquals(0, post.likeCount());
    }

    @Test
    void givenPostCreated_whenUnlike_thenLikeCountShouldBe0() {
        //when
        post.unlike(otherUser);

        //then
        assertEquals(0, post.likeCount());
    }

    @Test
    void givenPostCreated_whenUpdateContent_thenContentShouldBeUpdated() {
        //given
        String newText = "happy! happy!";

        //when
        post.updateContent(user, newText, PostPublicationState.PUBLIC);

        //then
        assertEquals(newText, post.getContent());
    }

    @Test
    void givenPostCreated_whenUpdateContentByOtherUser_thenReturnThrowError() {
        //given
        String newText = "happy! happy!";

        //when, then
        assertThrows(IllegalArgumentException.class, () -> post.updateContent(otherUser, newText, PostPublicationState.PUBLIC));
    }
}
