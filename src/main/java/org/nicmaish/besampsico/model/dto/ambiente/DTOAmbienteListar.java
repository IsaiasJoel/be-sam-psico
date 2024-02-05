package org.nicmaish.besampsico.model.dto.ambiente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOAmbienteListar {
    private Integer id;
    private String nombre;
    private String ubicacion;
    private Integer aforo;
    private boolean disponible;
    private String descripcion;
    private boolean habilitado;
}
