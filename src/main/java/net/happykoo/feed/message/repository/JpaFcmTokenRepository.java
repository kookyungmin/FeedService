package net.happykoo.feed.message.repository;

import net.happykoo.feed.message.repository.entity.FcmTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFcmTokenRepository extends JpaRepository<FcmTokenEntity, Long> {
}
