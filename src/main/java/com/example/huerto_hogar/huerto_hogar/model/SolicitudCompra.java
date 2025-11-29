package com.example.huerto_hogar.huerto_hogar.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter @Setter
public class SolicitudCompra {
    private Long idUsuario;
    private Double total;
    
    private String calle;
    private String comuna;
    private String region;
    private String indicaciones;


    private List<ItemCompra> items;

    @Getter @Setter
    public static class ItemCompra {
        private String idProducto;
        private Integer cantidad;
        private Double precio;
    }
}