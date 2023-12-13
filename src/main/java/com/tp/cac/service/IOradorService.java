package com.tp.cac.service;

import com.tp.cac.model.dto.OradorDto;
import com.tp.cac.model.entity.Orador;

import java.util.ArrayList;

public interface IOradorService {
    Orador save(OradorDto oradorDto);
    Orador findById(Integer id);
    void delete(Orador oradorDto);
    boolean existsById(Integer id);
    ArrayList<OradorDto> findAll();
}
