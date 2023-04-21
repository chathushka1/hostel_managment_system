package lk.ijse.gdse.hostel.bo.custom;

import lk.ijse.gdse.hostel.bo.SuperBO;
import lk.ijse.gdse.hostel.model.RoomDTO;
import lk.ijse.gdse.hostel.model.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface RoomBO extends SuperBO {
    List<RoomDTO> getAllRooms() throws SQLException, ClassNotFoundException;

    String saveRooms(RoomDTO roomDTO);

    boolean updateRooms(RoomDTO roomDTO);
    boolean deleteRooms(RoomDTO roomDTO);

    String genarateRooms();
}
