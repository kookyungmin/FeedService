package net.happykoo.feed.auth.domain;

import lombok.Getter;

public class UserAccount {
    private final Email email;
    private final Password password;
    private final UserRole userRole;
    @Getter
    private Long userId;

    public UserAccount(String email, String password, String userRole) {
        this.email = Email.createEmail(email);
        this.password = Password.createEncryptPassword(password);
        this.userRole = UserRole.valueOf(userRole);
    }

    public UserAccount(String email, String password, String userRole, Long userId) {
        this.email = Email.createEmail(email);
        this.password = Password.createPassword(password);
        this.userRole = UserRole.valueOf(userRole);
        this.userId = userId;
    }

    public String getEmail() {
        return this.email.getEmailAddress();
    }

    public String password() {
        return this.password.getEncryptedPassword();
    }

    public String getUserRole() {
        return this.userRole.name();
    }

    public boolean matchPassword(String password) {
        return this.password.matchPassword(password);
    }
}
