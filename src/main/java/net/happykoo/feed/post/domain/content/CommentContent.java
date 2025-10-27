package net.happykoo.feed.post.domain.content;

public class CommentContent extends Content {
    private final int MAX_COMMENT_LENGTH = 100;

    public CommentContent(String content) {
        super(content);
    }

    @Override
    protected void checkText(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (content.length() > MAX_COMMENT_LENGTH) {
            throw new IllegalArgumentException();
        }

    }
}
