package net.happykoo.feed.auth.application;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.auth.application.dto.CreateUserAccountRequestDto;
import net.happykoo.feed.auth.application.dto.LoginRequestDto;
import net.happykoo.feed.auth.application.dto.UserAccessTokenResponseDto;
import net.happykoo.feed.auth.application.interfaces.EmailVerificationRepository;
import net.happykoo.feed.auth.application.interfaces.UserAccountRepository;
import net.happykoo.feed.auth.domain.Email;
import net.happykoo.feed.auth.domain.TokenProvider;
import net.happykoo.feed.auth.domain.UserAccount;
import net.happykoo.feed.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final EmailVerificationRepository emailVerificationRepository;
    private final TokenProvider tokenProvider;

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

    public UserAccessTokenResponseDto login(LoginRequestDto dto) {
        UserAccount account = userAccountRepository.loginUser(dto.email(), dto.password(), dto.fcmToken());
        String token = tokenProvider.createToken(account.getUserId(), account.getUserRole());

        return new UserAccessTokenResponseDto(token);
    }
}
