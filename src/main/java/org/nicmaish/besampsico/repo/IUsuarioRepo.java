package org.nicmaish.besampsico.repo;

import org.nicmaish.besampsico.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioRepo extends JpaRepository<Usuario, Integer>{

	@Query("FROM Usuario where correo=:correo")
    Usuario findByCorreo(String correo);
}
