package com.eemarisademello.eletiva_geotec.converter;

import com.eemarisademello.eletiva_geotec.model.User;
import com.eemarisademello.eletiva_geotec_client.dto.UserDTO;


public class DTOConverter {

    public static UserDTO userToDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setUserPhoneNumber(user.getUserPhoneNumber());
        userDTO.setUserEmail(user.getUserEmail());
        userDTO.setSignUpDate(user.getSignUpDate());

        return userDTO;

    }

}
