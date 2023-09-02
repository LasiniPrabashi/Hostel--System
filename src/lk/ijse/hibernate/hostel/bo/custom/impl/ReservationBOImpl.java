package lk.ijse.hibernate.hostel.bo.custom.impl;

import lk.ijse.hibernate.hostel.bo.custom.ReservationBO;
import lk.ijse.hibernate.hostel.dao.DAOFactory;
import lk.ijse.hibernate.hostel.dao.custom.ReservationDAO;
import lk.ijse.hibernate.hostel.dao.custom.RoomDAO;
import lk.ijse.hibernate.hostel.dao.custom.StudentDAO;
import lk.ijse.hibernate.hostel.dto.ReservationDTO;
import lk.ijse.hibernate.hostel.dto.RoomDTO;
import lk.ijse.hibernate.hostel.dto.StudentDTO;
import lk.ijse.hibernate.hostel.entity.Reservation;
import lk.ijse.hibernate.hostel.entity.Room;
import lk.ijse.hibernate.hostel.entity.Student;
import lk.ijse.hibernate.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {

    private Session session;
    StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    RoomDAO roomDAO=(RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    ReservationDAO reservationDAO=(ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);

    public List<String> getStudentIds(){
        try{
            session= SessionFactoryConfig.getInstance().getSession();
            studentDAO.setSession((session));
            return studentDAO.getStIds();

        }catch (Exception e){
            session.close();
            return null;
        }
    }

    public List<String> getRoomIds(){
        try {
            session=SessionFactoryConfig.getInstance().getSession();
            roomDAO.setSession(session);
            return roomDAO.roomIds();
        }catch (Exception e){
            session.close();
            return null;
        }
    }

    public StudentDTO getStudent(String id) {
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        studentDAO.setSession(session);
        try {
            Student student = studentDAO.getObject(id);
            session.close();
            return new StudentDTO(
                    student.getStudentId(),
                    student.getName(),
                    student.getAddress(),
                    student.getContactNo(),
                    student.getDob(),
                    student.getGender()
            );

        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    public RoomDTO getRoom(String id) {
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        roomDAO.setSession(session);
        try {
            Room room=roomDAO.getObject(id);
            session.close();
            return new RoomDTO(
                    room.getRoomTypeId(),
                    room.getType(),
                    room.getKeyMoney(),
                    room.getQty()
            );

        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    public ReservationDTO getRes(String resID){
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        reservationDAO.setSession(session);
        try {
            Reservation reservation = reservationDAO.getObject(resID);
            session.close();
            return new ReservationDTO(
                    reservation.getResId(),
                    reservation.getDate(),
                    new StudentDTO(
                            reservation.getStudent().getStudentId(),
                            reservation.getStudent().getName(),
                            reservation.getStudent().getAddress(),
                            reservation.getStudent().getContactNo(),
                            reservation.getStudent().getDob(),
                            reservation.getStudent().getGender()
                    ),
                    new RoomDTO(
                            reservation.getRoom().getRoomTypeId(),
                            reservation.getRoom().getType(),
                            reservation.getRoom().getKeyMoney(),
                            reservation.getRoom().getQty()
                    ),
                    reservation.getStatus()
            );

        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    public boolean updateRoom(RoomDTO dto){
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        try {
            roomDAO.setSession(session);
            roomDAO.update(new Room(
                    dto.getRoomId(),
                    dto.getType(),
                    dto.getKeyMoney(),
                    dto.getQty()
            ));

            transaction.commit();
            session.close();
            return true;

        }catch (Exception e){
            transaction.rollback();
        }
        return false;
    }


    public List<ReservationDTO> loadAllRes(){
        return null;
    }

    public boolean saveReservation(ReservationDTO dto){
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        try {
            reservationDAO.setSession(session);
            reservationDAO.save(new Reservation(
                    dto.getResID(),
                    dto.getDate(),
                    new Student(
                            dto.getStudentDTO().getStId(),
                            dto.getStudentDTO().getStName(),
                            dto.getStudentDTO().getAddress(),
                            dto.getStudentDTO().getContact(),
                            dto.getStudentDTO().getDob(),
                            dto.getStudentDTO().getGender()
                    ),
                    new Room(
                            dto.getRoomDTO().getRoomId(),
                            dto.getRoomDTO().getType(),
                            dto.getRoomDTO().getKeyMoney(),
                            dto.getRoomDTO().getQty()
                    ),
                    dto.getStatus()
                    ));

            transaction.commit();
            session.close();
            return true;

        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateReservation(ReservationDTO dto){
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        try {
            reservationDAO.setSession(session);
            reservationDAO.update(new Reservation(
                    dto.getResID(),
                    dto.getDate(),
                    new Student(
                            dto.getStudentDTO().getStId(),
                            dto.getStudentDTO().getStName(),
                            dto.getStudentDTO().getAddress(),
                            dto.getStudentDTO().getContact(),
                            dto.getStudentDTO().getDob(),
                            dto.getStudentDTO().getGender()
                    ),
                    new Room(
                            dto.getRoomDTO().getRoomId(),
                            dto.getRoomDTO().getType(),
                            dto.getRoomDTO().getKeyMoney(),
                            dto.getRoomDTO().getQty()
                    ),
                    dto.getStatus()
            ));
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteReservation(ReservationDTO dto){
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            reservationDAO.setSession(session);
            reservationDAO.delete(new Reservation(
                    dto.getResID(),
                    dto.getDate(),
                    new Student(
                            dto.getStudentDTO().getStId(),
                            dto.getStudentDTO().getStName(),
                            dto.getStudentDTO().getAddress(),
                            dto.getStudentDTO().getContact(),
                            dto.getStudentDTO().getDob(),
                            dto.getStudentDTO().getGender()
                    ),
                    new Room(
                           dto.getRoomDTO().getRoomId(),
                           dto.getRoomDTO().getType(),
                           dto.getRoomDTO().getKeyMoney(),
                           dto.getRoomDTO().getQty()
                    ),
                    dto.getStatus()
            ));
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            session.close();
            transaction.rollback();
        }
        return false;
    }

    public List<ReservationDTO> loadAll() {
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        reservationDAO.setSession(session);
        List<Reservation>list= reservationDAO.loadAll();
        List<ReservationDTO>resList= new ArrayList<>();

        for (Reservation res:list){
            resList.add(new ReservationDTO(
                    res.getResId(),
                    res.getDate(),
                    new StudentDTO(
                            res.getStudent().getStudentId(),
                            res.getStudent().getName(),
                            res.getStudent().getAddress(),
                            res.getStudent().getContactNo(),
                            res.getStudent().getDob(),
                            res.getStudent().getGender()
                    ),
                    new RoomDTO(
                            res.getRoom().getRoomTypeId(),
                            res.getRoom().getType(),
                            res.getRoom().getKeyMoney(),
                            res.getRoom().getQty()
                    ),
                    res.getStatus()
            ));
        }
        System.out.println("Check2");
        return resList;
    }
}

