package org.example.dao;

import org.example.models.User;
import org.springframework.ui.Model;

import java.util.List;

public interface UserDAO {

    List<User> showAllUsers();

    User loadUserByUsername(String username);

    void saveUser(User user);

}
