package lk.ijse.gdse.hostel.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T,ID> {
    List<T> getAll() throws SQLException, ClassNotFoundException;
    ID save (T dto);
    void update(T dto);
    void delete(T dto);
    ID genarateNewId();
    T getObject(ID id);
}
