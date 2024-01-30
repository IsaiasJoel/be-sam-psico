package org.nicmaish.besampsico.service;

import lombok.RequiredArgsConstructor;
import org.nicmaish.besampsico.model.dto.paciente.DTOPacienteCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.paciente.DTOPacienteEncontrado;
import org.nicmaish.besampsico.model.dto.paciente.DTOPacienteListar;
import org.nicmaish.besampsico.model.entity.Paciente;
import org.nicmaish.besampsico.model.mapper.PacienteMapper;
import org.nicmaish.besampsico.repo.IPacienteRepo;
import org.nicmaish.besampsico.service.interfaces.IPacienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.nicmaish.besampsico.model.mapper.PacienteMapper.convertirEntityADtoPacienteEncontrado;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements IPacienteService {

    private final IPacienteRepo repo;

    @Override
    public Page<DTOPacienteListar> listarPaginacion(Pageable pageable, Map<String, Object> filtros) {
        List<Paciente> listaEntity = aplicarFiltros(filtros).toList();

        //tamaño de la lista filtrada
        long total = listaEntity.size();

        List<DTOPacienteListar> dtoList = listaEntity.stream()
                .skip(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .map(PacienteMapper::convertirEntityADtoPacienteListar)
                .toList();

        //armar la paginación
        return new PageImpl<>(dtoList, pageable, total);
    }

    @Override
    public void editar(Integer id, DTOPacienteCrearEditarRequest dto) {
        Paciente entity = repo.findById(id).orElseThrow();

        //Datos personales
        entity.setApPaterno(dto.getApPaterno());
        entity.setApMaterno(dto.getApMaterno());
        entity.setNombres(dto.getNombres());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        entity.setDni(dto.getDni());
        entity.setLugarNacimiento(dto.getLugarNacimiento());
        entity.setDireccion(dto.getDireccion());
        entity.setDepartamento(dto.getDepartamento());
        entity.setProvincia(dto.getProvincia());
        entity.setDistrito(dto.getDistrito());
        entity.setNumeroContacto(dto.getNumeroContacto());
        entity.setSexo(dto.getSexo());
        entity.setNacionalidad(dto.getNacionalidad());
        entity.setCorreo(dto.getCorreo());
        entity.setCarreraOProfesion(dto.getCarreraOProfesion());
        entity.setOcupacion(dto.getOcupacion());

        //Nivel socioeconómico
        entity.setTipoVivienda(dto.getTipoVivienda());
        entity.setHabitacionesOcamas(dto.getHabitacionesOcamas());
        entity.setServiciosBasicos(dto.getServiciosBasicos());
        entity.setGastosMensuales(dto.getGastosMensuales());
        entity.setInformacionGastoFamiliar(dto.getInformacionGastoFamiliar());
        entity.setTipoDeSeguro(dto.getTipoDeSeguro());
        entity.setCategorizacionSocioeconomica(dto.getCategorizacionSocioeconomica());

        //Familiar
        entity.setContactoEmergencia(dto.getContactoEmergencia());
        entity.setParentezcoContactoEmergencia(dto.getParentezcoContactoEmergencia());
        entity.setNumeroContactoEmergencia(dto.getNumeroContactoEmergencia());
    }

    @Override
    public DTOPacienteEncontrado buscarPorDni(String dni) {
        Paciente paciente = repo.findByDni(dni);
        return paciente == null ? null : convertirEntityADtoPacienteEncontrado(paciente);
    }

    @Override
    public void crear(DTOPacienteCrearEditarRequest dto) {
        Paciente entity = PacienteMapper.convertirDtoAEntity(dto);
        repo.save(entity);
    }

    @Override
    public DTOPacienteEncontrado buscarPorId(Integer id) {
        Paciente paciente = repo.findById(id).orElseThrow();
        return PacienteMapper.convertirEntityADtoPacienteEncontrado(paciente);
    }

    private Stream<Paciente> aplicarFiltros(Map<String, Object> filtros) {
        //Obtener los parámetros
        String filtroDni = filtros.get("filtroDni") == null ? null : String.valueOf(filtros.get("filtroDni"));
        String filtroNombres = filtros.get("filtroNombres") == null ? null : String.valueOf(filtros.get("filtroNombres")).toLowerCase();
        String filtroNacionalidad = filtros.get("filtroNacionalidad") == null ? null : String.valueOf(filtros.get("filtroNacionalidad")).toLowerCase();
        Boolean filtroEstado = filtros.get("filtroEstado") == null ? null : Boolean.parseBoolean(String.valueOf(filtros.get("filtroEstado")));

        //filtrar los datos
        return repo.findAll().stream()
                .filter(x -> filtroDni == null || x.getDni().equals(filtroDni))
                .filter(x -> filtroNombres == null ||
                        x.getApPaterno().toLowerCase().contains(filtroNombres) ||
                        x.getApMaterno().toLowerCase().contains(filtroNombres) ||
                        x.getNombres().toLowerCase().contains(filtroNombres))
                .filter(x -> filtroNacionalidad == null || x.getNacionalidad().equals(filtroNacionalidad))
                .filter(x -> filtroEstado == null || x.isHabilitado() == filtroEstado);
    }
}
