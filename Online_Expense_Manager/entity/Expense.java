package entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expense_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "Amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "Category", length = 50)
    private String category;

    @Temporal(TemporalType.DATE)
    @Column(name = "ExpenseDate")
    private Date date;

    @Column(name = "Description", length = 255)
    private String description;

    // Constructors
    public Expense() {
        super();
    }

    public Expense(int expense_id, User user, Account account, BigDecimal amount, String category, Date date, String description) {
        super();
        this.expense_id = expense_id;
        this.user = user;
        this.account = account;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    public Expense(User user, Account account, BigDecimal amount, String category, Date date, String description) {
        super();
        this.user = user;
        this.account = account;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    // Getters and Setters
    public int getExpense_id() {
        return expense_id;
    }

    public void setExpense_id(int expense_id) {
        this.expense_id = expense_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
        return "Expense [expense_id=" + expense_id +
               ", user_id=" + (user != null ? user.getUser_id() : null) +
               ", account_id=" + (account != null ? account.getAccount_id() : null) +
               ", amount=" + amount +
               ", category=" + category +
               ", date=" + date +
               ", description=" + description + "]";
    }
}
