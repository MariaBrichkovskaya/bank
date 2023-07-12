package com.bank.cardservice.model;

import com.bank.cardservice.enums.OperationType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@Builder
@ToString
@RequiredArgsConstructor
@Entity
@Table(name="operations")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "sum")
    BigDecimal sum;
    @Column(name = "date")
    LocalDate date;
    @Column(name = "time")
    LocalTime time;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    OperationType type;
    @Column(columnDefinition = "varchar(16)")
    String cardNumber;
    @PrePersist
    void initialize() {
        date = LocalDate.now();
        time = LocalTime.now();
    }

}
