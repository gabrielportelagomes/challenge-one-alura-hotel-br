package DAO;

import models.Bookings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingsDAO {

    private Connection connection;

    public BookingsDAO(Connection connection){
        this.connection=connection;
    }

    public void create(Bookings booking){
        String sql = "INSERT INTO bookings (entryDate, exitDate, amount, payment) VALUES (?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setDate(1, new Date(booking.getEntryDate().getTime()));
            preparedStatement.setDate(2, new Date(booking.getExitDate().getTime()));
            preparedStatement.setDouble(3, booking.getAmount());
            preparedStatement.setString(4, booking.getPayment());
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                while(resultSet.next()){
                    booking.setId(resultSet.getInt(1));
                }
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Bookings> find(){
        List<Bookings> bookings = new ArrayList<Bookings>();
        String sql = "SELECT * FROM bookings";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getResultSet()){
                while(resultSet.next()){
                    Bookings booking = new Bookings(resultSet.getInt(1), resultSet.getDate(2), resultSet.getDate(3), resultSet.getDouble(4), resultSet.getString(5));
                    bookings.add(booking);
                }
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return bookings;
    }

    public void update(String amount, Integer id){
        String sql = "UPDATE bookings SET payment = ? WHERE id = ? LIMIT 1";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, amount);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(String column, Date date, Integer id){
        String sql = "UPDATE bookings SET " + column + " = ? WHERE id = ? LIMIT 1";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setDate(1, new Date(date.getTime()));
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(Double amount, Integer id){
        String sql = "UPDATE bookings SET amount = ? WHERE id = ? LIMIT 1";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setDouble(1, amount);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
