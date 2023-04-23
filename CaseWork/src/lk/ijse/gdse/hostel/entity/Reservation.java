package lk.ijse.gdse.hostel.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Reservation")
public class Reservation {
    @Id
    @Column(name = "reservationId",length = 10)
    private String res_id;
    @Column(name = "date")
    private Date date;
    @Column(name = "status")
    private String status;

   /* @ManyToOne
    @JoinColumn(name = "studentId",referencedColumnName = "studentId",insertable = false,updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "roomTypeId",referencedColumnName = "roomTypeId",insertable = false,updatable = false)
    private Room room;*/
   @ManyToOne
   @JoinColumn(name = "studentId")
   private Student student;

    @ManyToOne
    @JoinColumn(name = "roomTypeId")
    private Room room;

    public Reservation() {
    }

    public Reservation(String res_id, Date date, String status) {
        this.res_id=res_id;
        this.date = date;
        this.status =status;
    }
    public Reservation(String resId, Date date, String status, Student student, Room room) {
        this.res_id = res_id;
        this.date = date;
        this.status = status;
        this.student = student;
        this.room = room;
    }
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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
