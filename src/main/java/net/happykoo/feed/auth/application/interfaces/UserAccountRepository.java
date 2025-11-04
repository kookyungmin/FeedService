package net.happykoo.feed.auth.application.interfaces;

import net.happykoo.feed.auth.domain.UserAccount;
import net.happykoo.feed.user.domain.User;

public interface UserAccountRepository {
    UserAccount registerUser(UserAccount account, User user);
    UserAccount loginUser(String email, String password);
}
