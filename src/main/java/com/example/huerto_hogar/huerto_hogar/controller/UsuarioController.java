package com.example.huerto_hogar.huerto_hogar.controller;

import com.example.huerto_hogar.huerto_hogar.model.Usuario;
import com.example.huerto_hogar.huerto_hogar.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired private UsuarioService service;

    @Autowired
    private com.example.huerto_hogar.huerto_hogar.security.JwtUtil jwtUtil; 

    //Login (Genera el Token JWT)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String correo = credenciales.get("correo");
        String password = credenciales.get("password");

        // Lógica de validación normal
        Usuario usuario = service.login(correo, password);

        if (usuario != null) {
            // Aqui generamos el token JWT
            String token = jwtUtil.generateToken(usuario.getCorreo(), usuario.getRol().getNombre());
            
            // Preparamos la respuesta (Usuario + Token)
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("usuario", usuario);
            respuesta.put("token", token);
            
            return ResponseEntity.ok(respuesta);
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }


    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = service.guardar(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al registrar: " + e.getMessage());
        }
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {

            Usuario actualizado = service.actualizar(id, usuario);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    

    @GetMapping
    public java.util.List<Usuario> listar() { return service.listar(); }


    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}