package org.nicmaish.besampsico.service;

import lombok.RequiredArgsConstructor;
import org.nicmaish.besampsico.model.dto.ubigeo.DTOBuscarUbigeo;
import org.nicmaish.besampsico.model.entity.Ubigeo;
import org.nicmaish.besampsico.model.mapper.UbigeoMapper;
import org.nicmaish.besampsico.repo.IUbigeoRepo;
import org.nicmaish.besampsico.service.interfaces.IUbigeoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UbigeoServiceImpl implements IUbigeoService {

    //=======================================
    //Inyección de dependencias
    //=======================================
    private final IUbigeoRepo ubigeoRepo;

    //=======================================
    // Métodos públicos
    //=======================================

    @Override
    public List<DTOBuscarUbigeo> listarUbigeos() {
        List<Ubigeo> listaRepo = ubigeoRepo.listarUbigeos();

        return listaRepo.stream()
                .map(UbigeoMapper::covnertirEntityADto)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listarDepartamentos() {
        return ubigeoRepo.listarDepartamentos();
    }

    @Override
    public List<String> listarProvinciaPorDepartamento(String departamento) {
        return ubigeoRepo.listarProvinciaPorDepartamento(departamento);
    }

    @Override
    public List<String> listarDistritoPorProvincia(String provincia) {
        return ubigeoRepo.listarDistritoPorProvincia(provincia);
    }

    @Override
    public DTOBuscarUbigeo buscarUbigeoPorCodigo(String codigoUbigeo) {
        Ubigeo encontrado = ubigeoRepo.buscarUbigeoPorCodigo(codigoUbigeo);
        return UbigeoMapper.covnertirEntityADto(encontrado);
    }

    @Override
    public DTOBuscarUbigeo buscarCodigoUbigeoPorDepartamentoProvinciaDistrito(String departamento, String provincia, String distrito) {
        Ubigeo encontrado = ubigeoRepo.buscarCodigoUbigeoPorDepartamentoProvinciaDistrito(departamento, provincia, distrito);
        return UbigeoMapper.covnertirEntityADto(encontrado);
    }

    //=======================================
    // Métodos privados
    //=======================================

}
