package net.happykoo.feed.post.application.repository;

import net.happykoo.feed.post.application.interfaces.PostRepository;
import net.happykoo.feed.post.domain.Post;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakePostRepository implements PostRepository {
    private final Map<Long, Post> store = new HashMap<>();
    private Long sequence = 1L;

    @Override
    public Post save(Post post) {
        Post newPost = post.getId() == null ? Post.createPost(sequence++, post.getAuthor(), post.getContent())
                : Post.createPost(post.getId(), post.getAuthor(), post.getContent());
        store.put(newPost.getId(), newPost);

        return newPost;
    }

    @Override
    public Post findById(Long id) {
        return store.get(id);
    }
}
