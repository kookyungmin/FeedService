package net.happykoo.feed.user.repository;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.user.application.interfaces.UserRelationRepository;
import net.happykoo.feed.user.domain.User;
import net.happykoo.feed.user.repository.entity.UserEntity;
import net.happykoo.feed.user.repository.entity.UserRelationEntity;
import net.happykoo.feed.user.repository.entity.UserRelationIdEntity;
import net.happykoo.feed.user.repository.jpa.JpaUserRelationRepository;
import net.happykoo.feed.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;

    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        return jpaUserRelationRepository.existsById(id);
    }

    @Override
    @Transactional
    public void save(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        UserRelationEntity userRelationEntity = new UserRelationEntity(id);
        jpaUserRelationRepository.save(userRelationEntity);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
    }

    @Override
    @Transactional
    public void delete(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.deleteById(id);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
    }
}
