package com.techinc.coffeehouse.entity;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
// user is the reserved keyword in H2 DB, so it is needed to escape it with ``
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int userId;
    @Column(name = "LOGIN", unique = true, nullable = false, length = 15)
    private String login;
    @Column(name = "PASSWORD", nullable = false, length = 20)
    private String password;
    @Column(name = "FIRST_NAME", nullable = false, length = 30)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false, length = 30)
    private String lastName;
    @Column(name = "EMAIL", unique = true, nullable = false, length = 50)
    private String email;
    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    public User() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) {
            return true;
        }

        if (otherObj == null || getClass() != otherObj.getClass()) {
            return false;
        }

        User other = (User) otherObj;
        return userId == other.getUserId() &&
                Objects.equals(login, other.getLogin()) &&
                Objects.equals(password, other.getPassword()) &&
                Objects.equals(firstName, other.getFirstName()) &&
                Objects.equals(lastName, other.getLastName()) &&
                Objects.equals(email, other.getEmail()) &&
                Objects.equals(birthDate, other.getBirthDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, password, firstName, lastName, email, birthDate);
    }

    @Override
    public String toString() {
        return String.format("User: login = %s, email = %s", login, email);
    }
}
