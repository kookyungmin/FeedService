package net.happykoo.feed.auth.repository.jpa;

import net.happykoo.feed.auth.repository.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserAccountRepository extends JpaRepository<UserAccountEntity, String> {
}
