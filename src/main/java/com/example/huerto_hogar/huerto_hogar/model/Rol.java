package com.example.huerto_hogar.huerto_hogar.model;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Roles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    // Admin, Cliente, Vendedor
    private String nombre; 
}