package org.nicmaish.besampsico.service.interfaces;

import org.nicmaish.besampsico.model.dto.ubigeo.DTOBuscarUbigeo;

import java.util.List;
import java.util.Map;

public interface IUbigeoService {
    List<DTOBuscarUbigeo> listarUbigeos();
    List<String> listarDepartamentos();
    List<String> listarProvinciaPorDepartamento(String departamento);
    List<String> listarDistritoPorProvincia(String provincia);
    DTOBuscarUbigeo buscarUbigeoPorCodigo(String codigoUbigeo);
    DTOBuscarUbigeo buscarCodigoUbigeoPorDepartamentoProvinciaDistrito(String departamento, String provincia, String distrito);
}
