package net.happykoo.feed.auth.domain;

import lombok.Getter;

public class Password {
    @Getter
    private final String encryptedPassword;

    private Password(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public static Password createEncryptPassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("패스워드는 빈 값이 될 수 없습니다.");
        }
        return new Password(SHA256.encrypt(password));
    }

    public static Password createPassword(String encryptedPassword) {
        if (encryptedPassword == null || encryptedPassword.isBlank()) {
            throw new IllegalArgumentException("패스워드는 빈 값이 될 수 없습니다.");
        }
        return new Password(encryptedPassword);
    }

    public boolean matchPassword(String password) {
        return encryptedPassword.equals(SHA256.encrypt(password));
    }
}
