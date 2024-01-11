package org.nicmaish.besampsico.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "menu")
@Getter
@Setter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "men_id")
    private Integer id;

    @Column(name = "numeroOrden")
    private Integer numeroOrden;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "icono")
    private String icono;

    @Column(name = "url")
    private String url;

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "activo")
    private Boolean activo;
}
