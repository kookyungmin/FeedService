package net.happykoo.feed.acceptance.util;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AcceptanceTestTemplate {
    @Autowired
    private DatabaseCleaner cleaner;

    @Autowired
    private DataLoader loader;

    @BeforeEach
    public void setup() {
        cleaner.execute();
        loader.loadData();
    }
}
