package com.eemarisademello.eletiva_geotec.exception.advice;

import com.eemarisademello.eletiva_geotec_client.dto.ErrorDTO;
import com.eemarisademello.eletiva_geotec_client.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice(basePackages="com.eemarisademello.eletiva_geotec.controller")
public class UserControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDTO handleUserNotFoundException(UserNotFoundException ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setMessage("Usuário não encontrado");
        errorDTO.setTimestamp(LocalDateTime.now());

        return errorDTO;
    }
}
