package hw5.mishanya.homework5;

public class Person {
    private String firstName;

    private String lastName;

    private String street;

    private String city;

    private String code;

    private String birthday;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCode() {
        return code;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Person(String firstName, String lastName, String street, String city, String code, String birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.code = code;
        this.birthday = birthday;
    }
}
