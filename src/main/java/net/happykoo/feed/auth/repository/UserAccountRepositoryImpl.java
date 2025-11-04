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
    private final JpaUserAccountRepository jpaUserAccountRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserAccount registerUser(UserAccount account, User user) {
        User savedUser = userRepository.save(user);
        UserAccountEntity entity = new UserAccountEntity(account, savedUser.getId());

        jpaUserAccountRepository.save(entity);

        return entity.toUserAccount();
    }

    @Override
    public UserAccount loginUser(String email, String password) {
        UserAccountEntity entity = jpaUserAccountRepository.findById(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));
        UserAccount account = entity.toUserAccount();

        if (!account.matchPassword(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return account;
    }
}
