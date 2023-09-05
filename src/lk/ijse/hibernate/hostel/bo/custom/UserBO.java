package lk.ijse.hibernate.hostel.bo.custom;

import lk.ijse.hibernate.hostel.bo.SuperBO;
import lk.ijse.hibernate.hostel.dto.UserDTO;

import java.util.List;

public interface UserBO extends SuperBO {
     boolean saveUser(UserDTO dto);
     UserDTO getUser(String id) throws Exception;
     boolean updateUser(UserDTO dto);
     List<UserDTO> loadAll();
}
