package lk.ijse.hibernate.hostel.bo.custom.impl;

import lk.ijse.hibernate.hostel.bo.custom.RoomBO;
import lk.ijse.hibernate.hostel.dao.DAOFactory;
import lk.ijse.hibernate.hostel.dao.custom.RoomDAO;
import lk.ijse.hibernate.hostel.dto.RoomDTO;
import lk.ijse.hibernate.hostel.entity.Room;
import lk.ijse.hibernate.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    private Session session;
    RoomDAO roomDAO=(RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public List<RoomDTO> loadAll(){
        session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();

        roomDAO.setSession(session);
        List<Room>list= roomDAO.loadAll();
        List<RoomDTO>roomList= new ArrayList<>();

        for (Room room:list){
            roomList.add(new RoomDTO(
                    room.getRoomTypeId(),
                    room.getType(),
                    room.getKeyMoney(),
                    room.getQty()
            ));
        }
        return roomList;

    }

    @Override
    public boolean saveRoom(RoomDTO dto){
        session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();

        try {
            roomDAO.setSession(session);
            roomDAO.save(new Room(
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

    @Override
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

    @Override
    public boolean deleteRoom(RoomDTO dto){
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        try {
            roomDAO.setSession(session);
            roomDAO.delete(new Room(
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

    @Override
    public RoomDTO getRoom(String id) throws Exception {
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        roomDAO.setSession(session);
        Room room=roomDAO.getObject(id);
        session.close();
        return new RoomDTO(
                room.getRoomTypeId(),
                room.getType(),
                room.getKeyMoney(),
                room.getQty()
        );
    }
}
