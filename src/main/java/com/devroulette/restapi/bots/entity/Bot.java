package com.devroulette.restapi.bots.entity;

import com.devroulette.restapi.common.entity.AbstractTransactableEntity;
import com.devroulette.restapi.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "BOTS")
public class Bot extends AbstractTransactableEntity {
    // TODO Cascade settings
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NonNull
    private String name;
    private String scriptBody;
    private boolean enabled;
    private String errorMessage;

    public Bot(long balance, User user, String name) {
        super(balance);
        this.user = user;
        this.name = name;
    }
}
