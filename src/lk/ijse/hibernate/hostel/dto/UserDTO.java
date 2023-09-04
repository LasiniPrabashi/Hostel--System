package lk.ijse.hibernate.hostel.dto;

public class UserDTO {
    private String UserName;
    private String PassWord;

    public UserDTO(String userName, String passWord) {
        UserName = userName;
        PassWord = passWord;
    }

    public UserDTO() {

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

    @Override
    public String toString() {
        return "UserDTO{" +
                "UserName='" + UserName + '\'' +
                ", PassWord='" + PassWord + '\'' +
                '}';
    }
}
