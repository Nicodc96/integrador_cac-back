package com.tp.cac.service.impl;

import com.tp.cac.model.dao.OradorDao;
import com.tp.cac.model.dto.OradorDto;
import com.tp.cac.model.entity.Orador;
import com.tp.cac.service.IOradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class OradorImplementService implements IOradorService {

    @Autowired
    private OradorDao oradorDao;
    @Transactional
    @Override
    public Orador save(OradorDto oradorDto) {
        Orador orador = Orador.builder()
                .id_orador(oradorDto.getId_orador())
                .nombre(oradorDto.getNombre())
                .apellido(oradorDto.getApellido())
                .email(oradorDto.getEmail())
                .tema(oradorDto.getTema())
                .fecha_alta(oradorDto.getFecha_alta())
                .build();
        return oradorDao.save(orador);
    }
    @Transactional(readOnly = true)
    @Override
    public Orador findById(Integer id) {
        return oradorDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Orador oradorDto) {
        oradorDao.delete(oradorDto);
    }

    @Override
    public boolean existsById(Integer id) {
        return oradorDao.existsById(id);
    }

    @Override
    public ArrayList<OradorDto> findAll() {
        ArrayList<OradorDto> completeList = new ArrayList<OradorDto>();
        oradorDao.findAll().forEach((oradordto) -> {
            completeList.add(OradorDto.builder()
                    .id_orador(oradordto.getId_orador())
                    .nombre(oradordto.getNombre())
                    .apellido(oradordto.getApellido())
                    .email(oradordto.getEmail())
                    .tema(oradordto.getTema())
                    .fecha_alta(oradordto.getFecha_alta())
                    .build());
        } );
        return completeList;
    }
}
