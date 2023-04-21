package lk.ijse.gdse.hostel.bo.custom.impl;

import lk.ijse.gdse.hostel.bo.custom.ReservationBO;
import lk.ijse.gdse.hostel.dao.DAOFactory;
import lk.ijse.gdse.hostel.dao.custom.QueryDAO;
import lk.ijse.gdse.hostel.dao.custom.ReservationDAO;
import lk.ijse.gdse.hostel.dao.custom.RoomDAO;
import lk.ijse.gdse.hostel.dao.custom.StudentDAO;
import lk.ijse.gdse.hostel.model.ReservationDTO;
import lk.ijse.gdse.hostel.model.RoomDTO;
import lk.ijse.gdse.hostel.model.StudentDTO;
import org.hibernate.Session;

import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    ReservationDAO reservationDAO= (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESRVATIONDAO);
    StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENTDAO);
    RoomDAO roomDAO= (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOMDAO);
    QueryDAO queryDAO= (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    private Session session;
    @Override
    public List<ReservationDTO> getAllReservation() throws Exception {
        return null;
    }

    @Override
    public String saveReservation(ReservationDTO reservationDTO) throws Exception {
        return null;
    }

    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteReservation(ReservationDTO reservationDTO) throws Exception {
        return false;
    }

    @Override
    public String generateReservationId() throws Exception {
        return null;
    }

    @Override
    public StudentDTO getStudents(String id) throws Exception {
        return null;
    }

    @Override
    public RoomDTO getRooms(String id) throws Exception {
        return null;
    }

    @Override
    public List<String> getStudentIds() {
        return null;
    }

    @Override
    public List<String> getRoomIds() {
        return null;
    }

    @Override
    public List<StudentDTO> geAllStudents() throws Exception {
        return null;
    }

    @Override
    public List<RoomDTO> getAllRooms() throws Exception {
        return null;
    }

    @Override
    public boolean updateRoomQty(RoomDTO roomDTO) throws Exception {
        return false;
    }

    @Override
    public boolean checkStatusClick(String id, String status) {
        return false;
    }
}
