package lk.ijse.hibernate.hostel.bo.custom;

import lk.ijse.hibernate.hostel.bo.SuperBO;
import lk.ijse.hibernate.hostel.dto.UserDTO;


public interface UserBO extends SuperBO {
     boolean saveUser(UserDTO userDTO);
     boolean updateUser(UserDTO dto);
     boolean deleteUser(UserDTO dto);
     UserDTO searchUser(String id);
     String getPassword(String username);

}
