package net.happykoo.feed.acceptance.steps;

import io.restassured.RestAssured;
import net.happykoo.feed.auth.application.dto.CreateUserAccountRequestDto;
import net.happykoo.feed.auth.application.dto.SendEmailRequestDto;
import net.happykoo.feed.auth.application.dto.VerifyEmailRequestDto;
import org.springframework.http.MediaType;

public class SignUpAcceptanceSteps {
    public static Integer requestSendEmail(SendEmailRequestDto dto) {
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/signup/send-verification-email")
                .then()
                .extract()
                .jsonPath()
                .get("code");
    }

    public static Integer requestVerifyEmail(VerifyEmailRequestDto dto) {
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/signup/verify-token")
                .then()
                .extract()
                .jsonPath()
                .get("code");
    }

    public static Integer requestRegisterUser(CreateUserAccountRequestDto dto) {
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/signup/register")
                .then()
                .extract()
                .jsonPath()
                .get("code");
    }
}
