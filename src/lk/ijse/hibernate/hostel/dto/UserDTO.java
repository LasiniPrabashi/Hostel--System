package lk.ijse.hibernate.hostel.dto;

public class UserDTO {
    private String userName;
    private String password;
    private String name;
    private String contactNo;

    public UserDTO(String userName, String password, String name, String contactNo) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.contactNo = contactNo;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }


}
