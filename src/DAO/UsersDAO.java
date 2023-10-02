package DAO;

import models.Users;

import java.sql.*;

public class UsersDAO {

    private Connection connection;

    public UsersDAO(Connection connection){
        this.connection=connection;
    }

    public String getPassword(Users usuario){
        String password = null;
        String sql = "SELECT password FROM users WHERE user = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, usuario.getUser());
            preparedStatement.execute();
            try(ResultSet resultSet = preparedStatement.getResultSet()){
                while(resultSet.next()){
                    password = resultSet.getString(1);
                    return password;
                }
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return password;
    }
}
