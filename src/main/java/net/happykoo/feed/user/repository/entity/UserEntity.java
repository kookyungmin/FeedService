package net.happykoo.feed.user.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.happykoo.feed.common.domain.PositiveIntegerCounter;
import net.happykoo.feed.common.repository.entity.TimeBaseEntity;
import net.happykoo.feed.user.domain.User;
import net.happykoo.feed.user.domain.UserInfo;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "feed_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate //변경된 부분만 update 쿼리 생성
public class UserEntity extends TimeBaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImageUrl;
    private Integer followerCount;
    private Integer followingCount;

    //커버링 인덱스 타기 위함(date(created_at) 는 temporary table 을 사용함)
    @CreatedDate
    @Column(updatable = false)
    private LocalDate regDate;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.profileImageUrl = user.getProfileImageUrl();
        this.followerCount = user.followerCount();
        this.followingCount = user.followingCount();
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .userInfo(new UserInfo(name, profileImageUrl))
                .followerCounter(new PositiveIntegerCounter(followerCount))
                .followingCounter(new PositiveIntegerCounter(followingCount))
                .build();
    }
}
