package org.nicmaish.besampsico.repo;

import org.nicmaish.besampsico.model.entity.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IVoluntarioRepo extends JpaRepository<Voluntario, Integer>{

	@Query("FROM Voluntario")
	Voluntario findByCorreo(String correo);
}
