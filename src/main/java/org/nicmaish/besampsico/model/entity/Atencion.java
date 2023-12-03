package org.nicmaish.besampsico.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "atencion")
public class Atencion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
}
