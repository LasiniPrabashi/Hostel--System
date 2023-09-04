package lk.ijse.hibernate.hostel.dao.custom.impl;

import lk.ijse.hibernate.hostel.dao.custom.UserDAO;
import lk.ijse.hibernate.hostel.entity.User;
import org.hibernate.Session;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    private Session session;

    @Override
    public List<User> loadAll() {
        return null;
    }

    @Override
    public String save(User user) {
        return (String) session.save(user);
    }

    @Override
    public void update(User user) {
        session.update(user);
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User getObject(String userName) throws Exception {
        return session.get(User.class, userName);
    }

    @Override
    public void setSession(Session session) {
         this.session=session;
    }
}
