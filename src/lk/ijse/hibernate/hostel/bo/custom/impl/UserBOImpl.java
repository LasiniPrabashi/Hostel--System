package lk.ijse.hibernate.hostel.bo.custom.impl;

import lk.ijse.hibernate.hostel.bo.custom.UserBO;
import lk.ijse.hibernate.hostel.dao.DAOFactory;
import lk.ijse.hibernate.hostel.dao.custom.UserDAO;
import lk.ijse.hibernate.hostel.dto.UserDTO;
import lk.ijse.hibernate.hostel.entity.User;
import lk.ijse.hibernate.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserBOImpl implements UserBO {
    private Session session;
    UserDAO userDAO=(UserDAO) DAOFactory.getDaoFactory ().getDAO (DAOFactory.DAOTypes.USER);

    @Override
    public boolean saveUser(UserDTO dto) {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            userDAO.setSession (session);
            String id=userDAO.save (new User (
                    dto.getUserName (),
                    dto.getPassword (),
                    dto.getName (),
                    dto.getContactNo()
            ));
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
    public boolean updateUser(UserDTO dto) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try {
            userDAO.setSession (session);
            userDAO.update (new User (
                    dto.getUserName (),
                    dto.getPassword (),
                    dto.getName (),
                    dto.getContactNo()
            ));

            transaction.commit ();
            session.close ();
            return true;
        }catch (Exception e){
            transaction.rollback ();;
        }
        return false;
    }

    @Override
    public boolean deleteUser(UserDTO dto) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try {
            userDAO.setSession (session);
            userDAO.delete (new User (
                    dto.getUserName (),
                    dto.getPassword (),
                    dto.getName (),
                    dto.getContactNo()
            ));

            transaction.commit ();
            session.close ();
            return true;
        }catch (Exception e){
            transaction.rollback ();;
        }
        return false;
    }

    @Override
    public UserDTO searchUser(String id) {
        User search = userDAO.search(id);

        return new UserDTO(search.getUserName(),
                search.getPassword(),
                search.getName(),
                search.getContactNo());
    }

    @Override
    public String getPassword(String username) {
        return userDAO.getPassword(username);
    }
}
