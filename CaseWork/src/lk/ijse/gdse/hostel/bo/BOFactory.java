package lk.ijse.gdse.hostel.bo;

import lk.ijse.gdse.hostel.bo.custom.impl.RoomBOImpl;
import lk.ijse.gdse.hostel.bo.custom.impl.StudentBOImpl;

public class BOFactory {
    public static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        if(boFactory==null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes{
        STUDENTBO,ROOMBO,RESERVATIONBO
    }

    public SuperBO getBo(BOTypes boTypes){
        switch (boTypes){
            case STUDENTBO:
                return new StudentBOImpl();
            case ROOMBO:
                //return new RoomBOImpl();
        }
        return null;
    }
}
