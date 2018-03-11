package spring.mvc.example.domain;

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

    @Column(name = "balance")
    @Getter
    @Setter
    private BigDecimal balance;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("created_date ASC")
    @Getter
    @Setter
    private List<Transaction> transactions;

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
