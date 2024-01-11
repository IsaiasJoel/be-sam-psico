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
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usu_id")
	private Integer id;

	@Column(name = "correo")
	private String correo;

	@Column(name = "contrasenia")
	private String contrasenia;

	@Column(name = "apPaterno")
	private String apPaterno;

	@Column(name = "apMaterno")
	private String apMaterno;

	@Column(name = "nombres")
	private String nombres;

	@Column(name = "dni")
	private String dni;

	@Column(name = "fechaNacimiento")
	private LocalDate fechaNacimiento;

	@Column(name = "sexo")
	private String sexo;

	@Column(name = "celular")
	private String celular;

	@Column(name = "nacionalidad")
	private String nacionalidad;

	@Column(name = "carrera")
	private String carrera;

	@Column(name = "especialidad")
	private String especialidad;

	@Column(name = "universidad")
	private String universidad;

	@Column(name = "anioEgreso")
	private String anioEgreso;

	@Column(name = "colegiado")
	private boolean colegiado;

	@Column(name = "numeroColegiatura")
	private String numeroColegiatura;

	@Column(name = "resumenProfesional")
	private String resumenProfesional;

	@Column(name = "habilitado")
	private boolean habilitado;

	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private List<Rol> roles;
}
