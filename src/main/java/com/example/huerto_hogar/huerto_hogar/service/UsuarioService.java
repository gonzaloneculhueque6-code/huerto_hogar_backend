package com.example.huerto_hogar.huerto_hogar.service;

import com.example.huerto_hogar.huerto_hogar.model.*;
import com.example.huerto_hogar.huerto_hogar.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired private UsuarioRepository repo;
    @Autowired private RolRepository rolRepo;

    public Usuario login(String correo, String password) {
        Optional<Usuario> usuarioOpt = repo.findByCorreo(correo);
        
        if (usuarioOpt.isPresent()) {
            Usuario u = usuarioOpt.get();
            // Verificar que la contraseña no sea nula en BD y que coincida
            if (u.getPassword() != null && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public Usuario guardar(Usuario u, String nombreRol) {
        if (u.getId() == null && repo.existsByCorreo(u.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        // Lógica de decisión
        String r = "CLIENTE"; 
        if (nombreRol != null) {
            if (nombreRol.equalsIgnoreCase("admin")) {
                r = "ADMIN";
            } else if (nombreRol.equalsIgnoreCase("vendedor")) {
                r = "VENDEDOR"; 
            }
        }

        // Busca rol en la base de datos
        String finalRolName = r;
        Rol rol = rolRepo.findAll().stream()
                .filter(x -> x.getNombre().equalsIgnoreCase(finalRolName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: El rol " + finalRolName + " no existe en la BD."));
        
        u.setRol(rol);
        return repo.save(u);
    }
    
    public List<Usuario> listar() { return repo.findAll(); }
}