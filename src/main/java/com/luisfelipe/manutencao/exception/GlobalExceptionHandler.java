package com.luisfelipe.manutencao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EquipamentoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String tratarEquipamentoNaoEncontrado(EquipamentoNaoEncontradoException exception){
        return exception.getMessage();
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String tratarClienteNaoEcontrado(ClienteNaoEncontradoException exception){
        return exception.getMessage();
    }

    @ExceptionHandler(ManutencaoNaoEncontrada.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String tratarManutencaoNaoEcontrado(ManutencaoNaoEncontrada exception){
        return exception.getMessage();
    }

}
