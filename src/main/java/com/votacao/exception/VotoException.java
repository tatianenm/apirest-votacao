package com.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_REQUIRED)
public class VotoException extends RuntimeException{

    public VotoException() {
        super("Voto repetido.");
    }
}
