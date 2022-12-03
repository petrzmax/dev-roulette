package com.devroulette.restapi.entity;

import com.devroulette.restapi.constant.BetType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "ROLLS")
public class Roll extends AbstractEntity {
    @NonNull
    private String seed;
    @NonNull
    private int result;
    @NonNull
    private float tileCoverageFactor;
    @CreationTimestamp
    private Timestamp timestamp;

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
        throw new IllegalStateException("The number is out of roulette range!");
    }

    private boolean isEven() {
        return this.result != 0 && this.result % 2 == 0;
    }

    public BetType getType() {
        return this.isEven() ? BetType.EVEN : BetType.ODD;
    }
}
