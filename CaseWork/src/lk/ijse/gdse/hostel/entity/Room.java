package lk.ijse.gdse.hostel.entity;

import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Room")
public class Room {

    @Id
    @Column(name = "roomTypeId",length = 10)
    private String room_type_id;
    @Column(name = "type")
    private String type;
    @Column(name = "keyMoney")
    private String key_money;
    @Column(name = "qty")
    private int qty;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "room")
    private List<Reservation> reservationList= new ArrayList<>();

    public Room() {
    }

    public Room(String room_type_id, String type, String key_money, int qty) {
        this.setRoom_type_id(room_type_id);
        this.setType(type);
        this.setKey_money(key_money);
        this.setQty(qty);
    }

    public String getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(String room_type_id) {
        this.room_type_id = room_type_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey_money() {
        return key_money;
    }

    public void setKey_money(String key_money) {
        this.key_money = key_money;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Room{" +
                "room_type_id='" + room_type_id + '\'' +
                ", type='" + type + '\'' +
                ", key_money='" + key_money + '\'' +
                ", qty=" + qty +
                '}';
    }
}
