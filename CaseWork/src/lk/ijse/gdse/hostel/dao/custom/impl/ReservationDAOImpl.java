package lk.ijse.gdse.hostel.dao.custom.impl;

import lk.ijse.gdse.hostel.dao.custom.ReservationDAO;
import lk.ijse.gdse.hostel.entity.Reservation;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public List<Reservation> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String save(Reservation dto) {
        return null;
    }

    @Override
    public void update(Reservation dto) {

    }

    @Override
    public void delete(Reservation dto) {

    }

    @Override
    public String genarateNewId() {
        return null;
    }

    @Override
    public Reservation getObject(String s) {
        return null;
    }

    @Override
    public void setSession(Session session) {

    }

    @Override
    public boolean changeCheckBOXValue(String id, String status) {
        return false;
    }
}
