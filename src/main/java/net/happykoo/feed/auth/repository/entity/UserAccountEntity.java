package net.happykoo.feed.auth.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.happykoo.feed.auth.domain.UserAccount;
import net.happykoo.feed.common.repository.entity.TimeBaseEntity;

@Entity
@Table(name = "feed_user_account")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserAccountEntity extends TimeBaseEntity {
    @Id
    private String email;
    private String password;
    private String userRole;
    private Long userId;

    public UserAccountEntity(UserAccount userAccount, Long userId) {
        this.email = userAccount.getEmail();
        this.password = userAccount.password();
        this.userRole = userAccount.getUserRole();
        this.userId = userId;
    }

    public UserAccount toUserAccount() {
        return new UserAccount(email, password, userRole, userId);
    }
}
