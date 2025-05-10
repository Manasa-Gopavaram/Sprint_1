package entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;



@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;  // Changed from String to int

    @Column(name = "Name", length = 100)
    private String name;

    @Column(name = "Email", length = 100, unique = true)
    private String email;

    @Column(name = "Password", length = 255)
    private String password;

    // Constructors
    public User() {
        super();
    }

    // Constructor without user_id since it's auto-generated
    public User(String name, String email, String password) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [user_id=" + user_id + ", name=" + name + ", email=" + email + "]";
    }
}
