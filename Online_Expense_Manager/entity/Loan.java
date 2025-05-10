package entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loan_id;

    @ManyToOne
    @JoinColumn(name = "account_id") // changed from user_id to account_id
    private Account account;  // Changed from User to Account

    @Column(name = "LenderName", length = 100)
    private String lender_name;

    @Column(name = "LoanAmount", precision = 10, scale = 2)
    private BigDecimal loan_amount;

    @Temporal(TemporalType.DATE)
    @Column(name = "StartDate")
    private Date start_date;

    @Temporal(TemporalType.DATE)
    @Column(name = "EndDate")
    private Date end_date;

    // Constructors
    public Loan() {
        super();
    }

    public Loan(int loan_id, Account account, String lender_name, BigDecimal loan_amount, Date start_date, Date end_date) {
        super();
        this.loan_id = loan_id;
        this.account = account;
        this.lender_name = lender_name;
        this.loan_amount = loan_amount;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public Loan(Account account, String lender_name, BigDecimal loan_amount, Date start_date, Date end_date) {
        super();
        this.account = account;
        this.lender_name = lender_name;
        this.loan_amount = loan_amount;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    // Getters and Setters
    public int getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(int loan_id) {
        this.loan_id = loan_id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getLender_name() {
        return lender_name;
    }

    public void setLender_name(String lender_name) {
        this.lender_name = lender_name;
    }

    public BigDecimal getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(BigDecimal loan_amount) {
        this.loan_amount = loan_amount;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "Loan [loan_id=" + loan_id + ", account_id=" + (account != null ? account.getAccount_id() : null) +
                ", lender_name=" + lender_name + ", loan_amount=" + loan_amount + 
                ", start_date=" + start_date + ", end_date=" + end_date + "]";
    }
}
