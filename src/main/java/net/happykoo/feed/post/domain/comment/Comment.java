package net.happykoo.feed.post.domain.comment;

import net.happykoo.feed.common.domain.PositiveIntegerCounter;
import net.happykoo.feed.post.domain.Post;
import net.happykoo.feed.post.domain.content.CommentContent;
import net.happykoo.feed.post.domain.content.Content;
import net.happykoo.feed.user.domain.User;

import java.util.Objects;

public class Comment {
    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;

    public static Comment createComment(Long id, User author, Post post, String content) {
        return new Comment(id, author, post, new CommentContent(content));
    }

    public static Comment createComment(User author, Post post, String content) {
        return new Comment(null, author, post, new CommentContent(content));
    }

    public Comment(Long id, User author, Post post, Content content) {
        if (author == null) {
            throw new IllegalArgumentException();
        }

        if (post == null) {
            throw new IllegalArgumentException();
        }

        if (content == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.post = post;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
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

    public void updateContent(User user, String updatedContent) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        this.content.updateContent(updatedContent);
    }

    public int likeCount() {
        return likeCount.getCount();
    }

    public String getContent() {
        return content.getContent();
    }

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public User getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
