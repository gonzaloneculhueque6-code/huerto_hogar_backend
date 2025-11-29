package com.example.huerto_hogar.huerto_hogar.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Categorias")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
}