package org.nicmaish.besampsico.model.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "voluntario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voluntario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "id")
	private String apPaterno;

	@Column(name = "id")
	private String apMaterno;

	@Column(name = "id")
	private String nombres;

	@Column(name = "id")
	private String dni;

	@Column(name = "id")
	private LocalDate fechaNacimiento;

	@Column(name = "id")
	private String sexo;

	@Column(name = "id")
	private String celular;

	@Column(name = "id")
	private String nacionalidad;

	@Column(name = "id")
	private String carrera;

	@Column(name = "id")
	private String especialidad;

	@Column(name = "id")
	private String universidad;

	@Column(name = "id")
	private String anioEgreso;

	@Column(name = "id")
	private boolean esColegiado;

	@Column(name = "id")
	private String numeroColegiatura;

	@Column(name = "id")
	private String resumenProfesional;

	@Column(name = "id")
	private String correo;

	@Column(name = "id")
	private String contrasenia;

	//el mapeo hace referencia desde la clase java, NO desde la tabla de la BD
	@OneToMany(mappedBy = "voluntario", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Rol> roles;
}
