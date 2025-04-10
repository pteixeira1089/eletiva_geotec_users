package com.eemarisademello.eletiva_geotec.model;

import jakarta.persistence.*;
import lombok.*;

import com.eemarisademello.eletiva_geotec.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_phone_number")
    private String userPhoneNumber;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "sign_up_date")
    private LocalDateTime signUpDate;

    public static User convert(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setUserPhoneNumber(userDTO.getUserPhoneNumber());
        user.setUserEmail(userDTO.getUserEmail());
        user.setSignUpDate(userDTO.getSignUpDate());

        return user;
    }
}
