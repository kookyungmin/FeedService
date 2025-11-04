package net.happykoo.feed.common.idempotency.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.happykoo.feed.common.idempotency.Idempotency;
import net.happykoo.feed.common.utils.ResponseObjectMapper;

@Entity
@Table(name = "feed_idempotency")
@NoArgsConstructor
@AllArgsConstructor
public class IdempotencyEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String idempotencyKey;

    @Getter
    @Column(nullable = false)
    private String response;

    public IdempotencyEntity(Idempotency idempotency) {
        this.idempotencyKey = idempotency.getKey();
        this.response = ResponseObjectMapper.toStringResponse(idempotency.getResponse());
    }

    public Idempotency toIdempotency() {
        return new Idempotency(idempotencyKey, ResponseObjectMapper.toResponseObject(response));
    }
}
