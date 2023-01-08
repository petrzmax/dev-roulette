package com.devroulette.restapi.entity;

import com.devroulette.restapi.constant.BetType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "BETS")
public class Bet extends AbstractEntity {
    @NonNull
    @Enumerated(EnumType.STRING) // It will consume much more db memory. Use only for testing
    private BetType betType;
    @NonNull
    private long amount;

    @NonNull
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @OneToOne
    @JoinColumn(name = "roll_id")
    private Roll roll;

    public boolean isVictory() {
        if (this.roll != null) {
            return this.betType.equals(this.roll.getColor()) || this.betType.equals(this.roll.getType());
        }
        throw new IllegalStateException("This bet has no Roll assigned!");
    }

    public long getPrize() {
        return this.betType.getPrize(this.amount);
    }
}
