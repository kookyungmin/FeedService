package net.happykoo.feed.post.repository.entity.comment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.happykoo.feed.common.domain.PositiveIntegerCounter;
import net.happykoo.feed.common.repository.entity.TimeBaseEntity;
import net.happykoo.feed.post.domain.comment.Comment;
import net.happykoo.feed.post.domain.content.CommentContent;
import net.happykoo.feed.post.repository.entity.post.PostEntity;
import net.happykoo.feed.user.repository.entity.UserEntity;

@Entity
@Table(name = "feed_comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentEntity extends TimeBaseEntity {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private PostEntity post;

    private String content;

    private Integer likeCount;

    public CommentEntity(Comment comment) {
        this.id = comment.getId();
        this.author = new UserEntity(comment.getAuthor());
        this.post = new PostEntity(comment.getPost());
        this.content = comment.getContent();
        this.likeCount = comment.likeCount();
    }

    public Comment toComment() {
        return Comment.builder()
                .id(id)
                .author(author.toUser())
                .post(post.toPost())
                .content(new CommentContent(content))
                .likeCount(new PositiveIntegerCounter(likeCount))
                .build();
    }
}
