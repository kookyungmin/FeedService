package net.happykoo.feed.auth.repository.jpa;

import net.happykoo.feed.auth.repository.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserAccountRepository extends JpaRepository<UserAccountEntity, String> {
}
