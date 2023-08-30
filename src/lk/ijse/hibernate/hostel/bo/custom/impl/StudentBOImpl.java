package lk.ijse.hibernate.hostel.bo.custom.impl;

import lk.ijse.hibernate.hostel.bo.SuperBo;
import lk.ijse.hibernate.hostel.bo.custom.StudentBO;
import lk.ijse.hibernate.hostel.dao.DAOFactory;
import lk.ijse.hibernate.hostel.dao.custom.StudentDAO;
import lk.ijse.hibernate.hostel.dto.StudentDTO;
import lk.ijse.hibernate.hostel.entity.Student;
import lk.ijse.hibernate.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    private Session session;
    StudentDAO studentDAO=(StudentDAO) DAOFactory.getDaoFactory ().getDAO (DAOFactory.DAOTypes.STUDENT);

    @Override
    public List<StudentDTO> loadAll() {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();
        studentDAO.setSession (session);

        List<Student> stList=studentDAO.loadAll ();
        List<StudentDTO> list=new ArrayList<> ();
        for (Student student:stList) {
            list.add(
                    new StudentDTO(
                            student.getStudentId(),
                            student.getName(),
                            student.getAddress(),
                            student.getContactNo(),
                            student.getDob(),
                            student.getGender()
                    )
            );
        }

        return list;
    }

    @Override
    public boolean saveStudent(StudentDTO dto) {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            studentDAO.setSession (session);
            String id=studentDAO.save (new Student (
                    dto.getStId (),
                    dto.getStName (),
                    dto.getAddress (),
                    dto.getContact (),
                    dto.getDob (),
                    dto.getGender ()));
            transaction.commit ();
            session.close ();
            if (id!=null){
                return true;
            }
        }catch (Exception e){
            transaction.rollback ();
        }
        return false;
    }

    @Override
    public boolean updateStudent(StudentDTO dto) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try {
            studentDAO.setSession (session);
            studentDAO.update (new Student (
                    dto.getStId (),
                    dto.getStName (),
                    dto.getAddress (),
                    dto.getContact (),
                    dto.getDob (),
                    dto.getGender ()));

            transaction.commit ();
            session.close ();
            return true;
        }catch (Exception e){
            transaction.rollback ();;
        }
        return false;
    }


    @Override
    public boolean deleteStudent(StudentDTO dto) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            studentDAO.setSession (session);
            studentDAO.delete (new Student (
                    dto.getStId (),
                    dto.getStName (),
                    dto.getAddress (),
                    dto.getContact (),
                    dto.getDob (),
                    dto.getGender ()
            ));
            transaction.commit ();
            session.close ();
            return true;
        }catch (Exception e){
            transaction.rollback ();
        }
        return false;
    }

     @Override
    public StudentDTO getStudent(String id) throws Exception {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        studentDAO.setSession (session);
        Student st=studentDAO.getObject (id);
        session.close ();
        return new StudentDTO (
                st.getStudentId (),
                st.getName (),
                st.getAddress (),
                st.getContactNo (),
                st.getDob (),
                st.getGender ()
        );
    }

    @Override
    public int getStudnetCount() {
        return 0;
    }


}
