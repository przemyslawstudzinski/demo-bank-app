package spring.mvc.example.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import spring.mvc.example.domain.enums.OperationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "operation")
public class Transaction extends BaseEntity {

    @Column(name = "type")
    @Getter
    @Setter
    private OperationType type;

    @Column(name = "value")
    @Getter
    @Setter
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter
    @Setter
    private User user;

    @Column(name = "created_date")
    @CreationTimestamp
    @Getter
    @Setter
    private LocalDateTime createdDate;
}
