package org.nicmaish.besampsico.config.apiresponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private LocalDateTime datetime;
    private Integer status;
    private boolean successful;
    private Map message;
    private T data;
}
