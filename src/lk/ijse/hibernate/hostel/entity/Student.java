package lk.ijse.hibernate.hostel.entity;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.time.LocalDate;




@Entity
public class Student {
    @Id
    @Column(name = "student_id", columnDefinition = "VARCHAR(64)")
    private String studentId;
    private String name;
    private String address;
    private String contactNo;
    private LocalDate dob;
    private String gender;


}
