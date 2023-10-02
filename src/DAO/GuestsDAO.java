package DAO;

import models.Bookings;
import models.Guests;

import java.util.Date;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestsDAO {

    private Connection connection;

    public GuestsDAO(Connection connection){
        this.connection=connection;
    }

    public void create(Guests guest){
        String sql = "INSERT INTO guests (name, lastname, birthdate, nationality, phone, idBooking) VALUES (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, guest.getName());
            preparedStatement.setString(2, guest.getLastName());
            preparedStatement.setDate(3, (java.sql.Date) new Date(guest.getBirthDate().getTime()));
            preparedStatement.setString(4, guest.getNationality());
            preparedStatement.setString(5, guest.getPhone());
            preparedStatement.setInt(6, guest.getIdBooking());
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                while(resultSet.next()){
                    guest.setId(resultSet.getInt(1));
                }
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Guests> findAll(){
        List<Guests> guests = new ArrayList<Guests>();
        String sql = "SELECT * FROM guests";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getResultSet()){
                while(resultSet.next()){
                    Guests guest = new Guests(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7));
                    guests.add(guest);
                }
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return guests;
    }

    public List<Guests> find(String lastName, Integer idBooking){
        List<Guests> guests = new ArrayList<Guests>();
        String sql = "SELECT * FROM guests H JOIN bookings R ON H.idBooking = R.id WHERE H.lastName = ? OR H.idBooking = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, lastName);
            preparedStatement.setInt(2, idBooking);
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getResultSet()){
                while(resultSet.next()){
                    Bookings booking = new Bookings(resultSet.getInt(8), resultSet.getDate(9), resultSet.getDate(10), resultSet.getDouble(11), resultSet.getString(12));
                    Guests guest = new Guests(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), booking);
                    guests.add(guest);
                }
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return guests;
    }

    public void delete(Integer count){
        String sql = "DELETE H, R FROM guests H JOIN bookings R ON H.idBooking = R.id WHERE H.idBooking = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, count);
            preparedStatement.execute();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateString(String column, String value, Integer id){
        String sql = "UPDATE guests SET " + column + " = ? WHERE id = ? LIMIT 1";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, value);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateDate(Date date, int id){
        String sql = "UPDATE guests SET birthDate = ? WHERE id = ? LIMIT 1";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setDate(1, (java.sql.Date) new Date(date.getTime()));
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
