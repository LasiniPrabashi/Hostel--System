package lk.ijse.hibernate.hostel.dao.custom.impl;

import lk.ijse.hibernate.hostel.dao.custom.ReservationDAO;
import lk.ijse.hibernate.hostel.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    private Session session;

    @Override
    public List<Reservation> loadAll() {
        String sqlQuery="FROM Reservation";
        Query query = session.createQuery(sqlQuery);
        List list =query.list ();
        session.close();
        return list;
    }

    @Override
    public String save(Reservation reservation) {
        return (String) session.save(reservation);
    }

    @Override
    public void update(Reservation reservation) {
        session.update(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        session.delete(reservation);
    }

    @Override
    public Reservation getObject(String id) throws Exception {
        return session.get(Reservation.class,id);
    }

    @Override
    public void setSession(Session session) {

    }
}
