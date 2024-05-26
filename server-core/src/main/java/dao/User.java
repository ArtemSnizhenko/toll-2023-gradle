package dao;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;


@Entity
@Table(name="Users")
public class User extends jdev.dto.User {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "ID")
    int id;

    @Column(name = "USERNAME")
    String userName;

    @Column(name = "PASSWORD")
    String password;

    @Column(name = "ROLES")
    String roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
