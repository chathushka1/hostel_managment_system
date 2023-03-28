package lk.ijse.gdse.hostel.entity;

import java.sql.Date;

public class Reservation {
    private String res_id;
    private Date date;
    private String status;

    public Reservation() {
    }

    public Reservation(String res_id, Date date, String status) {
        this.setRes_id(res_id);
        this.setDate(date);
        this.setStatus(status);
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "res_id='" + res_id + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
