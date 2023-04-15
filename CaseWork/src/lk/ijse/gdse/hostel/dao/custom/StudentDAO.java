package lk.ijse.gdse.hostel.dao.custom;

import lk.ijse.gdse.hostel.dao.CrudDAO;
import lk.ijse.gdse.hostel.dao.SuperDAO;

import lk.ijse.gdse.hostel.entity.Student;
import org.hibernate.Session;

public interface StudentDAO extends SuperDAO, CrudDAO<Student,String> {
    void setSession(Session session);
}
