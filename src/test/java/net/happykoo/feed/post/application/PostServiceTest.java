package net.happykoo.feed.post.application;

import net.happykoo.feed.fake.FakeObjectFactory;
import net.happykoo.feed.post.application.dto.CreatePostRequestDto;
import net.happykoo.feed.post.application.dto.LikeRequestDto;
import net.happykoo.feed.post.application.dto.UpdatePostRequestDto;
import net.happykoo.feed.post.domain.Post;
import net.happykoo.feed.post.domain.content.PostPublicationState;
import net.happykoo.feed.user.application.UserService;
import net.happykoo.feed.user.application.dto.CreateUserRequestDto;
import net.happykoo.feed.user.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostServiceTest {
    private final UserService userService = FakeObjectFactory.userService();
    private final PostService postService = FakeObjectFactory.postService();


    private final User user = userService.createUser(new CreateUserRequestDto("happykoo", ""));
    private final User otherUser = userService.createUser(new CreateUserRequestDto("marco", ""));

    private final CreatePostRequestDto postRequestDto = new CreatePostRequestDto(user.getId(), "I'm so happy!!", PostPublicationState.PUBLIC);

    @Test
    void givenPostRequestDto_whenCreate_thenReturnPost() {
        //when
        Post createdPost = postService.createPost(postRequestDto);

        //then
        Post foundPost = postService.getPost(createdPost.getId());

        assertEquals(createdPost, foundPost);
    }

    @Test
    void givenPostRequestDto_whenUpdate_thenReturnPost() {
        //given
        Post createdPost = postService.createPost(postRequestDto);

        //when
        UpdatePostRequestDto updatePostRequestDto = new UpdatePostRequestDto(createdPost.getId(),
                createdPost.getAuthor().getId(),
                "updateTest",
                PostPublicationState.PUBLIC);
        Post updatedPost = postService.updatePost(updatePostRequestDto);


        assertEquals(createdPost.getId(), updatedPost.getId());
        assertEquals(createdPost.getAuthor(), updatedPost.getAuthor());
        assertEquals(createdPost.getContent(), updatedPost.getContent());
    }

    @Test
    void givenCreatedPost_whenLiked_thenReturnPostWithLike() {
        //given
        Post createdPost = postService.createPost(postRequestDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(createdPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        //then
        assertEquals(1, createdPost.likeCount());
    }

    @Test
    void givenCreatedPost_whenLikedTwice_thenReturnPostWithLike() {
        //given
        Post createdPost = postService.createPost(postRequestDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(createdPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);
        postService.likePost(likeRequestDto);

        //then
        assertEquals(1, createdPost.likeCount());
    }


    @Test
    void givenCreatedPostAndLiked_whenUnliked_thenReturnPostWithoutLike() {
        //given
        Post createdPost = postService.createPost(postRequestDto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(createdPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        //when
        postService.unlikePost(likeRequestDto);

        //then
        assertEquals(0, createdPost.likeCount());
    }

    @Test
    void givenCreatedPost_whenUnliked_thenReturnPostWithoutLike() {
        //given
        Post createdPost = postService.createPost(postRequestDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(createdPost.getId(), otherUser.getId());
        postService.unlikePost(likeRequestDto);

        //then
        assertEquals(0, createdPost.likeCount());
    }
}
