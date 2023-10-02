package models;

import java.util.Date;

public class Guests {

    private Integer id;
    private String name;
    private String lastName;
    private Date birthDate;
    private String nationality;
    private String phone;
    private Integer idBooking;
    private Bookings booking;

    public Guests(String name, String lastName, Date birthDate, String nationality, String phone, Integer idBooking){
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.phone = phone;
        this.idBooking = idBooking;
    }

    public Guests(Integer id, String name, String lastName, Date birthDate, String nationality, String phone, Integer idBooking){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.phone = phone;
        this.idBooking = idBooking;
    }

    public Guests(Integer id, String name, String lastName, Date birthDate, String nationality, String phone, Integer idBooking, Bookings booking){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.phone = phone;
        this.idBooking = idBooking;
        this.booking = booking;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public String getNationality() {
        return this.nationality;
    }

    public String getPhone() {
        return this.phone;
    }

    public Integer getIdBooking() {
        return this.idBooking;
    }

    public Bookings getBooking() {
        return this.booking;
    }

    public String[] toListString() {
        String[] string = {id.toString(), name, lastName, birthDate.toString(), nationality, phone, idBooking.toString()};
        return string;
    }
}
