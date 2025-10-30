package net.happykoo.feed.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import net.happykoo.feed.common.domain.PositiveIntegerCounter;
import net.happykoo.feed.post.domain.content.Content;
import net.happykoo.feed.post.domain.content.PostContent;
import net.happykoo.feed.post.domain.content.PostPublicationState;
import net.happykoo.feed.user.domain.User;

import java.util.Objects;

@Builder
@AllArgsConstructor
public class Post {
    @Getter
    private final Long id;
    @Getter
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCounter;
    @Getter
    private PostPublicationState state;

    public static Post createPost(Long id, User author, String content) {
        return new Post(id, author, new PostContent(content));
    }

    public Post(Long id, User author, Content content) {
        this(id, author, content, PostPublicationState.PUBLIC);
    }

    public Post(Long id, User author, Content content, PostPublicationState state) {
        if (author == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCounter = new PositiveIntegerCounter();
        this.state = state;
    }

    public void like(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.likeCounter.increase();
    }

    public void unlike(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.likeCounter.decrease();
    }

    public void updateContent(User user, String updatedContent, PostPublicationState state) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        this.content.updateContent(updatedContent);
        this.state = state;
    }

    public int likeCount() {
        return likeCounter.getCount();
    }

    public String getContent() {
        return content.getContent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
