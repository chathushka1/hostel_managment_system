package lk.ijse.gdse.hostel.dao.custom.impl;

import lk.ijse.gdse.hostel.dao.custom.ReservationDAO;
import lk.ijse.gdse.hostel.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    private  Session session;
    @Override
    public List<Reservation> getAll() throws SQLException, ClassNotFoundException {
        String sql="FROM Reservation";
        Query query=session.createQuery(sql);
        List list= query.list();
        session.close();
        return list;
    }

    @Override
    public String save(Reservation dto) {
        return (String) session.save(dto);
    }

    @Override
    public void update(Reservation dto) {
        session.update(dto);
    }

    @Override
    public void delete(Reservation dto) {
        session.update(dto);
    }

    @Override
    public String genarateNewId() {
        String sql="FROM Reservation ORDER BY id DESC";
        Reservation reservation= (Reservation) session.createQuery(sql).setMaxResults(1).uniqueResult();
        session.close();
        if (reservation!=null){
            String lastId=reservation.getRes_id();
            int newCustomerId=Integer.parseInt(lastId.replace("RES-",""))+1;
            return String.format("RES-%03d",newCustomerId);
        }
        return "RES-001";
    }

    @Override
    public Reservation getObject(String id) {
        return session.get(Reservation.class,id);
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public boolean changeCheckBOXValue(String id, String status) {
        String hql="update Reservation r set r.status=:sts Where r.res_id=:rid";
        Query query=session.createQuery(hql);
        query.setParameter("sts",status);
        query.setParameter("rid",id);
        int value= query.executeUpdate();
        return value>=0;
    }
}
