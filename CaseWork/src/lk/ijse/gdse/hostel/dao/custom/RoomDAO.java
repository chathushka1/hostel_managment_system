package lk.ijse.gdse.hostel.dao.custom;

import lk.ijse.gdse.hostel.dao.CrudDAO;
import lk.ijse.gdse.hostel.dao.SuperDAO;
import lk.ijse.gdse.hostel.entity.Room;
import org.hibernate.Session;

import java.util.List;

public interface RoomDAO extends SuperDAO, CrudDAO<Room,String> {
    void setSession(Session session);
    List<String> getId();
}
