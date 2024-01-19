package org.nicmaish.besampsico.repo;

import org.nicmaish.besampsico.model.entity.Ubigeo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUbigeoRepo extends JpaRepository<Ubigeo, Integer>{
    @Query("select distinct u from Ubigeo u where u.habilitado=true AND u.eliminado=false")
    List<Ubigeo> listarUbigeos();

    @Query("select distinct u.departamento from Ubigeo u where u.habilitado=true AND u.eliminado=false")
    List<String> listarDepartamentos();

    @Query("select distinct u.provincia from Ubigeo u where u.habilitado=true AND u.eliminado=false " +
            "AND u.departamento=:departamento")
    List<String> listarProvinciaPorDepartamento(@Param("departamento") String departamento);

    @Query("select distinct u.distrito from Ubigeo u where u.habilitado=true AND u.eliminado=false " +
            "AND u.provincia=:provincia")
    List<String> listarDistritoPorProvincia(@Param("provincia") String provincia);

    @Query("from Ubigeo u where u.habilitado=true AND u.eliminado=false AND u.codigo=:codigoUbigeo")
    Ubigeo buscarUbigeoPorCodigo(@Param("codigoUbigeo") String codigoUbigeo);

    @Query("from Ubigeo u where u.habilitado=true AND u.eliminado=false AND u.departamento=:departamento AND u.provincia=:provincia AND u.distrito=:distrito")
    Ubigeo buscarCodigoUbigeoPorDepartamentoProvinciaDistrito(
            @Param("departamento") String departamento,
            @Param("provincia") String provincia,
            @Param("distrito") String distrito
    );
}
