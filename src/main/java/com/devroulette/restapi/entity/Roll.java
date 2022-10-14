package com.devroulette.restapi.entity;

import com.devroulette.restapi.utils.RouletteUtils;
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
    private Integer result;
    @NonNull
    private Float tileCoverageFactor;
    @CreationTimestamp
    private Timestamp timestamp;

    public String getColor() {
        return RouletteUtils.getNumberColor(this.result);
    }

    public String getType() {
        return RouletteUtils.isEven(this.result) ? "EVEN" : "ODD";
    }
}
