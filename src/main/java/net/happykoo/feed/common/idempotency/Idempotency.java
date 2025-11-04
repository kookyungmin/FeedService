package net.happykoo.feed.common.idempotency;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.happykoo.feed.common.ui.Response;

@Getter
@RequiredArgsConstructor
public class Idempotency {
    private final String key;
    private final Response<?> response;
}
