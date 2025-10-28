package net.happykoo.feed.post.domain.content;

import net.happykoo.feed.post.domain.common.DateTimeInfo;

public abstract class Content {
    protected String content;
    protected final DateTimeInfo dateTimeInfo;

    protected Content(String content) {
        checkText(content);
        this.content = content;
        this.dateTimeInfo = new DateTimeInfo();
    }

    public void updateContent(String updatedContent) {
        checkText(updatedContent);
        this.content = updatedContent;
        this.dateTimeInfo.updateDatetime();
    }

    public String getContent() {
        return content;
    }

    protected abstract void checkText(String content);
}
