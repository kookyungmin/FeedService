package net.happykoo.feed.user.repository.jpa;

import net.happykoo.feed.user.repository.entity.UserRelationEntity;
import net.happykoo.feed.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {
}
