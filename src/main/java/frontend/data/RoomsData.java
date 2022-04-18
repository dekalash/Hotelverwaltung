package frontend.data;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RoomsData {
    private final StringProperty status;
    private final StringProperty roomNr;
    private final StringProperty floor;
    private final StringProperty roomId;
    private final StringProperty roomType;
    private final DoubleProperty price;
    private final StringProperty singleBeds;
    private final StringProperty doubleBeds;
    
    public RoomsData(String status, String roomnr, String floor, String roomid, String roomrype,double price, String singlebeds, String doublebeds){
        this.status = new SimpleStringProperty(status);
        this.roomNr = new SimpleStringProperty(roomnr);
        this.floor = new SimpleStringProperty(floor);
        this.roomId = new SimpleStringProperty(roomid);
        this.roomType = new SimpleStringProperty(roomrype);
        this.price = new SimpleDoubleProperty(price);
        this.singleBeds = new SimpleStringProperty(singlebeds);
        this.doubleBeds = new SimpleStringProperty(doublebeds);
    }

    

    public StringProperty statusProperty() {
        return status;
    }

    public StringProperty roomNrProperty() {
        return roomNr;
    }

    public StringProperty floorProperty() {
        return floor;
    }
    
    public StringProperty roomIdProperty() {
        return roomId;
    }

    public StringProperty roomTypeProperty() {
        return roomType;
    }

    public DoubleProperty priceProperty(){
        return price;
    }
    public StringProperty singleBedsProperty() {
        return singleBeds;
    }

    public StringProperty doubleBedsProperty() {
        return doubleBeds;
    }

    public String getStatus(){
        return status.get();
    }

    public String getRoomNr(){
        return roomNr.get();
    }
    public String getFloor(){
        return floor.get();
    }
    public String getRoomId(){
        return roomId.get();
    }
    public String getRoomType(){
        return roomType.get();
    }
    public double getPrice(){
        return price.get();
    }
    public String getSingleBeds(){
        return singleBeds.get();
    }
    public String getDoubleBeds(){
        return doubleBeds.get();
    }
    public void setStatus(String status){
        this.status.set(status);
    }    
    
    public void setRoomNr(String roomNr){
        this.roomNr.set(roomNr);
    }    
    
    public void setFloor(String floor){
        this.floor.set(floor);
    }    
    
    public void setRoomId(String roomId){
        this.roomId.set(roomId);
    }    
    public void setPrice(double price){
        this.price.set(price);
    }

    public void setSingleBeds(String singleBeds){
        this.singleBeds.set(singleBeds);
    }        
    
    public void setDoubleBeds(String doubleBeds){
        this.doubleBeds.set(doubleBeds);
    }      


}
