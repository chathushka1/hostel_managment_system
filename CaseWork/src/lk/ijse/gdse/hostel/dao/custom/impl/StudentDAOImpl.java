package lk.ijse.gdse.hostel.dao.custom.impl;

import org.hibernate.query.Query;
import lk.ijse.gdse.hostel.dao.custom.StudentDAO;
import lk.ijse.gdse.hostel.entity.Student;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private Session session;

    @Override
    public List<Student> getAll() throws SQLException, ClassNotFoundException  {
        String sql= "FROM Student";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public String genarateNewId() {
        String sql="FROM Student ORDER BY id DESC";
        Student student= (Student) session.createQuery(sql).setMaxResults(1).uniqueResult();
        session.close();
        if (student!=null){
            String lastId=student.getStudent_id();
            int newCustomerId=Integer.parseInt(lastId.replace("S00-",""))+1;
            return String.format("S00-%03d",newCustomerId);
        }
        return "S00-001";
    }

    @Override
    public Student getObject(String student) {
        return session.get(Student.class,student);
    }

    @Override
    public void delete(Student dto) {
        session.delete(dto);
    }

    @Override
    public void update(Student dto) {
        session.update(dto);
    }

    @Override
    public String save(Student dto) {

        return (String) session.save(dto);
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }

   /* @Override
    public List<String> getIds(){
        String sqlQuery = "SELECT id FROM Student";
        Query query = session.createQuery(sqlQuery);
        List<String> list = query.list();
        System.out.println(list);
        return list;
    }*/
}
