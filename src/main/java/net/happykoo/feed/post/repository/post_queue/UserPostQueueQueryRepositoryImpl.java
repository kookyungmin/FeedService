package net.happykoo.feed.post.repository.post_queue;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.happykoo.feed.post.repository.entity.like.QLikeEntity;
import net.happykoo.feed.post.repository.entity.post.QPostEntity;
import net.happykoo.feed.post.repository.entity.post.QUserPostQueueEntity;
import net.happykoo.feed.post.ui.dto.GetPostContentResponseDto;
import net.happykoo.feed.user.repository.entity.QUserEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

import static net.happykoo.feed.post.repository.entity.like.LikeTarget.POST;

@Repository
@Profile("!test")
@RequiredArgsConstructor
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository {
    private final JPAQueryFactory queryFactory;
    private static final QUserPostQueueEntity userPostQueueEntity = QUserPostQueueEntity.userPostQueueEntity;
    private static final QPostEntity postEntity = QPostEntity.postEntity;
    private static final QUserEntity userEntity= QUserEntity.userEntity;
    private static final QLikeEntity likeEntity = QLikeEntity.likeEntity;

    @Override
    public List<GetPostContentResponseDto> getPostList(Long userId, Long lastPostId) {
        return queryFactory
                .select(
                        Projections.fields(
                                GetPostContentResponseDto.class,
                                postEntity.id.as("id"),
                                postEntity.content.as("content"),
                                userEntity.id.as("userId"),
                                userEntity.name.as("userName"),
                                userEntity.profileImageUrl.as("userProfileImageUrl"),
                                postEntity.createdAt.as("createdAt"),
                                postEntity.updatedAt.as("updatedAt"),
                                postEntity.commentCount.as("commentCount"),
                                postEntity.likeCount.as("likeCount"),
                                likeEntity.isNotNull().as("isLikedByMe")
                        )
                )
                .from(userPostQueueEntity)
                .join(postEntity).on(userPostQueueEntity.postId.eq(postEntity.id))
                .join(userEntity).on(userPostQueueEntity.authorId.eq(userEntity.id))
                .leftJoin(likeEntity).on(hasLike(userId))
                .where(
                        userPostQueueEntity.userId.eq(userId),
                        hasLastData(lastPostId)
                )
                .orderBy(userPostQueueEntity.postId.desc())
                .limit(20)
                .fetch();
    }

    private BooleanExpression hasLastData(Long lastId) {
        if (lastId == null) {
            return null;
        }
        return postEntity.id.lt(lastId);
    }

    private BooleanExpression hasLike(Long userId) {
        if (userId == null) {
            return null;
        }
        return postEntity.id.eq(likeEntity.id.targetId)
                .and(likeEntity.id.targetType.eq(POST.name()))
                .and(likeEntity.id.userId.eq(userId));
    }
}
