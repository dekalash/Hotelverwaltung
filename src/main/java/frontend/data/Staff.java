package frontend.data;


import frontend.Fetchers.PersonFetcher;
import frontend.util.BackendException;
import frontend.util.JsonParser;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Staff {
    private static PersonFetcher personFetcher = new PersonFetcher();

    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty birthDate;
    private StringProperty postalCode;
    private StringProperty city;
    private StringProperty street;
    private StringProperty email;
    private StringProperty phoneNumber;

    public Staff(String firstName, String lastName, String birthDate, String postalCode, String city, String street, String email,
            String phoneNumber) {
        this.firstName = firstNameProperty();
        this.lastName = lastNameProperty();
        this.birthDate = birthDateProperty();
        this.postalCode = postalCodeProperty();
        this.city = cityProperty();
        this.street = streetProperty();
        this.email = emailProperty();
        this.phoneNumber = phoneNumberProperty();

        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setPostalCode(postalCode);
        setCity(city);
        setStreet(street);
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }

    private void setPhoneNumber(String string) {
        phoneNumber.set(string);
    }

    private StringProperty phoneNumberProperty() {
        return phoneNumber == null ? new SimpleStringProperty(this, "phoneNumber") : phoneNumber;
    }

    private StringProperty emailProperty() {
        return email == null ? new SimpleStringProperty(this, "email") : email;
    }

    private void setEmail(String string) {
        email.set(string);
    }

    private void setStreet(String string) {
        street.set(string);
    }

    private StringProperty streetProperty() {
        return street == null ? new SimpleStringProperty(this, "street") : street;
    }

    @Override
    public String toString() {
        return firstName.get() + lastName.get() + birthDate.get() + postalCode.get() + city.get() + street.get() + email.get()
                + phoneNumber.get();
    }

    public void setFirstName(String string) {
        firstName.set(string);
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

    public static ObservableList<Staff> fetchAll() throws BackendException {
        try {
            return FXCollections.observableArrayList(JsonParser.parseStaff(personFetcher.fetchEmployees()));
        } catch (Exception exception) {
            throw new BackendException(exception);
        }
    }

    public static void add(String firstName, String lastName, String email, String phone, int gender, String street, int houseNR, String city, String state, String zip) throws BackendException {
        try {
            PersonFetcher.fetchCreateEmployee(firstName, lastName, email, phone, gender, street, houseNR, city, state, zip);
        } catch (Exception exception) {
            throw new BackendException(exception);
        }
    }

    
    public void update() throws BackendException {
        try {
            //TODO save the changes made to this staff
            //update person and update person address
            System.out.println("staff-update " + this.toString());
        } catch (Exception exception) {
            throw new BackendException(exception);
        }
    }

    public void delete() throws BackendException {
        try {
            //TODO delete this instance of staff
        System.out.println("staff-delete " + this.toString());
        } catch (Exception exception) {
            throw new BackendException(exception);
        }
    }

}
