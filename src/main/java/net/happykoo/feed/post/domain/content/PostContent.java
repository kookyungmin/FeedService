package net.happykoo.feed.post.domain.content;

public class PostContent extends Content {
    private final int MIN_POST_LENGTH = 5;
    private final int MAX_POST_LENGTH = 500;

    public PostContent(String content) {
        super(content);
    }

    @Override
    protected void checkText(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (content.length() < MIN_POST_LENGTH) {
            throw new IllegalArgumentException();
        }

        if (content.length() > MAX_POST_LENGTH) {
            throw new IllegalArgumentException();
        }
    }
}
