package lk.ijse.hibernate.hostel.dao.custom;

import lk.ijse.hibernate.hostel.dao.CrudDAO;
import lk.ijse.hibernate.hostel.entity.User;

public interface UserDAO extends CrudDAO<User> {

     String getPassword(String username);
     User search(String id);
}
