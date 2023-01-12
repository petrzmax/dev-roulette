package com.devroulette.restapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "BOTS")
public class Bot extends AbstractTransactableEntity {
    @NonNull
    private String name;
    @Setter
    private String scriptBody;
}
