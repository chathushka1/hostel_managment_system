package lk.ijse.gdse.hostel.model;

import java.sql.Date;

public class ReservationDTO {
    private String res_id;
    private Date date;
    private String student_id;
    private String room_type_id;
    private String status;

    public ReservationDTO() {
    }

    public ReservationDTO(String res_id, Date date, String student_id, String room_type_id, String status) {
        this.res_id = res_id;
        this.date = date;
        this.student_id = student_id;
        this.room_type_id = room_type_id;
        this.status = status;
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

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(String room_type_id) {
        this.room_type_id = room_type_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "res_id='" + res_id + '\'' +
                ", date=" + date +
                ", student_id='" + student_id + '\'' +
                ", room_type_id='" + room_type_id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
