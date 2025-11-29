package com.example.huerto_hogar.huerto_hogar.model;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Ordenes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;
    private BigDecimal total;
    // Pendiente, Enviado, Entregado, Cancelado
    private String estado; 

    // Datos de envío para la orden
    private String direccionEnvio;
    private String comunaEnvio;
    private String regionEnvio;
    private String indicaciones;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    private Set<DetalleOrden> detalles;

    @PrePersist
    protected void onCreate() { fecha = LocalDateTime.now(); }
}