package org.nicmaish.besampsico.model.dto.ambiente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DTOAmbienteCrearEditarRequest {
    private Integer id;
    private String nombre;
    private String ubicacion;
    private Integer aforo;
    private boolean disponible;
    private String descripcion;
    private boolean habilitado;
}
