package Controllers;

import DAO.BookingsDAO;
import Factory.ConnectionFactory;
import models.Bookings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingsController {

    private BookingsDAO bookingsDAO;

    public BookingsController(){
        Connection connection = new ConnectionFactory().recoverConnection();
        this.bookingsDAO = new BookingsDAO(connection);
    }

    public void postBooking(Bookings booking){
        this.bookingsDAO.create(booking);
    }

    public List<Bookings> findBookings(){
        return bookingsDAO.find();
    }

    public boolean update(String column, String value, String id){
        String rowValue = null;
        if(column.equals("Data Check In")){
            rowValue = "entryDate";
        } else if(column.equals("Data Check Out")){
            rowValue = "exitDate";
        } else{
            bookingsDAO.update(value, Integer.parseInt(id));
            return true;
        }
        Date date;
        try{
            date = Date.valueOf(value);
        } catch(IllegalArgumentException e){
            return false;
        }
        bookingsDAO.update(rowValue, date, Integer.parseInt(id));
        return true;
    }

    public void update(Double value, String id){
        bookingsDAO.update(value, Integer.parseInt(id));
    }
}
