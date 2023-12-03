package org.nicmaish.besampsico.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "informeatencion")
public class InformeAtencion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
}
