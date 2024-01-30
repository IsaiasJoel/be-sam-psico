package org.nicmaish.besampsico.repo;

import org.nicmaish.besampsico.model.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPacienteRepo extends JpaRepository<Paciente, Integer>{
    @Query("FROM Paciente where dni=:dni")
    Paciente findByDni(String dni);
}
