package com.example.huerto_hogar.huerto_hogar.repository;

import com.example.huerto_hogar.huerto_hogar.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    // Buscar el rol por nombre "ADMIN" sin saber su ID
    Optional<Rol> findByNombre(String nombre);
}