package Controllers;

import DAO.GuestsDAO;
import Factory.ConnectionFactory;
import models.Guests;

import java.sql.*;
import java.util.List;

public class GuestsController {

    private GuestsDAO guestsDAO;

    public GuestsController(){
        Connection connection = new ConnectionFactory().recoverConnection();
        this.guestsDAO = new GuestsDAO(connection);
    }

    public void postGuets(Guests guest){
        this.guestsDAO.create(guest);
    }

    public List<Guests> findGuests(){
        return guestsDAO.findAll();
    }

    public List<Guests> find(String search){
        Integer number;
        try{
            number = Integer.parseInt(search);
        } catch(NumberFormatException e){
            number = 0;
        }
        return guestsDAO.find(search, number);
    }

    public void delete(Integer id){
        this.guestsDAO.delete(id);
    }

    public boolean update(String column, String value, Integer id){
        if(column.equals("Nascimento")){
            Date date;
            try{
                date = Date.valueOf(value);
            } catch(IllegalArgumentException e){
                return false;
            }
            guestsDAO.updateDate(date, id);
        } else{
            guestsDAO.updateString(column, value, id);
        }
        return true;
    }
}
