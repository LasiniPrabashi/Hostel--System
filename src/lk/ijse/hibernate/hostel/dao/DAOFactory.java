package lk.ijse.hibernate.hostel.dao;

import lk.ijse.hibernate.hostel.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;

    public DAOFactory() {
    }

    public static DAOFactory getDaoFactory(){
        if (daoFactory==null){
            daoFactory=new DAOFactory ();
        }
        return daoFactory;
    }

    public enum DAOTypes{
        STUDENT,ROOM,USER,RESERVATION
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case STUDENT:
                return new StudentDAOImpl();
//            case ROOM:
//                return new RoomDAOImpl ();
//            case RESERVATION:
//                return new RevervationDAOImpl ();
//            case USER:
//                return new UserDAOImpl ();
            default:
                return null;
        }
    }
}
