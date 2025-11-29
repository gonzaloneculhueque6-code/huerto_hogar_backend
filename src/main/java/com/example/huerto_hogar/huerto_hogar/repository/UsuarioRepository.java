package com.example.huerto_hogar.huerto_hogar.repository;

import com.example.huerto_hogar.huerto_hogar.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Busca usuario por correo
    // Es vital para el login
    Optional<Usuario> findByCorreo(String correo);
    
    // Verifica si existe el correo 
    // Es vital para el registro
    boolean existsByCorreo(String correo);
}