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

    //Login
    public Usuario login(String correo, String password) {
        Optional<Usuario> usuarioOpt = repo.findByCorreo(correo);
        
        if (usuarioOpt.isPresent()) {
            Usuario u = usuarioOpt.get();
            // Verifica que la contraseña no sea nula en la BD y que coincida
            if (u.getPassword() != null && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    // Guardar nuevo usuario
   public Usuario guardar(Usuario u) {
        // Verificar correo duplicado
        if (repo.existsByCorreo(u.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        // Lógica para obtener el nombre del rol desde el objeto u
        String nombreRol = "CLIENTE"; // Por defecto
        
        // Si el frontend envió un rol (ej: { nombre: "VENDEDOR" })
        if (u.getRol() != null && u.getRol().getNombre() != null) {
            nombreRol = u.getRol().getNombre();
        }

        String finalRolName = nombreRol;
        Rol rolReal = rolRepo.findAll().stream()
                .filter(x -> x.getNombre().equalsIgnoreCase(finalRolName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error: El rol " + finalRolName + " no existe en la BD."));
        
        u.setRol(rolReal);
        return repo.save(u);
    }

    public Usuario actualizar(Long id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Validación de correo
        if (!usuarioExistente.getCorreo().equalsIgnoreCase(usuarioActualizado.getCorreo())) {
            if (repo.existsByCorreo(usuarioActualizado.getCorreo())) {
                throw new RuntimeException("El correo ya está en uso por otro usuario.");
            }
        }

        // Actualizamos datos básicos
        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setApellido(usuarioActualizado.getApellido());
        usuarioExistente.setRut(usuarioActualizado.getRut());
        usuarioExistente.setTelefono(usuarioActualizado.getTelefono());
        usuarioExistente.setDireccion(usuarioActualizado.getDireccion());
        usuarioExistente.setRegion(usuarioActualizado.getRegion());
        usuarioExistente.setComuna(usuarioActualizado.getComuna());
        usuarioExistente.setCorreo(usuarioActualizado.getCorreo());

        if (usuarioActualizado.getRol() != null) {
            String nombreRol = usuarioActualizado.getRol().getNombre();
        
            Rol rolReal = rolRepo.findAll().stream()
                    .filter(r -> r.getNombre().equalsIgnoreCase(nombreRol))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("El rol " + nombreRol + " no existe."));
            
            usuarioExistente.setRol(rolReal);
        }

        if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isEmpty()) {
            usuarioExistente.setPassword(usuarioActualizado.getPassword());
        }

        return repo.save(usuarioExistente);
    }


    public List<Usuario> listar() { return repo.findAll(); }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}