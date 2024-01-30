package org.nicmaish.besampsico.model.dto.paciente;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DTOPacienteListar {
    private Integer id;
    private String apPaterno;
    private String apMaterno;
    private String nombres;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    private String nacionalidad;
    private String dni;
    private String sexo;
    private String numeroContacto;
}
