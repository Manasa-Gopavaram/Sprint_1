package entity;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Income")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int income_id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "source", length = 255)
    private String source;

    @Temporal(TemporalType.DATE)
    @Column(name = "income_date", nullable = false)
    private Date date;

    @Column(name = "description", length = 255)
    private String description;

    // Constructors
    public Income() {
        super();
    }

    public Income(Account account, BigDecimal amount, String source, Date date, String description) {
        this.account = account;
        this.amount = amount;
        this.source = source;
        this.date = date;
        this.description = description;
    }

    // Getters and Setters
    public int getIncome_id() {
        return income_id;
    }

    public void setIncome_id(int income_id) {
        this.income_id = income_id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Income [income_id=" + income_id +
                ", account_id=" + (account != null ? account.getAccount_id() : null) +
                ", amount=" + amount + ", source=" + source +
                ", date=" + date + ", description=" + description + "]";
    }
}
