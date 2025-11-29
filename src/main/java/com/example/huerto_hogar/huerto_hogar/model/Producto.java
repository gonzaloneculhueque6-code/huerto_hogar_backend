package com.example.huerto_hogar.huerto_hogar.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Productos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Producto {
    @Id
    private String id; 

    @JsonProperty("name") 
    private String nombre;

    @JsonProperty("description")
    private String descripcion;

    private Integer stock;

    @JsonProperty("criticalStock")
    @Column(name = "stock_critico")
    private Integer stockCritico;

    @JsonProperty("price")
    private BigDecimal precio;

    @JsonProperty("image")
    @Column(name = "imagen_url")
    private String imagenUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    
    @JsonProperty("category")
    public String getNombreCategoria() {
        return categoria != null ? categoria.getNombre() : "";
    }
}