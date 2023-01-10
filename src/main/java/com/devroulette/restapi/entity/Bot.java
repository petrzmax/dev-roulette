package com.devroulette.restapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "BOTS")
public class Bot extends AbstractTransactableEntity {
    @NonNull
    private String name;

}
