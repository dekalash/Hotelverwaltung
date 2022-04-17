package frontend.data;

import frontend.Fetchers.BookingFetcher;
import frontend.util.BackendException;
import frontend.util.JsonParser;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Booking {
    private static BookingFetcher bookingFetcher = new BookingFetcher();

    public StringProperty firstName;
    public StringProperty lastName;
    public StringProperty birthDate;
    public StringProperty postalCode;
    public StringProperty city;
    public StringProperty street;
    public StringProperty email;
    public StringProperty phoneNumber;
    public StringProperty roomType;
    public LongProperty roomNr;
    public StringProperty startDate;
    public StringProperty endDate;
    public StringProperty price;
    public IndicatorEnum Indicator;

    //TODO add indicator to booking
    public Booking(IndicatorEnum Indicator,String firstName, String lastName, String birthDate, String postalCode, String city, String street, String email,
            String phoneNumber, String roomType, Long roomNr, String startDate, String endDate, String price) {
        this.Indicator = getIndicator();
        this.firstName = firstNameProperty();
        this.lastName = lastNameProperty();
        this.birthDate = birthDateProperty();
        this.postalCode = postalCodeProperty();
        this.city = cityProperty();
        this.street = streetProperty();
        this.email = emailProperty();
        this.phoneNumber = phoneNumberProperty();
        this.roomType = roomTypeProperty();
        this.roomNr = roomNrProperty();
        this.startDate = startDateProperty();
        this.endDate = endDateProperty();
        this.price = priceProperty();

        setRoomType(roomType);
        setRoomNr(roomNr);
        setStartDate(startDate);
        setEndDate(endDate);
        setPrice(price);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setPostalCode(postalCode);
        setCity(city);
        setStreet(street);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setIndicator(Indicator);
    }

    public void setIndicator(IndicatorEnum indicator) {
        this.Indicator = indicator;
    }

    public IndicatorEnum getIndicator() {
        return this.Indicator;
    }

    public void setPrice(String string) {
        price.set(string);
    }

    public void setEndDate(String string) {
        endDate.set(string);
    }

    public void setStartDate(String string) {
        startDate.set(string);
    }

    public void setRoomNr(long number) {
        roomNr.set(number);
    }

    public void setRoomType(String string) {
        roomType.set(string);
    }

    public StringProperty priceProperty() {
        return price == null ? new SimpleStringProperty(this, "price") : price;
    }

    public StringProperty endDateProperty() {
        return endDate == null ? new SimpleStringProperty(this, "endDate") : endDate;
    }

    public StringProperty startDateProperty() {
        return startDate == null ? new SimpleStringProperty(this, "startDate") : startDate;
    }

    public LongProperty roomNrProperty() {
        return roomNr == null ? new SimpleLongProperty(this, "roomNr") : roomNr;
    }

    public StringProperty roomTypeProperty() {
        return roomType == null ? new SimpleStringProperty(this, "roomType") : roomType;
    }

    public void setPhoneNumber(String string) {
        phoneNumber.set(string);
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber == null ? new SimpleStringProperty(this, "phoneNumber") : phoneNumber;
    }

    public StringProperty emailProperty() {
        return email == null ? new SimpleStringProperty(this, "email") : email;
    }

    public void setEmail(String string) {
        email.set(string);
    }

    public void setStreet(String string) {
        street.set(string);
    }

    public StringProperty streetProperty() {
        return street == null ? new SimpleStringProperty(this, "street") : street;
    }

    @Override
    public String toString() {
        return Indicator + firstName.get() + lastName.get() + birthDate.get() + postalCode.get() + city.get() + street.get() + email.get()
                + phoneNumber.get() + roomType.get() + roomNr.get() + startDate.get() + endDate.get() + price.get();
    }

    public void setFirstName(String string) {
        firstName.set(string);
    }

    public String getPrice() {
        return price.get();
    }

    public String getEndDate() {
        return endDate.get();
    }

    public String getStartDate() {
        return startDate.get();
    }

    public String getRoomType() {
        return roomType.get();
    }

    public long getRoomNr() {
        return roomNr.get();
    }

    public String getStreet() {
        return street.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getPostalCode() {
        return postalCode.get();
    }

    public String getBirthDate() {
        return birthDate.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName == null ? new SimpleStringProperty(this, "firstName") : firstName;
    }

    public void setLastName(String string) {
        lastName.set(string);
    }

    public StringProperty lastNameProperty() {
        return lastName == null ? new SimpleStringProperty(this, "lastName") : lastName;
    }

    public void setBirthDate(String string) {
        birthDate.set(string);
    }

    public StringProperty birthDateProperty() {
        return birthDate == null ? new SimpleStringProperty(this, "birthDate") : birthDate;
    }

    public void setPostalCode(String string) {
        postalCode.set(string);
    }

    public StringProperty postalCodeProperty() {
        return postalCode == null ? new SimpleStringProperty(this, "postalCode") : postalCode;
    }

    public void setCity(String string) {
        city.set(string);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city == null ? new SimpleStringProperty(this, "city") : city;
    }

    public static ObservableList<Booking> fetchAll() throws BackendException {
        try {
            return FXCollections.observableArrayList(JsonParser.parseBookings(bookingFetcher.fetchAllBookings()));
        } catch (Exception exception) {
            throw new BackendException(exception);
        }
    }

    public static ObservableList<Booking> fetchBeginningToday() throws BackendException {
        try {
            //TODO return /readBB
            return FXCollections.observableArrayList(JsonParser.parseBookings(bookingFetcher.fetchTodaysBeginningBookings()));
        } catch (Exception exception) {
            throw new BackendException(exception);
        }
    }

    public static ObservableList<Booking> fetchEndingToday() throws BackendException {
        try {
            //TODO return /readEB
            return null;
        } catch (Exception exception) {
            throw new BackendException(exception);
        }
    }

    public void checkIn() throws BackendException {
        try {
            //TODO change the state of this booking
            System.out.println("booking-check-in " + this.toString());
        } catch (Exception exception) {
            throw new BackendException(exception);
        }
    }

    public void checkOut() throws BackendException {
        try {
            //TODO change the state of this booking / delete this booking
            System.out.println("booking-check-out " + this.toString());
        } catch (Exception exception) {
            throw new BackendException(exception);
        }
    }

    //TODO add arguments
    public static void add() throws BackendException {
        try {
            //TODO create a new booking in backend
        } catch (Exception exception) {
            throw new BackendException(exception);
        }
    }

    public void update() throws BackendException {
        try {
            //TODO save the changes made to this booking
            System.out.println("booking-update " + this);
        } catch (Exception exception) {
            throw new BackendException(exception);
        }
    }

    public void delete() throws BackendException {
        try {
            //TODO delete this booking
            System.out.println("booking-delete " + this);
        } catch (Exception exception) {
            throw new BackendException(exception);
        }
    }
}
