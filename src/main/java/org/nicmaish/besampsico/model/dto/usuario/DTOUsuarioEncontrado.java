package org.nicmaish.besampsico.model.dto.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DTOUsuarioEncontrado {
    private Integer id;
    private String apPaterno;
    private String apMaterno;
    private String nombres;
    private String dni;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    private String sexo;
    private String celular;
    private String nacionalidad;
    private String carrera;
    private String especialidad;
    private String universidad;
    private String anioEgreso;
    private boolean colegiado;
    private String numeroColegiatura;
    private String resumenProfesional;
    private boolean habilitado;
    private String correo;
}
