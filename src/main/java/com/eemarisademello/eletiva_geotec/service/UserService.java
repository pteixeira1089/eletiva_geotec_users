package com.eemarisademello.eletiva_geotec.service;

import com.eemarisademello.eletiva_geotec.converter.DTOConverter;
import com.eemarisademello.eletiva_geotec_client.dto.UserDTO;
import com.eemarisademello.eletiva_geotec_client.exception.UserNotFoundException;
import com.eemarisademello.eletiva_geotec.model.User;
import com.eemarisademello.eletiva_geotec.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> getAll(){
        List<User> users = userRepository.findAll();

        return users
                .stream()
                .map(DTOConverter::userToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findById(long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());
        return DTOConverter.userToDTO(user);
    }

    public List<UserDTO> queryByUserName(String userName){
        List<User> users = userRepository.queryByUserNameLike(userName);
        return users
                .stream()
                .map(DTOConverter::userToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findByEmail(String email){
        User user = userRepository.findByUserEmail(email);
        return DTOConverter.userToDTO(user);
    }

    public UserDTO save(UserDTO userDTO){
        userDTO.setSignUpDate(LocalDateTime.now());
        User user = userRepository.save(User.convert(userDTO));
        return DTOConverter.userToDTO(user);
    }

    public UserDTO delete(long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
        return DTOConverter.userToDTO(user);
    }

    public UserDTO editUser(long userId, UserDTO userDTO){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUserName(userDTO.getUserName());
        user.setUserPhoneNumber(userDTO.getUserPhoneNumber());
        user.setUserEmail(userDTO.getUserEmail());
        user = userRepository.save(user);

        return DTOConverter.userToDTO(user);
    }

    public Page<UserDTO> getAllPage(Pageable page){
        Page<User> users = userRepository.findAll(page);
        return users.map(DTOConverter::userToDTO);
    }
}
