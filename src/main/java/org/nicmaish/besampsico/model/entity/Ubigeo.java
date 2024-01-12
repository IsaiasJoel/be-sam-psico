package org.nicmaish.besampsico.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "ubigeo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ubigeo {

	@Id
	@Column(name="ubig_codigo")
	@NotNull
	@Size(max = 6, min = 6)
	private String codigo;

	@Column(name="ubig_departamento")
	@NotNull
	@Size(max = 130)
	private String departamento;

	@Column(name="ubig_provincia")
	@NotNull
	@Size(max = 130)
	private String provincia;

	@Column(name="ubig_distrito")
	@NotNull
	@Size(max = 130)
	private String distrito;

	@Column(name="ubig_eliminado")
	@NotNull
	private boolean eliminado;

	@Column(name="ubig_habilitado")
	@NotNull
	private boolean habilitado;
}
