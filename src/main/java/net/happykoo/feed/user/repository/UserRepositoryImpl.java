package net.happykoo.feed.user.repository;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.user.application.interfaces.UserRepository;
import net.happykoo.feed.user.domain.User;
import net.happykoo.feed.user.repository.entity.UserEntity;
import net.happykoo.feed.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity(user);
        return jpaUserRepository.save(entity)
                .toUser();
    }

    @Override
    public User findById(Long id) {
        return jpaUserRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new)
                .toUser();
    }
}
