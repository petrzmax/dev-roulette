package com.devroulette.restapi.entity;

import com.devroulette.restapi.constant.BetType;
import com.devroulette.restapi.constant.ErrorMessages;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
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

    @OneToOne
    @JoinColumn(name = "bot_id")
    private Bot bot;

    @Setter
    @OneToOne
    @JoinColumn(name = "roll_id")
    private Roll roll;

    public boolean isVictory() {
        if (this.roll != null) {
            return this.betType.equals(this.roll.getColor()) || this.betType.equals(this.roll.getParity());
        }
        throw new IllegalStateException(ErrorMessages.ROLL_NOT_ASSIGNED);
    }

    public long getPrize() {
        return this.betType.getPrize(this.amount);
    }
}
