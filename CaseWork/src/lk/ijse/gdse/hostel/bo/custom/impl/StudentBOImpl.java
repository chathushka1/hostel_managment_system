package lk.ijse.gdse.hostel.bo.custom.impl;

import lk.ijse.gdse.hostel.bo.custom.StudentBO;
import lk.ijse.gdse.hostel.dao.DAOFactory;
import lk.ijse.gdse.hostel.dao.custom.StudentDAO;
import lk.ijse.gdse.hostel.entity.Student;
import lk.ijse.gdse.hostel.model.StudentDTO;
import lk.ijse.gdse.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENTDAO);
    private Session session;
    @Override
    public List<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException {
        session = SessionFactoryConfig.getInstance().getSession();
        studentDAO.setSession(session);
        List<Student> allStudent = studentDAO.getAll();
        List<StudentDTO> studentDTOList = new ArrayList<>();

        for(Student student : allStudent){
            studentDTOList.add(new StudentDTO(student.getStudent_id(),student.getName(),
                    student.getAddress(),student.getContact_no(),student.getDob(),student.getGender()));
        }
        return studentDTOList;
    }

    @Override
    public String saveStudent(StudentDTO studentDTO) {
        session =SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            studentDAO.setSession(session);
            String id = (String) studentDAO.save(new Student(studentDTO.getStudent_id(),studentDTO.getName(),
                    studentDTO.getAddress(),studentDTO.getContact_no(),studentDTO.getDob(),studentDTO.getGender()));
            transaction.commit();
            session.close();
            return id;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return "S00-001";
        }
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {
        session =SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            studentDAO.setSession(session);
            studentDAO.update(new Student(studentDTO.getStudent_id(),studentDTO.getName(),
                    studentDTO.getAddress(),studentDTO.getContact_no(),studentDTO.getDob(),studentDTO.getGender()));
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
    public boolean deleteStudent(StudentDTO studentDTO) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            studentDAO.setSession(session);
            studentDAO.delete(new Student(studentDTO.getStudent_id(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getContact_no(),
                    studentDTO.getDob(),studentDTO.getGender()));
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
    public String genarateStudentID() {
        session = SessionFactoryConfig.getInstance().getSession();
        studentDAO.setSession(session);
        return studentDAO.genarateNewId();
    }
}
