package lk.ijse.hibernate.hostel.dao.custom;

import lk.ijse.hibernate.hostel.dao.CrudDAO;
import lk.ijse.hibernate.hostel.entity.Student;
import org.hibernate.Session;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {
    List<String> getStIds();

}
