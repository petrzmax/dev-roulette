package com.devroulette.restapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "BOTS")
public class Bot extends AbstractTransactableEntity {
    @NonNull
    private String name;
    private String scriptBody;
    private boolean enabled;
    private String errorMessage;
}
