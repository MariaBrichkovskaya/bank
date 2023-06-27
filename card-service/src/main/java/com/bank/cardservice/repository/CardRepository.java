package com.bank.cardservice.repository;

import com.bank.cardservice.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByClientId(Long userId);
}
