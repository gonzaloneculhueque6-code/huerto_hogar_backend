package com.example.huerto_hogar.huerto_hogar.controller;

import com.example.huerto_hogar.huerto_hogar.model.Orden;
import com.example.huerto_hogar.huerto_hogar.model.SolicitudCompra;
import com.example.huerto_hogar.huerto_hogar.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List; 

@RestController
@RequestMapping("/api/v1/ordenes")
@CrossOrigin(origins = "*") 
public class OrdenController {

    @Autowired
    private OrdenService service;

    // GET /api/ordenes
    @GetMapping
    public List<Orden> listarTodas() {
        return service.listarTodas();
    }

    @PostMapping("/comprar")
    public ResponseEntity<?> comprar(@RequestBody SolicitudCompra solicitud) {
        return ResponseEntity.ok(service.procesarCompra(solicitud));
    }
    // PATCH /api/ordenes/{id}/estado
    // Recibe el ID en la URL y el nuevo estado como parámetro
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Orden> actualizarEstado(
            @PathVariable Long id, 
            @RequestParam String nuevoEstado
    ) {
        Orden ordenActualizada = service.cambiarEstado(id, nuevoEstado);
        return ResponseEntity.ok(ordenActualizada);
    }
    
    
}