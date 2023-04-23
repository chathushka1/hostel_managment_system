package lk.ijse.gdse.hostel.model;

import java.sql.Date;

public class ReservationDTO {
    private String res_id;
    private Date date;
    private String status;

    private StudentDTO studentDTO;
    private RoomDTO roomDTO;
    private String student_id;
    private String room_type_id;


    public ReservationDTO(String resId, Date date, String status) {
        this.res_id = resId;
        this.date = date;
        this.status = status;
    }

    public ReservationDTO(String resId, Date date, String status, StudentDTO studentDTO, RoomDTO roomDTO) {
        this.res_id = resId;
        this.date = date;
        this.status = status;
        this.studentDTO = studentDTO;
        this.roomDTO = roomDTO;
        student_id=studentDTO.getStudent_id();
        room_type_id=roomDTO.getRoom_type_id();
    }

    public String getResId() {
        return res_id;
    }

    public void setResId(String resId) {
        this.res_id = resId;
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

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
        student_id=studentDTO.getStudent_id();
    }

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
        room_type_id=roomDTO.getRoom_type_id();
    }

    public String getStudentId() {
        return student_id;
    }

    public void setStudentId(String studentId) {
        this.student_id = studentId;
    }

    public String getRoomId() {
        return room_type_id;
    }

    public void setRoomId(String roomId) {
        this.room_type_id = roomId;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "resId='" + res_id + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", studentDTO=" + studentDTO +
                ", roomDTO=" + roomDTO +
                ", studentId='" + student_id + '\'' +
                ", roomId='" + room_type_id + '\'' +
                '}';
    }
}
