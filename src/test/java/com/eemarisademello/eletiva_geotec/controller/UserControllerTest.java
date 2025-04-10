package com.eemarisademello.eletiva_geotec.controller;

import com.eemarisademello.eletiva_geotec.dto.UserDTO;
import com.eemarisademello.eletiva_geotec.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserDTO createUserDTO() {
        return new UserDTO(
                1L,
                "John Doe",
                "123456789",
                "johndoe@example.com",
                LocalDateTime.now()
        );
    }

    @Test
    void testGetUsers() throws Exception {
        List<UserDTO> users = Collections.singletonList(createUserDTO());
        Mockito.when(userService.getAll()).thenReturn(users);

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(1L))
                .andExpect(jsonPath("$[0].userName").value("John Doe"))
                .andExpect(jsonPath("$[0].userPhoneNumber").value("123456789"))
                .andExpect(jsonPath("$[0].userEmail").value("johndoe@example.com"));
    }

    @Test
    void testGetUserById() throws Exception {
        UserDTO user = createUserDTO();
        Mockito.when(userService.findById(1L)).thenReturn(user);

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.userName").value("John Doe"))
                .andExpect(jsonPath("$.userPhoneNumber").value("123456789"))
                .andExpect(jsonPath("$.userEmail").value("johndoe@example.com"));
    }

    @Test
    void testNewUser() throws Exception {
        UserDTO user = createUserDTO();
        Mockito.when(userService.save(Mockito.any(UserDTO.class))).thenReturn(user);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.userName").value("John Doe"))
                .andExpect(jsonPath("$.userPhoneNumber").value("123456789"))
                .andExpect(jsonPath("$.userEmail").value("johndoe@example.com"));
    }

    @Test
    void testGetUserByEmail() throws Exception {
        UserDTO user = createUserDTO();
        Mockito.when(userService.findByEmail("johndoe@example.com")).thenReturn(user);

        mockMvc.perform(get("/user/johndoe@example.com/email"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.userName").value("John Doe"))
                .andExpect(jsonPath("$.userPhoneNumber").value("123456789"))
                .andExpect(jsonPath("$.userEmail").value("johndoe@example.com"));
    }

    @Test
    void testDeleteUser() throws Exception {
        Mockito.doNothing().when(userService).delete(1L);

        mockMvc.perform(delete("/user/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testQueryByName() throws Exception {
        List<UserDTO> users = Collections.singletonList(createUserDTO());
        Mockito.when(userService.queryByUserName("John")).thenReturn(users);

        mockMvc.perform(get("/user/search").param("userName", "John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(1L))
                .andExpect(jsonPath("$[0].userName").value("John Doe"))
                .andExpect(jsonPath("$[0].userPhoneNumber").value("123456789"))
                .andExpect(jsonPath("$[0].userEmail").value("johndoe@example.com"));
    }

    @Test
    void testEditUser() throws Exception {
        UserDTO user = createUserDTO();
        Mockito.when(userService.editUser(Mockito.eq(1L), Mockito.any(UserDTO.class))).thenReturn(user);

        mockMvc.perform(patch("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.userName").value("John Doe"))
                .andExpect(jsonPath("$.userPhoneNumber").value("123456789"))
                .andExpect(jsonPath("$.userEmail").value("johndoe@example.com"));
    }

    @Test
    void testGetUsersPage() throws Exception {
        Page<UserDTO> page = new PageImpl<>(Collections.singletonList(createUserDTO()));
        Mockito.when(userService.getAllPage(PageRequest.of(0, 10))).thenReturn(page);

        mockMvc.perform(get("/user/pageable").param("page", "0").param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].userId").value(1L))
                .andExpect(jsonPath("$.content[0].userName").value("John Doe"))
                .andExpect(jsonPath("$.content[0].userPhoneNumber").value("123456789"))
                .andExpect(jsonPath("$.content[0].userEmail").value("johndoe@example.com"));
    }
}