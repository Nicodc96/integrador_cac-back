package com.tp.cac.controller;

import com.tp.cac.model.dto.OradorDto;
import com.tp.cac.model.entity.Orador;
import com.tp.cac.model.payload.MessageResponse;
import com.tp.cac.service.IOradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class OradorController {
    @Autowired
    private IOradorService oradorService;

    @GetMapping("")
    public ResponseEntity<?> index(){
        return new ResponseEntity<>(MessageResponse.builder()
                .mensaje("API Rest Java w/ Spring Framework - Grupo 2 CaC Java")
                .listaObjetos(new ArrayList<>())
                .build(), HttpStatus.OK);
    }

    @GetMapping("orador/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
        Orador orador = oradorService.findById(id);
        ArrayList<OradorDto> listReturn = new ArrayList<>();

        if(orador == null){
            return new ResponseEntity<>(MessageResponse.builder()
                    .mensaje("No se ha encontrado el registro solicitado.")
                    .listaObjetos(new ArrayList<>())
                    .build(), HttpStatus.NOT_FOUND);
        }
        listReturn.add(OradorDto.builder()
                .id_orador(orador.getId_orador())
                .nombre(orador.getNombre())
                .apellido(orador.getApellido())
                .email(orador.getEmail())
                .tema(orador.getTema())
                .fecha_alta(orador.getFecha_alta())
                .build());
        return new ResponseEntity<>(MessageResponse.builder()
                .mensaje("Mostrando información para ID: " + orador.getId_orador())
                .listaObjetos(listReturn)
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("oradores")
    public ResponseEntity<?> showAll(){
        try{
            ArrayList<OradorDto> listReturn = oradorService.findAll();

            return new ResponseEntity<>(MessageResponse.builder()
                    .mensaje("Registros de oradores")
                    .listaObjetos(listReturn)
                    .build(), HttpStatus.OK);
        } catch (DataAccessException dataAccessException){
            return new ResponseEntity<>(MessageResponse.builder()
                    .mensaje("Ha ocurrido un error: " + dataAccessException.getMessage())
                    .listaObjetos(new ArrayList<>())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("orador")
    public ResponseEntity<?> create(@RequestBody OradorDto oradorDto){
        Orador oradorSave = null;
        try{
            oradorSave = oradorService.save(oradorDto);
            oradorDto = OradorDto.builder()
                    .id_orador(oradorSave.getId_orador())
                    .nombre(oradorSave.getNombre())
                    .apellido(oradorSave.getApellido())
                    .email(oradorSave.getEmail())
                    .tema(oradorSave.getTema())
                    .fecha_alta(oradorSave.getFecha_alta())
                    .build();

            ArrayList<OradorDto> listReturn = new ArrayList<>();
            listReturn.add(oradorDto);
            return new ResponseEntity<>(MessageResponse.builder()
                    .mensaje("Se ha guardado el orador correctamente.")
                    .listaObjetos(listReturn)
                    .build(), HttpStatus.CREATED);
        }catch(DataAccessException dataAccessException){
            return new ResponseEntity<>(MessageResponse.builder()
                    .mensaje("Ha ocurrido un error: " + dataAccessException.getMessage())
                    .listaObjetos(new ArrayList<>())
                    .build(), HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("orador/{id}")
    public ResponseEntity<?> update(@RequestBody OradorDto oradorDto, @PathVariable Integer id){
        Orador oradorUpdate = null;
        try{
            if (oradorService.existsById(id)){
                oradorDto.setId_orador(id);
                oradorUpdate = oradorService.save(oradorDto);
                oradorDto = OradorDto.builder()
                        .id_orador(oradorUpdate.getId_orador())
                        .nombre(oradorUpdate.getNombre())
                        .apellido(oradorUpdate.getApellido())
                        .email(oradorUpdate.getEmail())
                        .tema(oradorUpdate.getTema())
                        .fecha_alta(oradorUpdate.getFecha_alta())
                        .build();
                
                ArrayList<OradorDto> listReturn = new ArrayList<>();
                listReturn.add(oradorDto);

                return new ResponseEntity<>(MessageResponse.builder()
                        .mensaje("Registro actualizado correctamente.")
                        .listaObjetos(listReturn)
                        .build(), HttpStatus.OK);
            } else{
                return new ResponseEntity<>(MessageResponse.builder()
                        .mensaje("Registro a actualizar no encontrado.")
                        .listaObjetos(new ArrayList<>())
                        .build(), HttpStatus.NOT_FOUND);
            }
        }catch(DataAccessException dataAccessException){
            return new ResponseEntity<>(MessageResponse.builder()
                    .mensaje(dataAccessException.getMessage())
                    .listaObjetos(new ArrayList<>())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
