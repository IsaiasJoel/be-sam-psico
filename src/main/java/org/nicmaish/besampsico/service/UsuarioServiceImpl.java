package org.nicmaish.besampsico.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioCrearEditarRequest;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioSesion;
import org.nicmaish.besampsico.model.dto.usuario.DTOUsuarioListar;
import org.nicmaish.besampsico.model.entity.Usuario;
import org.nicmaish.besampsico.model.mapper.UsuarioMapper;
import org.nicmaish.besampsico.repo.IUsuarioRepo;
import org.nicmaish.besampsico.service.interfaces.IUsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

import static org.nicmaish.besampsico.model.mapper.UsuarioMapper.convertirListaEntityAListaDTOUsuarioListar;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuarioRepo repo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public DTOUsuarioSesion buscarPorCorreo(String correo) {
        Usuario usuario = repo.findByCorreo(correo);
        return UsuarioMapper.convertirEntityADtoUsuarioSesion(usuario);
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public Page<DTOUsuarioListar> listarPaginacion(Pageable pageable, String filtro) {
        //filtrar los datos
        if (filtro == null) filtro = "";

        final String finalFiltro = filtro;
        Predicate<Usuario> apPaternoContains = x -> x.getApPaterno().toLowerCase().contains(finalFiltro.toLowerCase());
        Predicate<Usuario> apMaternoContains = x -> x.getApMaterno().toLowerCase().contains(finalFiltro.toLowerCase());
        Predicate<Usuario> nombresContains = x -> x.getNombres().toLowerCase().contains(finalFiltro.toLowerCase());

        List<Usuario> repoListResponse = repo.findAll().stream()
                .filter(x -> apPaternoContains.test(x) || apMaternoContains.test(x) || nombresContains.test(x))
                .filter(Usuario::isHabilitado)
                .skip(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .toList();

        //tamaño de la lista filtrada
        int total = repoListResponse.size();

        //convertir la lista a dto
        List<DTOUsuarioListar> dtoList = convertirListaEntityAListaDTOUsuarioListar(repoListResponse);

        //armar la paginación
        return new PageImpl<>(dtoList, pageable, total);
    }

    @Override
    @Transactional
    public Integer crear(DTOUsuarioCrearEditarRequest dto) {
        Usuario entity = UsuarioMapper.convertirDtoAEntity(dto);
        final String contraseniaActual = dto.getContrasenia();
        final String contraseniaEncriptada = passwordEncoder.encode(contraseniaActual);
        entity.setContrasenia(contraseniaEncriptada);
        return repo.save(entity).getId();
    }

    @Override
    @Transactional
    public void editar(Integer id, DTOUsuarioCrearEditarRequest dto) {
        Usuario entity = repo.findById(id).orElseThrow();

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
        final boolean activar = tipo.equals("S"); //S: SI | N: NO
        entity.setHabilitado(activar);
        repo.save(entity);
    }
}
