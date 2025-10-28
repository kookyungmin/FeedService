package net.happykoo.feed.post.domain.comment;

import net.happykoo.feed.post.domain.Post;
import net.happykoo.feed.post.domain.content.CommentContent;
import net.happykoo.feed.post.domain.content.PostContent;
import net.happykoo.feed.user.domain.User;
import net.happykoo.feed.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentTest {
    private final UserInfo userInfo = new UserInfo("happykoo", "");
    private final UserInfo otherUserInfo = new UserInfo("marco", "");
    private final User user = new User(1L, userInfo);
    private final User otherUser = new User(2L, otherUserInfo);
    private final Post post = new Post(1L, user, new PostContent("post!!"));
    private final Comment comment = new Comment(1L, user, post, new CommentContent("hi"));

    @Test
    void givenCommentCreated_whenLike_thenLikeCountShouldBe1() {
        //when
        comment.like(otherUser);

        //then
        assertEquals(1, comment.likeCount());
    }

    @Test
    void givenCommentCreated_whenLikeByAuthor_thenThrowError() {
        //when, then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    void givenCommentCreatedAndLike_whenUnlike_thenLikeCountShouldBe0() {
        //given
        comment.like(otherUser);

        //when
        comment.unlike(otherUser);

        //then
        assertEquals(0, comment.likeCount());
    }

    @Test
    void givenCommentCreated_whenUnlike_thenLikeCountShouldBe0() {
        //when
        comment.unlike(otherUser);

        //then
        assertEquals(0, comment.likeCount());
    }

    @Test
    void givenCommentCreated_whenUpdateContent_thenContentShouldBeUpdated() {
        //when
        String newText = "happy! happy!";
        comment.updateContent(user, newText);

        //then
        assertEquals(newText, comment.getContent());
    }

    @Test
    void givenCommentCreated_whenUpdateContentByOtherUser_thenThrowError() {
        //when, then
        String newText = "happy! happy!";
        assertThrows(IllegalArgumentException.class, () -> comment.updateContent(otherUser, newText));
    }
}
