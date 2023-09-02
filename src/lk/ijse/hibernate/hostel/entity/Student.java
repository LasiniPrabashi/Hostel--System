package lk.ijse.hibernate.hostel.entity;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id",length = 25)
    private String studentId;
    @Column(name = "stName")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "contactNo")
    private String contactNo;
    @Column(name = "dob")
    private String dob;
    @Column(name = "gender")
    private String gender;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "student")
    private List<Reservation> reservationList;


    public Student(String studentId, String name, String address, String contactNo, String dob, String gender) {
        this.studentId = studentId;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.dob = dob;
        this.gender = gender;
    }

    public Student() {

    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
