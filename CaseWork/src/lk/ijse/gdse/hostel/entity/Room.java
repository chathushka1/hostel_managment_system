package lk.ijse.gdse.hostel.entity;

public class Room {
    private String room_type_id;
    private String type;
    private String key_money;
    private int qty;

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
