package lk.ijse.hibernate.hostel.bo.custom;

import lk.ijse.hibernate.hostel.bo.SuperBO;
import lk.ijse.hibernate.hostel.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    List<StudentDTO> loadAll();
    boolean saveStudent(StudentDTO studentDTO);
    boolean updateStudent(StudentDTO studentDTO);
    boolean deleteStudent(StudentDTO studentDTO);
    StudentDTO getStudent(String id) throws Exception;

    int getStudnetCount();
}
