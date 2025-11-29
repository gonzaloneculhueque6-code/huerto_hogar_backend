package com.example.huerto_hogar.huerto_hogar.controller;

import com.example.huerto_hogar.huerto_hogar.model.Usuario;
import com.example.huerto_hogar.huerto_hogar.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired private UsuarioService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        try {
            String correo = body.get("correo");
            String password = body.get("password");

            Usuario u = service.login(correo, password);
            
            if (u != null) {
                return ResponseEntity.ok(u);
            } else {
                return ResponseEntity.status(401).body("Credenciales incorrectas");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error en el servidor: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Map<String, Object> body) {
        try {
            System.out.println("Datos recibidos en Java: " + body);
            String nombreRolRecibido = (String) body.get("rol");
            System.out.println("Rol extraído: " + nombreRolRecibido);

            Usuario u = new Usuario();
            u.setNombre((String) body.get("nombre"));
            u.setApellido((String) body.get("apellido"));
            u.setRut((String) body.get("rut"));
            u.setCorreo((String) body.get("correo"));
            u.setPassword((String) body.get("password"));
            u.setDireccion((String) body.get("direccion"));
            u.setTelefono((String) body.get("telefono"));
            u.setRegion((String) body.get("region"));
            u.setComuna((String) body.get("comuna"));

            Usuario nuevoUsuario = service.guardar(u, nombreRolRecibido);
            return ResponseEntity.ok(nuevoUsuario);


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al registrar: " + e.getMessage());
        }
    }
    
    @GetMapping
    public java.util.List<Usuario> listar() { return service.listar(); }
}