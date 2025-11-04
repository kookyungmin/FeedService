package net.happykoo.feed.admin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.happykoo.feed.admin.ui.dto.users.GetDailyRegisterUserResponseDto;
import net.happykoo.feed.admin.ui.query.UserStatsQueryRepository;
import net.happykoo.feed.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

import static net.happykoo.feed.common.utils.TimeCalculator.getDateDaysAgo;

@Repository
@RequiredArgsConstructor
public class UserStatsQueryRepositoryImpl implements UserStatsQueryRepository {
    private final JPAQueryFactory queryFactory;
    private static final QUserEntity userEntity = QUserEntity.userEntity;

    @Override
    public List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays) {
        return queryFactory
                .select(
                        Projections.fields(
                                GetDailyRegisterUserResponseDto.class,
                                userEntity.regDate.as("date"),
                                userEntity.count().as("count")
                        )
                )
                .from(userEntity)
                .where(userEntity.regDate.after(getDateDaysAgo(beforeDays)))
                .groupBy(userEntity.regDate)
                .orderBy(userEntity.regDate.asc())
                .fetch();
    }
}
