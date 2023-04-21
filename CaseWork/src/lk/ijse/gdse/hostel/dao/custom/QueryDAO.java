package lk.ijse.gdse.hostel.dao.custom;

import lk.ijse.gdse.hostel.dao.SuperDAO;
import lk.ijse.gdse.hostel.projection.StudentDetailsDTO;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<StudentDetailsDTO> getAllStudentProjection();
}
