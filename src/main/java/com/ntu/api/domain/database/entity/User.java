package com.ntu.api.domain.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "ntu")
public class User {

    @Id
    @Column(name = "user_id")
    @SequenceGenerator(name = "userId", sequenceName = "seq_user_id", initialValue = 10001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userId")
    private Long userId;

    @Column(name="login")
    private String login;

    @Column(name = "password")
    private String password;

    public User(){}

    public User(String login, String password) {
        this.login = login;
        this.password = password;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
