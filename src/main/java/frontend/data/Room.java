package frontend.data;

import java.util.List;

import frontend.Fetchers.RoomFetcher;
import frontend.util.BackendException;
import frontend.util.JsonParser;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Room {
    private static RoomFetcher roomFetcher = new RoomFetcher();

    private IndicatorEnum Indicator;
    private LongProperty RoomNumber;
    private StringProperty RoomType;
    private StringProperty price;
    private StringProperty OneBedRoom;
    private StringProperty TwoBedRoom;

    public Room(IndicatorEnum Indicator, long RoomNumber, String RoomType, String price, String OneBedRoom, String TwoBedRoom) {
        this.Indicator = getIndicator();
        this.RoomNumber = roomNumberProperty();
        this.RoomType = roomTypeProperty();
        this.price = priceProperty();
        this.OneBedRoom = oneBedRoomProperty();
        this.TwoBedRoom = twoBedRoomProperty();

        setIndicator(Indicator);
        setRoomNumber(RoomNumber);
        setRoomType(RoomType);
        setPrice(price);
        setOneBedRoom(OneBedRoom);
        setTwoBedRoom(TwoBedRoom);
    }

    @Override
    public String toString() {
        return RoomNumber.get() + RoomType.get() + price.get() + OneBedRoom.get() + TwoBedRoom.get();
    }

    public void setIndicator(IndicatorEnum indicator) {
        this.Indicator = indicator;
    }

    public IndicatorEnum getIndicator() {
        return this.Indicator;
    }

    public void setRoomNumber(long number) {
        RoomNumber.set(number);
    }

    public long getRoomNumber() {
        return RoomNumber.get();
    }

    public LongProperty roomNumberProperty() {
        return RoomNumber == null ? new SimpleLongProperty(this, "RoomNumber") : RoomNumber;
    }

    public void setRoomType(String string) {
        RoomType.set(string);
    }

    public String getRoomType() {
        return RoomType.get();
    }

    public StringProperty roomTypeProperty() {
        return RoomType == null ? new SimpleStringProperty(this, "RoomType") : RoomType;
    }

    public void setPrice(String string) {
        price.set(string);
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price == null ? new SimpleStringProperty(this, "price") : price;
    }

    public void setOneBedRoom(String string) {
        OneBedRoom.set(string);
    }

    public String getOneBedRoom() {
        return OneBedRoom.get();
    }

    public StringProperty oneBedRoomProperty() {
        return OneBedRoom == null ? new SimpleStringProperty(this, "OneBedRoom") : OneBedRoom;
    }

    public void setTwoBedRoom(String string) {
        TwoBedRoom.set(string);
    }

    public String getTwoBedRoom() {
        return TwoBedRoom.get();
    }

    public StringProperty twoBedRoomProperty() {
        return TwoBedRoom == null ? new SimpleStringProperty(this, "TwoBedRoom") : TwoBedRoom;
    }

    public static ObservableList<Room> fetchAll() throws BackendException {
        try {
            return FXCollections.observableArrayList(JsonParser.parseRooms(roomFetcher.fetchRooms()));
        } catch (Exception exception) {
            throw new BackendException(exception);
        }
    }

    public static List<String> fetchRoomTypes() throws BackendException {
        try {
            return JsonParser.parseRoomTypes(roomFetcher.fetchRoomTypes());
        } catch (Exception exception) {
            throw new BackendException(exception);
        }
    }

    public void setStatus(IndicatorEnum status) throws BackendException {
        try {
            //TODO change the state of this room to the corresponding state
            System.out.println("room-state-change " + this.toString());
        } catch (Exception exception) {
            throw new BackendException(exception);
        }
    }

    //TODO add arguments
    public static void add(Long roomType, int floor, int roomNr, String name) throws BackendException {
        try {
            roomFetcher.fetchCreateRoom(roomType, floor, roomNr, name);
        } catch (Exception exception) {
            throw new BackendException(exception);
        }
    }

    public void update() throws BackendException {
        try {
            //TODO save the changes made to this room
            System.out.println("room-update " + this.toString());
        } catch (Exception exception) {
            throw new BackendException(exception);
        }
    }

    public void delete() throws BackendException {
        try {
            //TODO delete this room
            System.out.println("room-delete " + this.toString());
        } catch (Exception exception) {
            throw new BackendException(exception);
        }
    }

}
