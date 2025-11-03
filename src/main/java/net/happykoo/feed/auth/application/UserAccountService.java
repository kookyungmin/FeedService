package net.happykoo.feed.auth.application;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.auth.application.dto.CreateUserAccountRequestDto;
import net.happykoo.feed.auth.application.interfaces.EmailVerificationRepository;
import net.happykoo.feed.auth.application.interfaces.UserAccountRepository;
import net.happykoo.feed.auth.domain.Email;
import net.happykoo.feed.auth.domain.UserAccount;
import net.happykoo.feed.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final EmailVerificationRepository emailVerificationRepository;

    public Long registerUser(CreateUserAccountRequestDto dto) {
        Email email = Email.createEmail(dto.email());
        if (!emailVerificationRepository.isVerified(email)) {
            throw new IllegalArgumentException("이메일 인증을 하지 않았습니다.");
        }

        UserAccount userAccount = new UserAccount(dto.email(), dto.password(), dto.role());
        User user = new User(dto.name(), dto.profileImg());
        userAccount = userAccountRepository.registerUser(userAccount, user);

        return userAccount.getUserId();
    }
}
