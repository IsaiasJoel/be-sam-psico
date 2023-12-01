package org.nicmaish.besampsico.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelNotFoundExceptionCustom extends RuntimeException{
    private String id;

    public ModelNotFoundExceptionCustom(String id, String message) {
        super(message);
        this.id=id;
    }
}