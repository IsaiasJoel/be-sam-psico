package org.nicmaish.besampsico.repo;

import org.nicmaish.besampsico.model.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepo extends JpaRepository<Paciente, Integer>{

}
