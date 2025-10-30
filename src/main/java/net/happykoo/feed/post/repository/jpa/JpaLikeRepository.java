package net.happykoo.feed.post.repository.jpa;

import net.happykoo.feed.post.repository.entity.like.LikeEntity;
import net.happykoo.feed.post.repository.entity.like.LikeIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeIdEntity> {
}
