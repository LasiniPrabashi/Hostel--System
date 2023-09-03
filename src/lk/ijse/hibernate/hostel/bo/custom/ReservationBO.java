package lk.ijse.hibernate.hostel.bo.custom;

import lk.ijse.hibernate.hostel.bo.SuperBO;
import lk.ijse.hibernate.hostel.dto.ReservationDTO;
import lk.ijse.hibernate.hostel.dto.RoomDTO;
import lk.ijse.hibernate.hostel.dto.StudentDTO;

import java.util.List;

public interface ReservationBO extends SuperBO {
    List<String> getStudentIds();
    List<String> getRoomIds();
    StudentDTO getStudent(String id);
    RoomDTO getRoom(String id);
    ReservationDTO getRes(String resID);
    boolean updateRoom(RoomDTO dto);
    List<ReservationDTO> loadAllRes();
    boolean saveReservation(ReservationDTO dto);
    boolean updateReservation(ReservationDTO dto);
    boolean deleteReservation(ReservationDTO dto);
    List<ReservationDTO> loadAll();
}
