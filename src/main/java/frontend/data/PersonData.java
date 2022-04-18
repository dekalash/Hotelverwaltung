package frontend.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonData {
    
    private final StringProperty lastName;
    private final StringProperty firstName;
    private final StringProperty DOB;
    private final StringProperty street;
    private final StringProperty email;
    private final StringProperty role;

    public PersonData(String lastname, String firstname, String dob, String street, String email,String role){
        this.lastName = new SimpleStringProperty(lastname);
        this.firstName = new SimpleStringProperty(firstname);
        this.DOB = new SimpleStringProperty(dob);
        this.street = new SimpleStringProperty(street);
        this.email = new SimpleStringProperty(email);
        this.role = new SimpleStringProperty(role);
    }

    

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty dOBProperty() {
        return DOB;
    }

    public StringProperty streetProperty() {
        return street;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty roleProperty() {
        return role;
    }

    public String getFirstName(){
        return firstName.get();
    }

    public String getLastName(){
        return lastName.get();
    }
    public String getDOB(){
        return DOB.get();
    }
    public String getStreet(){
        return street.get();
    }
    public String getEmail(){
        return email.get();
    }
    public String getRole(){
        return role.get();
    }

    public void setFirstName(String firstname){
        this.firstName.set(firstname);
    }    
    
    public void setLastName(String lastname){
        this.lastName.set(lastname);
    }    
    
    public void setDOB(String dob){
        this.DOB.set(dob);
    }    
    
    public void setStreet(String street){
        this.street.set(street);
    }    
    
    public void setEmail(String email){
        this.email.set(email);
    }        
    
    public void setRole(String role){
        this.role.set(role);
    }      


}
