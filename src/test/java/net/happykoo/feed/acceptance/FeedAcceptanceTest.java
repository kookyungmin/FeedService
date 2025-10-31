package net.happykoo.feed.acceptance;

import net.happykoo.feed.acceptance.util.AcceptanceTestTemplate;
import net.happykoo.feed.post.application.dto.CreatePostRequestDto;
import net.happykoo.feed.post.domain.content.PostPublicationState;
import net.happykoo.feed.post.ui.dto.GetPostContentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static net.happykoo.feed.acceptance.steps.FeedAcceptanceSteps.requestCreatePost;
import static net.happykoo.feed.acceptance.steps.FeedAcceptanceSteps.requestFeed;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FeedAcceptanceTest extends AcceptanceTestTemplate {
    @BeforeEach
    @DisplayName("User 1, 2, 3 생성 및 User 1 이 User2, 3 Follow")
    public void setup() {
        super.setup();
    }

    @Test
    @DisplayName("User2 가 Post 1을 작성하면, User 1의 피드에 Post1 이 조회된다.")
    void test1() {
        //given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L, "user 1 can get this post", PostPublicationState.PUBLIC);
        Long createPostId = requestCreatePost(dto);

        //when, 팔로우 피드 요청
        List<GetPostContentResponseDto> result = requestFeed(1L);

        //then
        assertEquals(1, result.size());
        assertEquals(createPostId, result.get(0).getId());
    }
}
