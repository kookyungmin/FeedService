package net.happykoo.feed.admin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.happykoo.feed.admin.ui.dto.GetTableListResponse;
import net.happykoo.feed.admin.ui.dto.users.GetUserTableRequestDto;
import net.happykoo.feed.admin.ui.dto.users.GetUserTableResponseDto;
import net.happykoo.feed.admin.ui.query.AdminTableQueryRepository;
import net.happykoo.feed.auth.repository.entity.QUserAccountEntity;
import net.happykoo.feed.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminTableQueryRepositoryImpl implements AdminTableQueryRepository {
    private final JPAQueryFactory queryFactory;
    private static final QUserAccountEntity userAccountEntity = QUserAccountEntity.userAccountEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity;

    @Override
    public GetTableListResponse<GetUserTableResponseDto> getUserTableData(GetUserTableRequestDto dto) {
//        아래 방식은 커버링 인덱스가 사용되지 않기에 속도가 느림
//        List<GetUserTableResponseDto> userList = queryFactory
//                .select(
//                        Projections.fields(
//                                GetUserTableResponseDto.class,
//                                userEntity.id.as("id"),
//                                userAccountEntity.email.as("email"),
//                                userEntity.name.as("name"),
//                                userAccountEntity.userRole.as("role"),
//                                userEntity.createdAt.as("createdAt"),
//                                userEntity.updatedAt.as("updatedAt"),
//                                userAccountEntity.lastLoginAt.as("lastLoginAt")
//                        )
//                )
//                .from(userEntity)
//                .join(userAccountEntity).on(userEntity.id.eq(userAccountEntity.userId))
//                .where(likeName(dto.getName()))
//                .orderBy(userEntity.id.desc())
//                .offset(dto.getOffset())
//                .limit(dto.getLimit())
//                .fetch();

//        커버링 인덱스(primary key는 클러스터링 인덱스 기본)를 사용하기에 속도가 빠름
        List<Long> ids = queryFactory
                .select(userEntity.id)
                .from(userEntity)
                .where(likeName(dto.getName()))
                .orderBy(userEntity.id.desc())
                .offset(dto.getOffset())
                .limit(dto.getLimit())
                .fetch();

        List<GetUserTableResponseDto> userList = queryFactory
            .select(
                    Projections.fields(
                            GetUserTableResponseDto.class,
                            userEntity.id.as("id"),
                            userAccountEntity.email.as("email"),
                            userEntity.name.as("name"),
                            userAccountEntity.userRole.as("role"),
                            userEntity.createdAt.as("createdAt"),
                            userEntity.updatedAt.as("updatedAt"),
                            userAccountEntity.lastLoginAt.as("lastLoginAt")
                    )
            )
            .from(userEntity)
            .join(userAccountEntity).on(userEntity.id.eq(userAccountEntity.userId))
            .where(userEntity.id.in(ids))
            .orderBy(userEntity.id.desc())
            .fetch();

        int totalCount = queryFactory
                .select(userEntity.id)
                .from(userEntity)
                .where(likeName(dto.getName()))
                .fetch()
                .size();

        return new GetTableListResponse<>(userList, totalCount);
    }

    private BooleanExpression likeName(String name) {
        if (name == null || name.isBlank()) return null;
        return userEntity.name.like(name + "%");
    }
}
