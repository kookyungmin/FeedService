package net.happykoo.feed.message.repository;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import net.happykoo.feed.message.application.interfaces.MessageRepository;
import net.happykoo.feed.message.repository.entity.FcmTokenEntity;
import net.happykoo.feed.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FcmMessageRepository implements MessageRepository {
    private final JpaFcmTokenRepository jpaFcmTokenRepository;
    private static final String LIKE_MESSAGE_TEMPLATE = "%s님이 %s 글에 좋아요를 눌렀습니다.";
    private static final String MESSAGE_KEY = "message";

    @Override
    public void sendLikeMessage(User sender, User targetUser) {
        Optional<FcmTokenEntity> tokenEntityOpt = jpaFcmTokenRepository.findById(sender.getId());
        if (tokenEntityOpt.isEmpty()) {
            return;
        }

        FcmTokenEntity tokenEntity = tokenEntityOpt.get();
        Message message = Message.builder()
                .putData(MESSAGE_KEY, LIKE_MESSAGE_TEMPLATE.formatted(sender.getName(), targetUser.getName()))
                .setToken(tokenEntity.getFcmToken())
                .build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }
}
