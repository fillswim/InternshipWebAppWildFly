package org.example.service;

import org.example.dto.UserDTO;
import org.example.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserDTO> getAllUserDTOS();

    User findUserByUsername(String username);

    boolean saveUser(UserDTO userDTO);

}
