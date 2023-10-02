package Controllers;

import java.sql.*;

import DAO.UsersDAO;
import Factory.ConnectionFactory;
import models.Users;

public class UsersController {

    private UsersDAO usersDAO;

    public UsersController(){
        Connection connection = new ConnectionFactory().recoverConnection();
        this.usersDAO = new UsersDAO(connection);
    }

    public boolean checkPassword(Users user){
        if(user.getPassword().equals(usersDAO.getPassword(user))){
            return true;
        }
        return false;
    }
}
