package net.happykoo.feed.post.application;

import net.happykoo.feed.post.application.dto.CreateCommentRequestDto;
import net.happykoo.feed.post.application.dto.LikeRequestDto;
import net.happykoo.feed.post.application.dto.UpdateCommentRequestDto;
import net.happykoo.feed.post.application.interfaces.CommentRepository;
import net.happykoo.feed.post.application.interfaces.LikeRepository;
import net.happykoo.feed.post.domain.Post;
import net.happykoo.feed.post.domain.comment.Comment;
import net.happykoo.feed.user.application.UserService;
import net.happykoo.feed.user.domain.User;

public class CommentService {
    private final UserService userService;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final LikeRepository<Comment> likeRepository;

    public CommentService(UserService userService, PostService postService, CommentRepository commentRepository, LikeRepository<Comment> likeRepository) {
        this.userService = userService;
        this.postService = postService;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Comment createComment(CreateCommentRequestDto dto) {
        Post post = postService.getPost(dto.postId());
        User author = userService.getUser(dto.userId());

        Comment comment = Comment.createComment(author, post, dto.content());
        return commentRepository.save(comment);
    }

    public Comment updateComment(UpdateCommentRequestDto dto) {
        Comment comment = getComment(dto.commentId());
        User author = userService.getUser(dto.userId());

        comment.updateContent(author, dto.content());

        return commentRepository.save(comment);
    }

    public void like(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(comment, user)) {
            return;
        }

        comment.like(user);
        likeRepository.like(comment, user);
    }

    public void unlike(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (!likeRepository.checkLike(comment, user)) {
            return;
        }

        comment.unlike(user);
        likeRepository.unlike(comment, user);
    }
}
