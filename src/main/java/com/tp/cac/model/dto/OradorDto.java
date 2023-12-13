package com.tp.cac.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@ToString
@Builder
public class OradorDto implements Serializable {
    private Integer id_orador;
    private String nombre;
    private String apellido;
    private String email;
    private String tema;
    private Timestamp fecha_alta;
}
