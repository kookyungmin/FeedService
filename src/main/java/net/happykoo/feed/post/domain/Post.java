package net.happykoo.feed.post.domain;

import net.happykoo.feed.common.domain.PositiveIntegerCounter;
import net.happykoo.feed.post.domain.content.Content;
import net.happykoo.feed.post.domain.content.PostPublicationState;
import net.happykoo.feed.user.domain.User;

import java.util.Objects;

public class Post {
    private final Long id;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;

    public Post(Long id, User author, Content content) {
        if (author == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = PostPublicationState.PUBLIC;
    }

    public void like(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.likeCount.increase();
    }

    public void unlike(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.likeCount.decrease();
    }

    public void updateContent(User user, String updatedContent, PostPublicationState state) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        this.content.updateContent(updatedContent);
        this.state = state;
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
