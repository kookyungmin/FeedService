package net.happykoo.feed.auth.repository;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.auth.application.interfaces.UserAccountRepository;
import net.happykoo.feed.auth.domain.UserAccount;
import net.happykoo.feed.auth.repository.entity.UserAccountEntity;
import net.happykoo.feed.auth.repository.jpa.JpaUserAccountRepository;
import net.happykoo.feed.user.application.interfaces.UserRepository;
import net.happykoo.feed.user.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserAccountRepositoryImpl implements UserAccountRepository {
    private final JpaUserAccountRepository userAccountRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserAccount registerUser(UserAccount account, User user) {
        User savedUser = userRepository.save(user);
        UserAccountEntity entity = new UserAccountEntity(account, savedUser.getId());

        userAccountRepository.save(entity);

        return entity.toUserAccount();
    }
}
