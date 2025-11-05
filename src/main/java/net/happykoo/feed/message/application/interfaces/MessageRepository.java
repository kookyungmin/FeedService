package net.happykoo.feed.message.application.interfaces;

import net.happykoo.feed.user.domain.User;

public interface MessageRepository {
    void sendLikeMessage(User sender, User targetUser);
}
