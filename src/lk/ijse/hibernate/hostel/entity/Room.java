package lk.ijse.hibernate.hostel.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "room")
public class Room {

    @Id
    @Column(name = "room_id", length = 25)
    private String roomTypeId;
    @Column(name = "room_type")
    private String type;
    @Column(name = "key_money")
    private String keyMoney;
    @Column(name = "quantity")
    private int qty;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "room")
    List<Reservation> reservationList;

    public Room(String roomTypeId, String type, String keyMoney, int qty) {
        this.roomTypeId = roomTypeId;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }

    public Room() {

    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(String keyMoney) {
        this.keyMoney = keyMoney;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }


}
