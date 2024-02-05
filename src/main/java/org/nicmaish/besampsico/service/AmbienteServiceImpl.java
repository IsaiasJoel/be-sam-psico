package org.nicmaish.besampsico.service;

import lombok.RequiredArgsConstructor;
import org.nicmaish.besampsico.model.dto.ambiente.DTOAmbienteCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.ambiente.DTOAmbienteListar;
import org.nicmaish.besampsico.model.entity.Ambiente;
import org.nicmaish.besampsico.model.mapper.AmbienteMapper;
import org.nicmaish.besampsico.repo.IAmbienteRepo;
import org.nicmaish.besampsico.service.interfaces.IAmbienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AmbienteServiceImpl implements IAmbienteService {

    private final IAmbienteRepo repo;

    @Override
    public DTOAmbienteListar buscarPorId(Integer id) {
        Ambiente entity = repo.findById(id).orElseThrow();
        return AmbienteMapper.convertirEntityADtoUsuarioListar(entity);
    }

    @Override
    public List<DTOAmbienteListar> listarTodos() {
        List<Ambiente> listaEnjtity = repo.findAll();
        return AmbienteMapper.convertirListaEntityAListaDTOAmbienteListar(listaEnjtity);
    }

    @Override
    public void crear(DTOAmbienteCrearEditarRequest dto) {
        Ambiente entity = AmbienteMapper.convertirDTOAEntity(dto);
        repo.save(entity);
    }

    @Override
    public void editar(Integer id, DTOAmbienteCrearEditarRequest dto) {
        Ambiente entity = repo.findById(id).orElseThrow();

        entity.setNombre(dto.getNombre());
        entity.setUbicacion(dto.getUbicacion());
        entity.setAforo(dto.getAforo());
        entity.setDescripcion(dto.getDescripcion());

        repo.save(entity);
    }

    @Override
    public void habilitar(Integer id, String tipo) {
        Ambiente entity = repo.findById(id).orElseThrow();
        final boolean activar = tipo.equals("habilitar"); //habilitar | deshabilitar
        entity.setHabilitado(activar);
        repo.save(entity);
    }
}
