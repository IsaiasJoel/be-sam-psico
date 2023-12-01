package org.nicmaish.besampsico.service;

import org.nicmaish.besampsico.model.entity.Voluntario;
import org.nicmaish.besampsico.repo.IVoluntarioRepo;
import org.nicmaish.besampsico.service.interfaces.IVoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoluntarioServiceImpl implements IVoluntarioService {
	
	@Autowired
	private IVoluntarioRepo repo;

	@Override
	public Voluntario buscarPorCorreo(String correo) {
		return repo.findByCorreo(correo);
	}

}
