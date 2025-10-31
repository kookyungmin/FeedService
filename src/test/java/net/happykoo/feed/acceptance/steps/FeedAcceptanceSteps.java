package net.happykoo.feed.acceptance.steps;

import io.restassured.RestAssured;
import net.happykoo.feed.post.application.dto.CreatePostRequestDto;
import net.happykoo.feed.post.ui.dto.GetPostContentResponseDto;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class FeedAcceptanceSteps {
    public static Long requestCreatePost(CreatePostRequestDto dto) {
        return RestAssured
                .given().log().all()
                .body(dto)
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .post("/api/post")
                .then().log().all()
                .extract()
                .jsonPath()
                .getObject("value", Long.class);
    }

    public static List<GetPostContentResponseDto> requestFeed(Long userId) {
        return RestAssured
                .given().log().all()
                .accept(APPLICATION_JSON_VALUE)
                .when()
                .get("/api/feed/{userId}", userId)
                .then().log().all()
                .extract()
                .jsonPath()
                .getList("value", GetPostContentResponseDto.class);
    }
}
