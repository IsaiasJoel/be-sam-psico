package org.nicmaish.besampsico.service;

import lombok.RequiredArgsConstructor;
import org.nicmaish.besampsico.model.dto.servicio.DTOServicioCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.servicio.DTOServicioListar;
import org.nicmaish.besampsico.model.entity.Servicio;
import org.nicmaish.besampsico.model.mapper.ServicioMapper;
import org.nicmaish.besampsico.repo.IServicioRepo;
import org.nicmaish.besampsico.service.interfaces.IServicioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioServiceImpl implements IServicioService {

    private final IServicioRepo repo;

    @Override
    public DTOServicioListar buscarPorId(Integer id) {
        Servicio entity = repo.findById(id).orElseThrow();
        return ServicioMapper.convertirEntityADtoUsuarioListar(entity);
    }

    @Override
    public List<DTOServicioListar> listarTodos() {
        List<Servicio> listaEnjtity = repo.findAll();
        return ServicioMapper.convertirListaEntityAListaDTOServicioListar(listaEnjtity);
    }

    @Override
    public void crear(DTOServicioCrearEditarRequest dto) {
        Servicio entity = ServicioMapper.convertirDTOAEntity(dto);
        repo.save(entity);
    }

    @Override
    public void editar(Integer id, DTOServicioCrearEditarRequest dto) {
        Servicio entity = repo.findById(id).orElseThrow();

        entity.setDescripcion(dto.getDescripcion());
        entity.setCosto(dto.getCosto());

        repo.save(entity);
    }

    @Override
    public void habilitar(Integer id, String tipo) {
        Servicio entity = repo.findById(id).orElseThrow();
        final boolean activar = tipo.equals("habilitar"); //habilitar | deshabilitar
        entity.setHabilitado(activar);
        repo.save(entity);
    }
}
