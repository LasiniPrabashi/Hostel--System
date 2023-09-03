package lk.ijse.hibernate.hostel.bo.custom;

import lk.ijse.hibernate.hostel.bo.SuperBO;
import lk.ijse.hibernate.hostel.dto.RoomDTO;

import java.util.List;

public interface RoomBO extends SuperBO {
    List<RoomDTO> loadAll();
    boolean saveRoom(RoomDTO dto);
    boolean updateRoom(RoomDTO dto);
    boolean deleteRoom(RoomDTO dto);
    RoomDTO getRoom(String id) throws Exception;
}
