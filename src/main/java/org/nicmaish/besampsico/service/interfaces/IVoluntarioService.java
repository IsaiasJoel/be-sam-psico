package org.nicmaish.besampsico.service.interfaces;

import org.nicmaish.besampsico.model.entity.Voluntario;

public interface IVoluntarioService {
	Voluntario buscarPorCorreo(String correo);

}
