package net.happykoo.feed.auth.domain;

import lombok.Getter;

import java.util.regex.Pattern;

public class Email {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    @Getter
    private String emailAddress;
    private Email(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public static Email createEmail(String emailAddress) {
        if (emailAddress == null || emailAddress.isBlank()) throw new IllegalArgumentException("email is null or empty");
        if (!isValidEmailText(emailAddress)) {
            throw new IllegalArgumentException("email is not invalid");
        }
        return new Email(emailAddress);
    }

    private static boolean isValidEmailText(String emailAddress) {
        return pattern.matcher(emailAddress).matches();
    }
}
