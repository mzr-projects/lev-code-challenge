package com.challenge.codechallenge.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Player extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String email;

    private BigDecimal credit;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @UpdateTimestamp
    private Date modifiedDate;

    @CreationTimestamp
    private Date creationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "player",cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<PlayerTransaction> playerTransactionSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(getEmail(), player.getEmail())
                .append(getFirstName(), player.getFirstName())
                .append(getLastName(), player.getLastName())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(getEmail())
                .append(getFirstName())
                .append(getLastName())
                .toHashCode();
    }

    public void setPlayerTransaction(PlayerTransaction playerTransaction) {
        this.playerTransactionSet.add(playerTransaction);
    }
}
