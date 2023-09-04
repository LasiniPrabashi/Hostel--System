package lk.ijse.hibernate.hostel.bo.custom;

import lk.ijse.hibernate.hostel.bo.SuperBO;
import lk.ijse.hibernate.hostel.dto.UserDTO;

public interface UserBO extends SuperBO {
    public String save(UserDTO dto);
    public UserDTO get(String userName) throws Exception;
    public boolean update(UserDTO dto);
}
