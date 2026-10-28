package com.example.PokemonAPI.exception;

import com.example.PokemonAPI.dto.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> manejarErroresDeValidacion(MethodArgumentNotValidException ex){
        List<String> detalles = new ArrayList<>();

        for(FieldError error: ex.getBindingResult().getFieldErrors()){
            detalles.add(error.getDefaultMessage());
        }
        ErrorDTO errorDTO = new ErrorDTO("Error en la validacion de datos enviados",detalles, LocalDateTime.now());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorDTO> manejarErroresDeNegocio(BusinessRuleException ex){
        List<String> detalles = new ArrayList<>();
        detalles.add(ex.getMessage());
        ErrorDTO errorDTO = new ErrorDTO("Error en la capa de negocio",detalles, LocalDateTime.now());
        return new ResponseEntity<>(errorDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> manejarErroresDeRecursos(ResourceNotFoundException ex){
        List<String> detalles = new ArrayList<>();
        detalles.add(ex.getMessage());
        ErrorDTO errorDTO = new ErrorDTO("El recurso no existe",detalles, LocalDateTime.now());
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> manejarErrorDeServidor(Exception ex){
        List<String> detalles = new ArrayList<>();
        detalles.add(ex.getMessage());
        ErrorDTO errorDTO = new ErrorDTO("El error esta entre la silla y el escritorio",detalles, LocalDateTime.now());
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
