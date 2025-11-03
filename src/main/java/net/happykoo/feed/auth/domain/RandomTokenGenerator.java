package net.happykoo.feed.auth.domain;

import java.security.SecureRandom;

public class RandomTokenGenerator {
    private static final String CHARACTER = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int TOKEN_LENGTH = 16;

    private static final SecureRandom random = new SecureRandom();

    private RandomTokenGenerator() {}
    public static String generateToken() {
        StringBuilder token = new StringBuilder(TOKEN_LENGTH);

        for(int i = 0; i < TOKEN_LENGTH; i++) {
            token.append(CHARACTER.charAt(random.nextInt(TOKEN_LENGTH)));
        }

        return token.toString();
    }
}
