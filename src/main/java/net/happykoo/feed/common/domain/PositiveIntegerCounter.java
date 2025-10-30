package net.happykoo.feed.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PositiveIntegerCounter {
    @Getter
    private int count;

    public void increase() {
        this.count++;
    }

    public void decrease() {
        if (this.count <= 0) {
            return;
        }
        this.count--;
    }
}
