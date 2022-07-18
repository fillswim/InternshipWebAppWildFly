package org.example.mappers;

import org.example.dto.UserDTO;
import org.example.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDTO mapToUserDTO(User user) {

        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().name())
                .build();
    }

}
