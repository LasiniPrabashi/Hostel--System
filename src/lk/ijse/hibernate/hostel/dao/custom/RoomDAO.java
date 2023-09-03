package lk.ijse.hibernate.hostel.dao.custom;

import lk.ijse.hibernate.hostel.dao.CrudDAO;
import lk.ijse.hibernate.hostel.entity.Room;

import java.util.List;

public interface RoomDAO  extends CrudDAO<Room> {
    List<String> roomIds();
}
