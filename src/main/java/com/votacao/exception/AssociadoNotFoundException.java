package com.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AssociadoNotFoundException extends RuntimeException {

    public AssociadoNotFoundException(String message) {
        super(message);
    }
}
