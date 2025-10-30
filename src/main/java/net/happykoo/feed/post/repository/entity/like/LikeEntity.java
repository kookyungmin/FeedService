package net.happykoo.feed.post.repository.entity.like;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.happykoo.feed.common.repository.entity.TimeBaseEntity;
import net.happykoo.feed.post.domain.Post;
import net.happykoo.feed.post.domain.comment.Comment;
import net.happykoo.feed.user.domain.User;

import static net.happykoo.feed.post.repository.entity.like.LikeTarget.COMMENT;
import static net.happykoo.feed.post.repository.entity.like.LikeTarget.POST;

@Entity
@Table(name = "feed_like")
@NoArgsConstructor
@Getter
public class LikeEntity extends TimeBaseEntity {
    @EmbeddedId
    private LikeIdEntity id;

    public LikeEntity(Post post, User likeUser) {
        this.id = new LikeIdEntity(post.getId(), likeUser.getId(), POST.name());
    }

    public LikeEntity(Comment comment, User likeUser) {
        this.id = new LikeIdEntity(comment.getId(), likeUser.getId(), COMMENT.name());
    }
}
