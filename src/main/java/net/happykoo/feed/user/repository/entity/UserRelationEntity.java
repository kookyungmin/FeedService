package net.happykoo.feed.user.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.happykoo.feed.common.repository.entity.TimeBaseEntity;

@Entity
@Table(name = "feed_user_relation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserRelationEntity extends TimeBaseEntity {
    @EmbeddedId
    private UserRelationIdEntity id;
}
