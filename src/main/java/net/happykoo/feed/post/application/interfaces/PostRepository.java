package net.happykoo.feed.post.application.interfaces;

import net.happykoo.feed.post.domain.Post;

import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long id);
}
