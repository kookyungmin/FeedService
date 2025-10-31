package net.happykoo.feed.fake;

import net.happykoo.feed.post.application.CommentService;
import net.happykoo.feed.post.application.PostService;
import net.happykoo.feed.post.application.interfaces.CommentRepository;
import net.happykoo.feed.post.application.interfaces.LikeRepository;
import net.happykoo.feed.post.application.interfaces.PostRepository;
import net.happykoo.feed.post.repository.FakeCommentRepository;
import net.happykoo.feed.post.repository.FakeLikeRepository;
import net.happykoo.feed.post.repository.FakePostRepository;
import net.happykoo.feed.user.application.UserRelationService;
import net.happykoo.feed.user.application.UserService;
import net.happykoo.feed.user.application.interfaces.UserRelationRepository;
import net.happykoo.feed.user.application.interfaces.UserRepository;
import net.happykoo.feed.user.application.repository.FakeUserRelationRepository;
import net.happykoo.feed.user.application.repository.FakeUserRepository;

public class FakeObjectFactory {
    private static final UserRepository userRepository = new FakeUserRepository();
    private static final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository postRepository = new FakePostRepository();
    private static final CommentRepository commentRepository = new FakeCommentRepository();
    private static final LikeRepository likeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(userRepository);
    private static final UserRelationService userRelationService = new UserRelationService(userService, userRelationRepository);
    private static final PostService postService = new PostService(userService, postRepository, likeRepository);
    private static final CommentService commentService = new CommentService(userService, postService, commentRepository, likeRepository);
    private FakeObjectFactory() {}

    public static UserService userService() {
        return userService;
    }

    public static UserRelationService userRelationService() {
        return userRelationService;
    }

    public static PostService postService() {
        return postService;
    }

    public static CommentService commentService() {
        return commentService;
    }
}
