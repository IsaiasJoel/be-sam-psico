package org.nicmaish.besampsico.model.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nicmaish.besampsico.model.dto.rol.DTORolUsuarioSesion;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DTOUsuarioSesion {
    private Integer id;
    private String apPaterno;
    private String apMaterno;
    private String nombres;
    private String dni;
    private String correo;
    private List<DTORolUsuarioSesion> roles;
}
