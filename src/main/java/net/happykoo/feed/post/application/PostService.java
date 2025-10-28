package net.happykoo.feed.post.application;

import net.happykoo.feed.post.application.dto.CreatePostRequestDto;
import net.happykoo.feed.post.application.dto.LikeRequestDto;
import net.happykoo.feed.post.application.dto.UpdatePostRequestDto;
import net.happykoo.feed.post.application.interfaces.LikeRepository;
import net.happykoo.feed.post.application.interfaces.PostRepository;
import net.happykoo.feed.post.domain.Post;
import net.happykoo.feed.post.domain.content.Content;
import net.happykoo.feed.post.domain.content.PostContent;
import net.happykoo.feed.user.application.UserService;
import net.happykoo.feed.user.domain.User;

public class PostService {
    private final UserService userService;
    private final PostRepository postRepository;
    private final LikeRepository<Post> likeRepository;

    public PostService(UserService userService, PostRepository postRepository, LikeRepository<Post> likeRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Post createPost(CreatePostRequestDto dto) {
        User author = userService.getUser(dto.userId());
        Content content = new PostContent(dto.content());
        Post post = new Post(null, author, content, dto.state());

        return postRepository.save(post);
    }

    public Post updatePost(UpdatePostRequestDto dto) {
        Post post = getPost(dto.postId());
        User author = userService.getUser(dto.userId());

        post.updateContent(author, dto.content(), dto.state());

        return postRepository.save(post);
    }

    public void likePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            return;
        }

        post.like(user);
        likeRepository.like(post, user);
    }

    public void unlikePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (!likeRepository.checkLike(post, user)) {
            return;
        }

        post.unlike(user);
        likeRepository.unlike(post, user);
    }
}
