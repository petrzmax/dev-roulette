package com.devroulette.restapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
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
}
