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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {
    private final UserService userService;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public PostService(UserService userService, PostRepository postRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id);
    }

    public Post createPost(CreatePostRequestDto dto) {
        User author = userService.getUser(dto.authorId());
        Content content = new PostContent(dto.content());
        Post post = new Post(null, author, content, dto.state());

        return postRepository.save(post);
    }

    public Post updatePost(Long id, UpdatePostRequestDto dto) {
        Post post = getPost(id);
        //임의로 authorId 를 수정할 수도 있기에 User 불러옴
        User author = userService.getUser(dto.authorId());

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
