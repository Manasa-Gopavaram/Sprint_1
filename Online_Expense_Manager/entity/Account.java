package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int account_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // Reference to User entity (Many Accounts belong to One User)

    @Column(name = "AccountType", length = 50)
    private String account_type;

    @Column(name = "Balance", precision = 10, scale = 2)
    private BigDecimal balance; // Change to BigDecimal for financial precision

    // Default constructor
    public Account() {
        super();
    }

    // Constructor with account details (excluding account_id as it is auto-generated)
    public Account(User user, String account_type, BigDecimal balance) {
        super();
        this.user = user;
        this.account_type = account_type;
        this.balance = balance;
    }

    // Constructor including account_id (if needed for updates or direct creation)
    public Account(int account_id, User user, String account_type, BigDecimal balance) {
        super();
        this.account_id = account_id;
        this.user = user;
        this.account_type = account_type;
        this.balance = balance;
    }

    // Getters and Setters
    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account [account_id=" + account_id + ", user_id=" + (user != null ? user.getUser_id() : null) +
                ", account_type=" + account_type + ", balance=" + balance + "]";
    }
}
