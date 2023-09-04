package lk.ijse.hibernate.hostel.dao.custom.impl;

import lk.ijse.hibernate.hostel.dao.custom.UserDAO;
import lk.ijse.hibernate.hostel.entity.User;
import lk.ijse.hibernate.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private Session session;

    @Override
    public List<User> loadAll() {
        return null;
    }

    @Override
    public String save(User user) {
        return (String) session.save (user);
    }

    @Override
    public void update(User user) {
        session.update (user);
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User getObject(String id) throws Exception {
        return null;
    }

    @Override
    public void setSession(Session session) {
           this.session=session;
    }

    public String getPassword(String username) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("select password from User where userName like: ID").setParameter("ID", username);

        List list = query.list();

        transaction.commit();
        session.close();

        if(list.size() == 0){
            return null;
        }else{
            return (String)list.get(0);
        }

    }

    @Override
    public User search(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, id);

        transaction.commit();
        session.close();

        return user;
    }
}
