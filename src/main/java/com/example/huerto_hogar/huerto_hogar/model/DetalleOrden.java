package com.example.huerto_hogar.huerto_hogar.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_ordenes")
@Getter @Setter
public class DetalleOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    private Integer cantidad;
    private BigDecimal precioUnitario;

    @ManyToOne
    @JoinColumn(name = "id_orden")
    @JsonIgnore  
    private Orden orden;;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;
}