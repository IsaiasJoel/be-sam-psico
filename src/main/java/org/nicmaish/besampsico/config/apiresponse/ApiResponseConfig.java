package org.nicmaish.besampsico.config.apiresponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Map;

public class ApiResponseConfig {
    public static ResponseEntity<Object> generarRespuesta(boolean exito, HttpStatus httpStatus, Map mensaje, Object data) {
        ApiResponse apiResponse = ApiResponse.builder()
                .datetime(LocalDateTime.now())
                .status(httpStatus.value())
                .successful(exito)
                .message(mensaje)
                .data(data)
                .build();

        return new ResponseEntity<>(apiResponse, httpStatus);

    }
}
