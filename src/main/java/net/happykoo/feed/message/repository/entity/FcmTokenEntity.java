package net.happykoo.feed.message.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "feed_fcm_token")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FcmTokenEntity {
    @Id
    private Long userId;
    private String fcmToken;
}
