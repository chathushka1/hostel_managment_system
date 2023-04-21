package lk.ijse.gdse.hostel.dao.custom.impl;

import lk.ijse.gdse.hostel.dao.custom.QueryDAO;
import lk.ijse.gdse.hostel.projection.StudentDetailsDTO;
import lk.ijse.gdse.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    private Session session;
    @Override
    public List<StudentDetailsDTO> getAllStudentProjection() {
        session= SessionFactoryConfig.getInstance().getSession();
        String sql="SELECT new lk.ijse.hostelManagement.projection.StudentDetailsDTO(s.id,s.name,s.contact,r.date,r.id,r.room) FROM Student s INNER join s.reservationList r WHERE r.status='unPaid'";

        Query query=session.createQuery(sql);
        List<StudentDetailsDTO>list=query.list();
        session.close();
        return list;
    }
}
