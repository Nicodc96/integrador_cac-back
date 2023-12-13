package com.tp.cac.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "oradores")
public class Orador {
    @Id
    @Column(name = "id_orador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_orador;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "email")
    private String email;
    @Column(name = "tema")
    private String tema;
    @Column(name = "fecha_alta")
    private Timestamp fecha_alta;
}
