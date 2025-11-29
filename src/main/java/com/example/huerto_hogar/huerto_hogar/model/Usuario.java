package com.example.huerto_hogar.huerto_hogar.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Usuarios")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Todo esto lo mapeamos en el controlador
    private String nombre;
    private String apellido; 
    private String rut;
    
    @Column(unique = true, nullable = false)
    private String correo;
    private String password; 
    private String direccion;
    private String telefono;
    private String region;
    private String comuna;
    // Carga el rol al cargar el usuario
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "id_rol")
    private Rol rol;
}