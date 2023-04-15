package lk.ijse.gdse.hostel.bo.custom;

import lk.ijse.gdse.hostel.bo.SuperBO;
import lk.ijse.gdse.hostel.model.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO {
    List<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException;

    String saveStudent(StudentDTO studentDTO);

    boolean updateStudent(StudentDTO studentDTO);
    boolean deleteStudent(StudentDTO studentDTO);

    String genarateStudentID();
}
