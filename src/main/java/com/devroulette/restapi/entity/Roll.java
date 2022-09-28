package com.devroulette.restapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "ROLL_HISTORY")
public class Roll {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String seed;
    @NonNull
    private Integer result;

    @CreationTimestamp
    private Timestamp timestamp;
}
