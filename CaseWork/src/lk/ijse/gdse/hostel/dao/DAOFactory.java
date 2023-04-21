package lk.ijse.gdse.hostel.dao;

import lk.ijse.gdse.hostel.dao.custom.impl.QueryDAOImpl;
import lk.ijse.gdse.hostel.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.gdse.hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.gdse.hostel.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        if (daoFactory==null){
            daoFactory= new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes{
        STUDENTDAO,ROOMDAO,RESERVATIONDAO,QUERY
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case STUDENTDAO:
                return new StudentDAOImpl();
            case ROOMDAO:
                return new RoomDAOImpl();
            case RESERVATIONDAO:
                return new ReservationDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
        }
        return null;
    }
}
