package net.happykoo.feed.post.application;

import net.happykoo.feed.fake.FakeObjectFactory;
import net.happykoo.feed.post.application.dto.*;
import net.happykoo.feed.post.domain.Post;
import net.happykoo.feed.post.domain.comment.Comment;
import net.happykoo.feed.post.domain.content.PostPublicationState;
import net.happykoo.feed.user.application.UserService;
import net.happykoo.feed.user.application.dto.CreateUserRequestDto;
import net.happykoo.feed.user.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentServiceTest {
    private final UserService userService = FakeObjectFactory.userService();
    private final PostService postService = FakeObjectFactory.postService();
    private final CommentService commentService = FakeObjectFactory.commentService();

    private final User user = userService.createUser(new CreateUserRequestDto("happykoo", ""));
    private final User otherUser = userService.createUser(new CreateUserRequestDto("marco", ""));

    private final CreatePostRequestDto postRequestDto = new CreatePostRequestDto(user.getId(), "I'm so happy!!", PostPublicationState.PUBLIC);
    private final Post post = postService.createPost(postRequestDto);

    private final CreateCommentRequestDto commentRequestDto = new CreateCommentRequestDto(post.getId(), user.getId(), "happy comment!");


    @Test
    void givenCommentRequestDto_whenCreate_thenReturnComment() {
        //when
        Comment createdComment = commentService.createComment(commentRequestDto);

        //then
        Comment foundComment = commentService.getComment(createdComment.getId());

        assertEquals(createdComment, foundComment);
    }

    @Test
    void givenCommentRequestDto_whenUpdate_thenReturnComment() {
        //given
        Comment createdComment = commentService.createComment(commentRequestDto);

        //when
        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(createdComment.getAuthor().getId(),
                "updateTest");
        Comment updatedComment = commentService.updateComment(createdComment.getId(), updateCommentRequestDto);


        assertEquals(createdComment.getId(), updatedComment.getId());
        assertEquals(createdComment.getAuthor(), updatedComment.getAuthor());
        assertEquals(createdComment.getContent(), updatedComment.getContent());
    }

    @Test
    void givenCreatedComment_whenLiked_thenReturnCommentWithLike() {
        //given
        Comment createdComment = commentService.createComment(commentRequestDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(createdComment.getId(), otherUser.getId());
        commentService.like(likeRequestDto);

        //then
        assertEquals(1, createdComment.likeCount());
    }

    @Test
    void givenCreatedComment_whenLikedTwice_thenReturnCommentWithOneLike() {
        //given
        Comment createdComment = commentService.createComment(commentRequestDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(createdComment.getId(), otherUser.getId());
        commentService.like(likeRequestDto);
        commentService.like(likeRequestDto);

        //then
        assertEquals(1, createdComment.likeCount());
    }

    @Test
    void givenCreatedCommentAndLiked_whenUnliked_thenReturnCommentWithoutLike() {
        //given
        Comment createdComment = commentService.createComment(commentRequestDto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(createdComment.getId(), otherUser.getId());
        commentService.like(likeRequestDto);

        //when
        commentService.unlike(likeRequestDto);

        //then
        assertEquals(0, createdComment.likeCount());
    }

    @Test
    void givenCreatedComment_whenUnliked_thenReturnCommentWithoutLike() {
        //given
        Comment createdComment = commentService.createComment(commentRequestDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(createdComment.getId(), otherUser.getId());
        commentService.unlike(likeRequestDto);

        //then
        assertEquals(0, createdComment.likeCount());
    }
}
