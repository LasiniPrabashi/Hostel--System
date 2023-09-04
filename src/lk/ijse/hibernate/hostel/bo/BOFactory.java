package lk.ijse.hibernate.hostel.bo;

import lk.ijse.hibernate.hostel.bo.custom.impl.ReservationBOImpl;
import lk.ijse.hibernate.hostel.bo.custom.impl.RoomBOImpl;
import lk.ijse.hibernate.hostel.bo.custom.impl.StudentBOImpl;
import lk.ijse.hibernate.hostel.bo.custom.impl.UserBOImpl;


public class BOFactory {
    public static BOFactory boFactory;
    public BOFactory() {
    }

    public BOFactory  getBoFactory(){
        if (boFactory==null){
            boFactory=new BOFactory ();
        }
        return boFactory;
    }

    public enum BOTypes{
        STUDENT,ROOM,USER,RESERVATION
    }

    public static SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case STUDENT:
                return new StudentBOImpl ();
            case ROOM:
                return new RoomBOImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
