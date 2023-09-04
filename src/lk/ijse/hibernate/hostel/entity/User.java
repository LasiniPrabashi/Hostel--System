package lk.ijse.hibernate.hostel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_name", length = 25)
    private String UserName;
    @Column(name = "password")
    private String PassWord;

    public User(String userName, String passWord) {
        UserName = userName;
        PassWord = passWord;
    }

    public User() {

    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }
}