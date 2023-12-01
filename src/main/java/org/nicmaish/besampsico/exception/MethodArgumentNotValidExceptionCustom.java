package org.nicmaish.besampsico.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MethodArgumentNotValidExceptionCustom extends RuntimeException {
    private String id;

    public MethodArgumentNotValidExceptionCustom(String id, String message) {
        super(message);
        this.id = id;
    }
}
