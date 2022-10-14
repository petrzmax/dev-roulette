package com.devroulette.restapi.entity;

import com.devroulette.restapi.constant.BetType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "BETS")
public class Bet extends AbstractEntity {
    @NonNull
    private BetType betType;
    @NonNull
    private Long amount;

    @NonNull
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @OneToOne
    @JoinColumn(name = "roll_id")
    private Roll roll;

    @NonNull
    private Boolean processed = false;

    public void markAsProcessed() {
        this.processed = true;
    }

    public Boolean isVictory() {
        if (this.roll != null) {
            String betType = this.betType.toString();
            return betType.equals(this.getRoll().getColor()) || betType.equals(this.getRoll().getType());
        }
        throw new IllegalStateException("This bet has no Roll assigned!");
    }
}
