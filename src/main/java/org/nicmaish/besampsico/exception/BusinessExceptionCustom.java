package org.nicmaish.besampsico.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessExceptionCustom extends RuntimeException {
    private String error;
    private String detalle;

    public BusinessExceptionCustom(String error, String detalle) {
        this.error = error;
        this.detalle = detalle;
    }

}

