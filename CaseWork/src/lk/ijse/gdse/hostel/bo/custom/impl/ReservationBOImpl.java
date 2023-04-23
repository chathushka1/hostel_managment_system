package lk.ijse.gdse.hostel.bo.custom.impl;

import lk.ijse.gdse.hostel.bo.custom.ReservationBO;
import lk.ijse.gdse.hostel.dao.DAOFactory;
import lk.ijse.gdse.hostel.dao.custom.QueryDAO;
import lk.ijse.gdse.hostel.dao.custom.ReservationDAO;
import lk.ijse.gdse.hostel.dao.custom.RoomDAO;
import lk.ijse.gdse.hostel.dao.custom.StudentDAO;
import lk.ijse.gdse.hostel.entity.Reservation;
import lk.ijse.gdse.hostel.entity.Room;
import lk.ijse.gdse.hostel.entity.Student;
import lk.ijse.gdse.hostel.model.ReservationDTO;
import lk.ijse.gdse.hostel.model.RoomDTO;
import lk.ijse.gdse.hostel.model.StudentDTO;
import lk.ijse.gdse.hostel.projection.StudentDetailsDTO;
import lk.ijse.gdse.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    ReservationDAO reservationDAO= (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATIONDAO);
    StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENTDAO);
    RoomDAO roomDAO= (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOMDAO);
    QueryDAO queryDAO= (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    private Session session;
    @Override
    public List<ReservationDTO> getAllReservation() throws Exception {
        session= SessionFactoryConfig.getInstance().getSession();
        reservationDAO.setSession(session);
        List<Reservation>reservations= reservationDAO.getAll();
        List<ReservationDTO>reservationDTOS=new ArrayList<>();
        for (Reservation reservation:reservations) {
            reservationDTOS.add(
                    new ReservationDTO(
                            reservation.getRes_id(),
                            reservation.getDate(),
                            reservation.getStatus(),
                            new StudentDTO(
                                    reservation.getStudent().getStudent_id(),
                                    reservation.getStudent().getName(),
                                    reservation.getStudent().getAddress(),
                                    reservation.getStudent().getContact_no(),
                                    reservation.getStudent().getDob(),
                                    reservation.getStudent().getGender()
                            ),
                            new RoomDTO(
                                    reservation.getRoom().getRoom_type_id(),
                                    reservation.getRoom().getType(),
                                    reservation.getRoom().getKey_money(),
                                    reservation.getRoom().getQty())));
        }
        return  reservationDTOS;
    }

    @Override
    public String saveReservation(ReservationDTO reservationDTO) throws Exception {
        session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        try {
            reservationDAO.setSession(session);
            reservationDAO.save(new Reservation(
                            reservationDTO.getResId(),
                            reservationDTO.getDate(),
                            reservationDTO.getStatus(),
                            new Student(
                                    reservationDTO.getStudentDTO().getStudent_id(),
                                    reservationDTO.getStudentDTO().getName(),
                                    reservationDTO.getStudentDTO().getAddress(),
                                    reservationDTO.getStudentDTO().getContact_no(),
                                    reservationDTO.getStudentDTO().getDob(),
                                    reservationDTO.getStudentDTO().getGender()
                            ),
                            new Room(
                                    reservationDTO.getRoomDTO().getRoom_type_id(),
                                    reservationDTO.getRoomDTO().getType(),
                                    reservationDTO.getRoomDTO().getKey_money(),
                                    reservationDTO.getRoomDTO().getQty()
                            )
                    )
            );
            transaction.commit();
            session.close();
            return String.valueOf(true);
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return null;
        }
    }

    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) throws Exception {
        session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            reservationDAO.setSession(session);
            reservationDAO.update(new Reservation(
                            reservationDTO.getResId(),
                            reservationDTO.getDate(),
                            reservationDTO.getStatus(),
                            new Student(
                                    reservationDTO.getStudentDTO().getStudent_id(),
                                    reservationDTO.getStudentDTO().getName(),
                                    reservationDTO.getStudentDTO().getAddress(),
                                    reservationDTO.getStudentDTO().getContact_no(),
                                    reservationDTO.getStudentDTO().getDob(),
                                    reservationDTO.getStudentDTO().getGender()
                            ),
                            new Room(
                                    reservationDTO.getRoomDTO().getRoom_type_id(),
                                    reservationDTO.getRoomDTO().getType(),
                                    reservationDTO.getRoomDTO().getKey_money(),
                                    reservationDTO.getRoomDTO().getQty()
                            )
                    )
            );
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean deleteReservation(ReservationDTO reservationDTO) throws Exception {
        session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            reservationDAO.setSession(session);
            reservationDAO.delete(new Reservation(reservationDTO.getResId(),reservationDTO.getDate(),
                    reservationDTO.getStatus()));
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
    public String generateReservationId() throws Exception {
        session= SessionFactoryConfig.getInstance().getSession();
        reservationDAO.setSession(session);
        return reservationDAO.genarateNewId();
    }

    @Override
    public StudentDTO getStudents(String id) throws Exception {
        try {
            session= SessionFactoryConfig.getInstance().getSession();
            Transaction transaction=session.beginTransaction();
            studentDAO.setSession(session);
            Student student=studentDAO.getObject(id);
            transaction.commit();
            session.close();
            return new StudentDTO(student.getStudent_id(),student.getName(),student.getAddress(),student.getContact_no(),
                    student.getDob(),student.getGender());
        }catch (Exception e){
            session.close();
            return null;
        }

    }

    @Override
    public RoomDTO getRooms(String id) throws Exception {
        try {
            session= SessionFactoryConfig.getInstance().getSession();
            Transaction transaction=session.beginTransaction();
            roomDAO.setSession(session);
            Room room=roomDAO.getObject(id);
            transaction.commit();
            session.close();
            return new RoomDTO(room.getRoom_type_id(),room.getType(),room.getKey_money(),room.getQty());
        }catch (Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public List<String> getStudentIds() {
        try {
            session=SessionFactoryConfig.getInstance().getSession();
            studentDAO.setSession(session);
            session.close();
            return studentDAO.geIds();
        }catch (Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public List<String> getRoomIds() {
        try {
            session=SessionFactoryConfig.getInstance().getSession();
            roomDAO.setSession(session);
            return roomDAO.getId();
        }catch (Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public List<StudentDTO> geAllStudents() throws Exception {
        session= SessionFactoryConfig.getInstance().getSession();
        studentDAO.setSession(session);
        List<Student>allStudent=studentDAO.getAll();
        List<StudentDTO>studentDTOList=new ArrayList<>();
        for (Student student :allStudent){
            studentDTOList.add(new StudentDTO(student.getStudent_id(),student.getName(),student.getAddress(),
                    student.getContact_no(),student.getDob(),student.getGender()));
        }
        return studentDTOList;
    }

    @Override
    public List<RoomDTO> getAllRooms() throws Exception {
        session= SessionFactoryConfig.getInstance().getSession();
        roomDAO.setSession(session);
        List<Room>allRooms=roomDAO.getAll();
        List<RoomDTO>roomDTOS=new ArrayList<>();
        for (Room room : allRooms){
            roomDTOS.add(new RoomDTO(room.getRoom_type_id(),room.getType(),room.getKey_money(),room.getQty()));
        }
        return roomDTOS;
    }

    @Override
    public boolean updateRoomQty(RoomDTO roomDTO) throws Exception {
        session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            roomDAO.setSession(session);
            roomDAO.update(new Room(roomDTO.getRoom_type_id(),roomDTO.getType(),roomDTO.getKey_money(),
                    roomDTO.getQty()));
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
    public List<StudentDetailsDTO> getAllProjection() {
        session=SessionFactoryConfig.getInstance().getSession();
        return queryDAO.getAllStudentProjection();
    }

    @Override
    public boolean checkStatusClick(String id, String status) {
        session=SessionFactoryConfig.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        boolean isUpdate=false;
        try {
            reservationDAO.setSession(session);
            isUpdate=reservationDAO.changeCheckBOXValue(id,status);
            transaction.commit();
            session.close();
        }catch (Exception e){
            transaction.rollback();
            session.close();
        }
        return isUpdate;
    }

}
