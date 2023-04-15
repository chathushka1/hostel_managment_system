package lk.ijse.gdse.hostel.dao;

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
        STUDENTDAO
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case STUDENTDAO:
                return new StudentDAOImpl();
        }
        return null;
    }
}
