package com.bank.cardservice.model;

import com.bank.cardservice.enums.CardType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name="cards")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(columnDefinition = "varchar(16)")
    String number;
    @Column(name = "date_of_issue")
    LocalDate issueDate;
    @Column(name = "expiry_date")
    LocalDate expiryDate;
    @Column(name = "client_id")
    Long clientId;
    @Column(columnDefinition = "boolean default false")
    Boolean locked;
    BigDecimal balance;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    CardType type;


}
