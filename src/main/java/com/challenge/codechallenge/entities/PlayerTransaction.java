package com.challenge.codechallenge.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class PlayerTransaction extends BaseEntity {

    @Column(nullable = false, unique = true)
    private Long transactionId;

    private BigDecimal transactionAmount;

    private TransactionType transactionType;

    @CreationTimestamp
    private Date createdData;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    public PlayerTransaction(Player player) {
        this.player = player;
    }

    public PlayerTransaction() {

    }
}
