package net.happykoo.feed.acceptance.steps;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import net.happykoo.feed.auth.application.dto.LoginRequestDto;
import net.happykoo.feed.auth.application.dto.UserAccessTokenResponseDto;
import org.springframework.http.MediaType;

public class LoginAcceptanceSteps {
    public static ExtractableResponse<Response> requestLoginResponse(LoginRequestDto dto) {
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/login")
                .then()
                .extract();
    }

    public static Integer requestLoginResponseCode(LoginRequestDto dto) {
        return requestLoginResponse(dto)
                .jsonPath()
                .get("code");
    }

    public static String requestLoginGetAccessToken(LoginRequestDto dto) {
        return requestLoginResponse(dto)
                .jsonPath()
                .getObject("value", UserAccessTokenResponseDto.class)
                .accessToken();
    }
}
