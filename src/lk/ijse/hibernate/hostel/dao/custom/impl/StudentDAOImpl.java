package lk.ijse.hibernate.hostel.dao.custom.impl;

import lk.ijse.hibernate.hostel.dao.custom.StudentDAO;
import lk.ijse.hibernate.hostel.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private Session session;

    public List<Student> loadAll() {
        String sqlQuery="FROM Student ";
        Query query = session.createQuery(sqlQuery);
        List list =query.list ();
        session.close();
        return list;
    }

    public String save(Student student) {
        return (String) session.save (student);
    }

    public void update(Student student) {
        session.update (student);
    }

    public void delete(Student student) {
        session.delete (student);
    }

    @Override
    public Student getObject(String id) throws Exception {
        return session.get(Student.class,id);
    }

    public List<String> getStIds() {
        String hql = "SELECT id from Student ";
        Query<String> query=session.createQuery (hql);
        List<String> results = query.list();
        session.close();
        return results;

    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }
}
