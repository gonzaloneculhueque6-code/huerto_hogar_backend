package com.example.huerto_hogar.huerto_hogar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "mensajes_contacto")
@Getter @Setter
public class MensajeContacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String mensaje;
    
    private LocalDateTime fechaEnvio;

    @PrePersist 
    public void asignarFecha() {
        this.fechaEnvio = LocalDateTime.now();
    }
}