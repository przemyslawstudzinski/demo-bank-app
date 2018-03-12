package org.banana.bank.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "username", nullable = false, unique = true)
    @Getter
    @Setter
    private String username;

    @Column(name = "balance", nullable = false)
    @Getter
    @Setter
    private BigDecimal balance;

    @OneToMany(mappedBy = "user")
    @OrderBy("createdDate ASC")
    @Getter
    @Setter
    private List<Transaction> transactions;

    @Column(name = "seed_of_token")
    @Getter
    @Setter
    private String seedOfToken;

    /**
     * Allows add new transaction to collection.
     *
     * @param newTransaction New Transaction to add
     */
    public void addTransaction(Transaction newTransaction) {
        if (null == newTransaction) {
            return;
        }
        if (null == transactions) {
            transactions = new ArrayList<>();
        }

        List<Transaction> updatedList = new ArrayList<>();
        updatedList.addAll(this.transactions);
        updatedList.add(newTransaction);

        this.transactions.clear();
        this.transactions.addAll(updatedList);
    }
}
