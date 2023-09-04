package lk.ijse.hibernate.hostel.bo.custom.impl;

import lk.ijse.hibernate.hostel.bo.custom.UserBO;
import lk.ijse.hibernate.hostel.dao.DAOFactory;
import lk.ijse.hibernate.hostel.dao.custom.UserDAO;
import lk.ijse.hibernate.hostel.dto.StudentDTO;
import lk.ijse.hibernate.hostel.dto.UserDTO;
import lk.ijse.hibernate.hostel.entity.Student;
import lk.ijse.hibernate.hostel.entity.User;
import lk.ijse.hibernate.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserBOImpl implements UserBO {
    private Session session;
    private UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public String save(UserDTO dto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            userDAO.setSession(session);
            String save = userDAO.save(new User(
                    dto.getUserName(),
                    dto.getPassWord()
            ));
            transaction.commit ();
            session.close ();

            return save;

        }catch (Exception e){
            transaction.rollback();
            session.close();

            return null;
        }
    }

    @Override
    public UserDTO get(String userName) throws Exception {

        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        userDAO.setSession (session);
        User user=userDAO.getObject (userName);
        session.close ();
        return new UserDTO (
              user.getUserName(),
              user.getPassWord()
        );
    }

    @Override
    public boolean update(UserDTO dto) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try {
            userDAO.setSession (session);
            userDAO.update (new User (
                    dto.getUserName(),
                    dto.getPassWord()

                    ));

            transaction.commit ();
            session.close ();
            return true;
        }catch (Exception e){
            transaction.rollback ();;
        }
        return false;
    }

}
