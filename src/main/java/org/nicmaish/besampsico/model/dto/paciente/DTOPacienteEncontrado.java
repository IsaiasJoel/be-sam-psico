package org.nicmaish.besampsico.model.dto.paciente;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DTOPacienteEncontrado {
    private Integer id;

    //Datos personales
    private String apPaterno;
    private String apMaterno;
    private String nombres;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    private String dni;
    private String lugarNacimiento;
    private String direccion;
    private String departamento;
    private String provincia;
    private String distrito;
    private String numeroContacto;
    private String sexo;
    private String nacionalidad;
    private String correo;
    private String carreraOProfesion;
    private String ocupacion;

    //Nivel socioeconómico
    private String tipoVivienda;
    private String habitacionesOcamas;
    private String serviciosBásicos;
    private String gastosMensuales;
    private String informacionGastoFamiliar;
    private String tipoDeSeguro;
    private String categorizacionSocioeconomica;

    //Familiar
    private String contactoEmergencia;
    private String parentezcoContactoEmergencia;
    private String numeroContactoEmergencia;

    private boolean habilitado;
}
