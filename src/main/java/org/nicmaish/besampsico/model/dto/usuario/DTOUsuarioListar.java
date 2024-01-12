package org.nicmaish.besampsico.model.dto.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DTOUsuarioListar {
    private Integer id;
    private String apPaterno;
    private String apMaterno;
    private String nombres;
    private String carrera;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    private String especialidad;
    private Boolean habilitado;
    private Integer casosAsignados;
    private String dni;
}
