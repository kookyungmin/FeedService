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
    private final PositiveIntegerCounter likeCounter;
    private PostPublicationState state;

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
