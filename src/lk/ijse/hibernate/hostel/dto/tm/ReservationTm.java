package lk.ijse.hibernate.hostel.dto.tm;

public class ReservationTm {
    private String resId;
    private String stId;
    private String stName;
    private String roomId;
    private String roomName;
    private String status;

    public ReservationTm(String resId, String stId, String stName, String roomId, String roomName, String status) {
        this.resId = resId;
        this.stId = stId;
        this.stName = stName;
        this.roomId = roomId;
        this.roomName = roomName;
        this.status = status;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getStId() {
        return stId;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
