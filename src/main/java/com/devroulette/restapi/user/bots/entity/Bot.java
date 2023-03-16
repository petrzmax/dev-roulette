package com.devroulette.restapi.user.bots.entity;

import com.devroulette.restapi.common.entity.AbstractTransactableEntity;
import com.devroulette.restapi.roulette.bets.entity.BotBet;
import com.devroulette.restapi.user.bots.enums.BotStatus;
import com.devroulette.restapi.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "BOTS")
public class Bot extends AbstractTransactableEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "bot", cascade = CascadeType.REMOVE)
    private List<BotBet> bets;

    @NonNull
    private String name;
    private String scriptBody;
    @Enumerated(EnumType.STRING) // It will consume much more db memory. Use only for testing
    private BotStatus status;
    private String errorMessage;

    public Bot(long balance, User user, String name) {
        super(balance);
        this.user = user;
        this.name = name;
    }
}
