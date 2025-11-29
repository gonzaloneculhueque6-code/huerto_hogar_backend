package com.example.huerto_hogar.huerto_hogar.repository;

import com.example.huerto_hogar.huerto_hogar.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
    // Busca todas las compras de un usuario específico
    List<Orden> findByUsuario_Id(Long idUsuario);
}