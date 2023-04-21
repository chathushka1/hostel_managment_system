package lk.ijse.gdse.hostel.dao.custom;

import lk.ijse.gdse.hostel.dao.CrudDAO;
import lk.ijse.gdse.hostel.dao.SuperDAO;
import lk.ijse.gdse.hostel.entity.Reservation;
import org.hibernate.Session;

public interface ReservationDAO extends SuperDAO, CrudDAO<Reservation,String> {
    void setSession(Session session);
    boolean changeCheckBOXValue(String id,String status);
}
