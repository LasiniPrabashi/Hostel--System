package lk.ijse.hibernate.hostel.dao.custom;

import lk.ijse.hibernate.hostel.dao.CrudDAO;
import lk.ijse.hibernate.hostel.entity.Student;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {
        List<String> getStIds();
}
