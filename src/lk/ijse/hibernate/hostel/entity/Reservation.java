package lk.ijse.hibernate.hostel.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @Column(name = "resId",length = 25)
    String resId;
    @Column(name = "date")
    Date date;
    @JoinColumn(name = "studentId", referencedColumnName = "studentId", nullable = false)
    @ManyToOne
    Student student;
    @JoinColumn(name = "roomTypeId", referencedColumnName = "roomTypeId", nullable = false)
    @ManyToOne
    Room room;
    @Column(name = "status")
    String status;

    public Reservation(String resId, Date date, Student student, Room room, String status) {
        this.resId = resId;
        this.date = date;
        this.student = student;
        this.room = room;
        this.status = status;
    }

    public Reservation() {

    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
