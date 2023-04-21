package lk.ijse.gdse.hostel.bo.custom.impl;

import lk.ijse.gdse.hostel.bo.custom.RoomBO;
import lk.ijse.gdse.hostel.dao.DAOFactory;
import lk.ijse.gdse.hostel.dao.custom.RoomDAO;
import lk.ijse.gdse.hostel.entity.Room;
import lk.ijse.gdse.hostel.model.RoomDTO;
import lk.ijse.gdse.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOMDAO);
    private Session session;

    @Override
    public List<RoomDTO> getAllRooms() throws SQLException, ClassNotFoundException {
        session = SessionFactoryConfig.getInstance().getSession();
        roomDAO.setSession(session);
        List<Room> allRooms = roomDAO.getAll();
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for(Room room : allRooms) {
            roomDTOS.add(new RoomDTO(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty()));
        }
        return roomDTOS;
    }

    @Override
    public String saveRooms(RoomDTO roomDTO) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            roomDAO.setSession(session);
            String id = (String) roomDAO.save(new Room(roomDTO.getRoom_type_id(),roomDTO.getType(),roomDTO.getKey_money(),roomDTO.getQty()));
            transaction.commit();
            session.close();
            return id;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return "R00-001";
        }
    }

    @Override
    public boolean updateRooms(RoomDTO roomDTO) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            roomDAO.setSession(session);
            roomDAO.update(new Room(roomDTO.getRoom_type_id(),roomDTO.getType(),roomDTO.getKey_money(),roomDTO.getQty()));
            transaction.commit();
            session.close();
            return  true;
        }catch(Exception e){
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean deleteRooms(RoomDTO roomDTO) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            roomDAO.setSession(session);
            roomDAO.delete(new Room(roomDTO.getRoom_type_id(),roomDTO.getType(),roomDTO.getKey_money(),roomDTO.getQty()));
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public String genarateRooms() {
        session = SessionFactoryConfig.getInstance().getSession();
        roomDAO.setSession(session);
        return roomDAO.genarateNewId();
    }
}
