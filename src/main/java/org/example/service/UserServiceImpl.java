package org.example.service;

import org.example.dao.UserDAO;
import org.example.dto.UserDTO;
import org.example.mappers.UserMapper;
import org.example.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    private final UserMapper userMapper;

    public UserServiceImpl(UserDAO userDAO,
                           UserMapper userMapper) {
        this.userDAO = userDAO;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getAllUserDTOS() {

        return userDAO.showAllUsers().stream()
                .map(userMapper::mapToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public User findUserByUsername(String username) {
        return userDAO.loadUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDAO.loadUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with name: " + username);
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                roles
        );

    }
}
