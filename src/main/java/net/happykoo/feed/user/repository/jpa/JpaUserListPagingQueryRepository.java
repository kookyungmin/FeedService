package net.happykoo.feed.user.repository.jpa;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.happykoo.feed.user.application.dto.GetUserListResponseDto;
import net.happykoo.feed.user.repository.entity.QUserEntity;
import net.happykoo.feed.user.repository.entity.QUserRelationEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaUserListPagingQueryRepository {
    private static final QUserEntity user = QUserEntity.userEntity;
    private static final QUserRelationEntity relation = QUserRelationEntity.userRelationEntity;
    private final JPAQueryFactory jpaQueryFactory;

    public List<GetUserListResponseDto> getFollowerList(Long userId, Long lastFollowerId) {
        return jpaQueryFactory.select(
                Projections.fields(GetUserListResponseDto.class)
        )
        .from(relation)
        .join(user).on(relation.id.followerUserId.eq(user.id))
        .where(relation.id.followingUserId.eq(userId),
                hasLastData(lastFollowerId))
        .orderBy(user.id.desc())
        .limit(20)
        .fetch();
    }

    private BooleanExpression hasLastData(Long lastId) {
        if (lastId == null) return null;
        return user.id.lt(lastId);
    }
}
