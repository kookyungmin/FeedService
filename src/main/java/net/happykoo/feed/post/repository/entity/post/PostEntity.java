package net.happykoo.feed.post.repository.entity.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.happykoo.feed.common.domain.PositiveIntegerCounter;
import net.happykoo.feed.common.repository.entity.TimeBaseEntity;
import net.happykoo.feed.post.domain.Post;
import net.happykoo.feed.post.domain.content.PostContent;
import net.happykoo.feed.post.domain.content.PostPublicationState;
import net.happykoo.feed.user.repository.entity.UserEntity;

@Entity
@Table(name = "feed_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostEntity extends TimeBaseEntity {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)) //FK 제외
    private UserEntity author;

    private String content;

    private Integer likeCount;

    @Convert(converter = PostPublicationStateConverter.class)
    private PostPublicationState state;

    public PostEntity(Post post) {
        this.id = post.getId();
        this.author = new UserEntity(post.getAuthor());
        this.content = post.getContent();
        this.state = post.getState();
        this.likeCount = post.likeCount();
    }

    public Post toPost() {
        return Post.builder()
                .id(id)
                .author(author.toUser())
                .content(new PostContent(content))
                .state(state)
                .likeCounter(new PositiveIntegerCounter(likeCount))
                .build();
    }
}
