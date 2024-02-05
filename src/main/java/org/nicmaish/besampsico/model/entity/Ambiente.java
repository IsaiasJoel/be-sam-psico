package org.nicmaish.besampsico.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ambiente")
@Getter
@Setter
public class Ambiente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String ubicacion;
    private Integer aforo;
    private boolean disponible;
    private boolean habilitado;
    private String descripcion;
}