package com.devroulette.restapi.entity;

import com.devroulette.restapi.constant.BetType;
import com.devroulette.restapi.constant.ErrorMessages;
import com.mysema.commons.lang.Assert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "ROLLS")
public class Roll extends AbstractEntity {
    @NonNull
    private String seed;
    private int result;
    private float tileCoverageFactor;
    @CreationTimestamp
    private Timestamp timestamp;

    public Roll(String seed, int result, float tileCoverageFactor) {
        Assert.isTrue(result >= 0 && result <= 36, ErrorMessages.NUMBER_OUT_OF_ROULETTE_RANGE);

        this.seed = seed;
        this.result = result;
        this.tileCoverageFactor = tileCoverageFactor;
    }

    // TODO refactor, remove magic numbers
    public BetType getColor() {
        if (this.result == 0) {
            return BetType.GREEN;
        }
        if ((this.result >= 1 && this.result <= 10) || (this.result >= 19 && this.result <= 28)) {
            return this.isEven() ? BetType.BLACK : BetType.RED;
        }
        if ((this.result >= 11 && this.result <= 18) || (this.result >= 29 && this.result <= 36)) {
            return this.isEven() ? BetType.RED : BetType.BLACK;
        }

        return null;
    }

    private boolean isEven() {
        return this.result != 0 && this.result % 2 == 0;
    }

    public BetType getParity() {
        if (this.result == 0) {
            return BetType.GREEN;
        }

        return this.isEven() ? BetType.EVEN : BetType.ODD;
    }
}