package lk.ijse.gdse.hostel.dao.custom.impl;

import lk.ijse.gdse.hostel.dao.custom.RoomDAO;
import lk.ijse.gdse.hostel.entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    private Session session;
    @Override
    public List<Room> getAll() throws SQLException, ClassNotFoundException {
        String sql = "FROM Room";
        Query query = session.createQuery(sql);
        List<Room> list = query.list();
        session.close();
        return list;
    }

    @Override
    public String save(Room dto) {
        return (String) session.save(dto);
    }

    @Override
    public void update(Room dto) {
        session.update(dto);
    }

    @Override
    public void delete(Room dto) {
        session.delete(dto);
    }

    @Override
    public String genarateNewId() {
        String sql = "FROM Room ORDER BY id DESC";
        Room room = (Room) session.createQuery(sql).setMaxResults(1).uniqueResult();
        session.close();
        if(room!=null){
            String lastID = room.getRoom_type_id();
            int newStudentID = Integer.parseInt(lastID.replace("R00",""))+1;
            System.out.println(newStudentID);
         return String.format("R00-%03d",newStudentID);

        }
        return "R00-001";
    }

    @Override
    public Room getObject(String s) {
        return session.get(Room.class,s);
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public List<String> getId() {
        String hql = "SELECT id from Room ";
        Query<String> query = session.createQuery(hql);
        List<String> results = query.list();
        session.close();
        return results;
    }
}
