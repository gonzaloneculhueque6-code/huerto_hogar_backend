package com.example.huerto_hogar.huerto_hogar.repository;

import com.example.huerto_hogar.huerto_hogar.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {
    // Método para filtrar productos por categoría
    List<Producto> findByCategoria_Id(Long idCategoria);
}