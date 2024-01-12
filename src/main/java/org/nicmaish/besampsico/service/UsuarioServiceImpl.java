package org.nicmaish.besampsico.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioEncontrado;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioSesion;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioListar;
import org.nicmaish.besampsico.model.entity.Usuario;
import org.nicmaish.besampsico.model.mapper.UsuarioMapper;
import org.nicmaish.besampsico.repo.IUsuarioRepo;
import org.nicmaish.besampsico.service.interfaces.IUsuarioService;
import org.nicmaish.besampsico.utils.TextoUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.nicmaish.besampsico.model.mapper.UsuarioMapper.convertirEntityADtoUsuarioEncontrado;
import static org.nicmaish.besampsico.model.mapper.UsuarioMapper.convertirListaEntityAListaDTOUsuarioListar;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    //=============================================================
    // Dependencias
    //=============================================================
    private final IUsuarioRepo repo;
    private final PasswordEncoder passwordEncoder;

    //=============================================================
    // Métodos públicos
    //=============================================================
    @Override
    public DTOUsuarioSesion buscarPorCorreo(String correo) {
        Usuario usuario = repo.findByCorreo(correo);
        return UsuarioMapper.convertirEntityADtoUsuarioSesion(usuario);
    }

    @Override
    public DTOUsuarioEncontrado buscarPorId(Integer id) {
        Usuario encontrado = repo.findById(id).orElseThrow();
        return UsuarioMapper.convertirEntityADtoUsuarioEncontrado(encontrado);
    }

    @Override
    public DTOUsuarioEncontrado buscarPorDni(String dni) {
        Usuario usuarioEncontrado = repo.findByDni(dni);
        return convertirEntityADtoUsuarioEncontrado(usuarioEncontrado);
    }

    @Override
    public Page<DTOUsuarioListar> listarPaginacion(Pageable pageable, Map<String, Object> filtros) {

        List<Usuario> listaEntity = aplicarFiltros(filtros).toList();

        //tamaño de la lista filtrada
        long total = listaEntity.size();

        List<DTOUsuarioListar> dtoList = listaEntity.stream()
                .skip(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .map(UsuarioMapper::convertirEntityADtoUsuarioListar)
                .toList();

        //armar la paginación
        return new PageImpl<>(dtoList, pageable, total);
    }

    @Override
    @Transactional
    public void crear(DTOUsuarioCrearEditarRequest dto) {
        Usuario entity = UsuarioMapper.convertirDtoAEntity(dto);
        final String contraseniaActual = TextoUtils.obtenerUsuarioDesdeCorreo(entity.getCorreo());
        final String contraseniaEncriptada = passwordEncoder.encode(contraseniaActual);
        entity.setContrasenia(contraseniaEncriptada);
        repo.save(entity);
    }

    @Override
    @Transactional
    public void editar(Integer id, DTOUsuarioCrearEditarRequest dto) {
        Usuario entity = repo.findById(id).orElseThrow();

        entity.setCorreo(dto.getCorreo());
        entity.setApPaterno(dto.getApPaterno());
        entity.setApMaterno(dto.getApMaterno());
        entity.setNombres(dto.getNombres());
        entity.setDni(dto.getDni());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        entity.setSexo(dto.getSexo());
        entity.setCelular(dto.getCelular());
        entity.setNacionalidad(dto.getNacionalidad());
        entity.setCarrera(dto.getCarrera());
        entity.setEspecialidad(dto.getEspecialidad());
        entity.setUniversidad(dto.getUniversidad());
        entity.setAnioEgreso(dto.getAnioEgreso());
        entity.setColegiado(dto.isColegiado());
        entity.setNumeroColegiatura(dto.getNumeroColegiatura());
        entity.setResumenProfesional(dto.getResumenProfesional());

        repo.save(entity);
    }

    @Override
    public void habilitar(Integer id, String tipo) {
        Usuario entity = repo.findById(id).orElseThrow();
        final boolean activar = tipo.equals("habilitar"); //habilitar | deshabilitar
        entity.setHabilitado(activar);
        repo.save(entity);
    }

    //=============================================================
    // Métodos privados
    //=============================================================
    private Stream<Usuario> aplicarFiltros(Map<String, Object> filtros) {
        //Obtener los parámetros
        String filtroDni = filtros.get("filtroDni") == null ? null : String.valueOf(filtros.get("filtroDni"));
        String filtroNombres = filtros.get("filtroNombres") == null ? null : String.valueOf(filtros.get("filtroNombres")).toLowerCase();
        Boolean filtroEstado = filtros.get("filtroEstado") == null ? null : Boolean.parseBoolean(String.valueOf(filtros.get("filtroEstado")));

        //filtrar los datos
        return repo.findAll().stream()
                .filter(x -> filtroDni == null || x.getDni().equals(filtroDni))
                .filter(x -> filtroNombres == null ||
                        x.getApPaterno().toLowerCase().contains(filtroNombres) ||
                        x.getApMaterno().toLowerCase().contains(filtroNombres) ||
                        x.getNombres().toLowerCase().contains(filtroNombres))
                .filter(x -> filtroEstado == null || x.isHabilitado() == filtroEstado);
    }
}
