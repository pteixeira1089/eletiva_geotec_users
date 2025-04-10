package com.eemarisademello.eletiva_geotec.controller;

import com.eemarisademello.eletiva_geotec.dto.UserDTO;
import com.eemarisademello.eletiva_geotec.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<UserDTO> getUsers(){
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable Long userId){
        return userService.findById(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO newUser(@RequestBody UserDTO userDTO){
        return userService.save(userDTO);
    }

    @GetMapping("/{email}/email")
    public UserDTO getUserByEmail(@PathVariable String email){
        return userService.findByEmail(email);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId){
        userService.delete(userId);
    }

    //TODO: the method only searches for exact matches - make it possible to query using like method
    @GetMapping("/search")
    public List<UserDTO> queryByName(@RequestParam(name="userName", required=true) String userName){
        return userService.queryByUserName(userName);
    }

    @PatchMapping("/{userId}")
    public UserDTO editUser(@PathVariable Long userId, @RequestBody UserDTO userDTO){
        return userService.editUser(userId, userDTO);
    }

    @GetMapping("/pageable")
    public Page<UserDTO> getUsersPage(Pageable pageable){
        return userService.getAllPage(pageable);
    }
}
