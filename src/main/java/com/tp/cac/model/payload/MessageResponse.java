package com.tp.cac.model.payload;

import com.tp.cac.model.dto.OradorDto;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@ToString
@Builder
public class MessageResponse implements Serializable {
    private String mensaje;
    private ArrayList<OradorDto> listaObjetos;
}
