package net.happykoo.feed.acceptance.steps;

import io.restassured.RestAssured;
import net.happykoo.feed.auth.application.dto.SendEmailRequestDto;
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
}
