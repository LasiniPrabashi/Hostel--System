package lk.ijse.hibernate.hostel.dto;

public class RoomDTO {
    private String roomId;
    private String type;
    private String keyMoney;
    private int qty;

    public RoomDTO(String roomId, String type, String keyMoney, int qty) {
        this.roomId = roomId;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
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
