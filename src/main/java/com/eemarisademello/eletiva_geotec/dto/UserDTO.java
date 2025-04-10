package com.eemarisademello.eletiva_geotec.dto;

import com.eemarisademello.eletiva_geotec.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long userId;
    private String userName;
    private String userPhoneNumber;
    private String userEmail;
    private LocalDateTime signUpDate;

    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setUserPhoneNumber(user.getUserPhoneNumber());
        userDTO.setUserEmail(user.getUserEmail());
        userDTO.setSignUpDate(user.getSignUpDate());

        return userDTO;
    }
}
