package net.happykoo.feed.common.idempotency.repository;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.common.idempotency.Idempotency;
import net.happykoo.feed.common.idempotency.IdempotencyRepository;
import net.happykoo.feed.common.idempotency.repository.entity.IdempotencyEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IdempotencyRepositoryImpl implements IdempotencyRepository {
    private final JpaIdempotencyRepository jpaIdempotencyRepository;

    @Override
    public Idempotency getByKey(String key) {
        return jpaIdempotencyRepository.findByIdempotencyKey(key)
                .map(IdempotencyEntity::toIdempotency)
                .orElse(null);
    }

    @Override
    public void save(Idempotency idempotency) {
        jpaIdempotencyRepository.save(new IdempotencyEntity(idempotency));
    }
}
