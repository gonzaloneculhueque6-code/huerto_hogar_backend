package com.example.huerto_hogar.huerto_hogar.service;

import com.example.huerto_hogar.huerto_hogar.model.*;
import com.example.huerto_hogar.huerto_hogar.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {
    @Autowired private ProductoRepository repo;
    @Autowired private CategoriaRepository catRepo;

    public List<Producto> listar() { return repo.findAll(); }
    
    public Producto guardar(Producto p, String nombreCategoria) {
        // Busca categoría por nombre, si no existe la crea
        Categoria cat = catRepo.findAll().stream()
             .filter(c -> c.getNombre().equalsIgnoreCase(nombreCategoria))
             .findFirst()
             .orElseGet(() -> catRepo.save(new Categoria(null, nombreCategoria)));
        p.setCategoria(cat);
        return repo.save(p);
    }
    
    public void eliminar(String id) { repo.deleteById(id); }
}