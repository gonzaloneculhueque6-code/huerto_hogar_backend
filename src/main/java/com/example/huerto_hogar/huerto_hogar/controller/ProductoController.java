package com.example.huerto_hogar.huerto_hogar.controller;

import com.example.huerto_hogar.huerto_hogar.model.Producto;
import com.example.huerto_hogar.huerto_hogar.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/productos")
@CrossOrigin(origins = "*")
public class ProductoController {
    
    @Autowired private ProductoService service;

    
    @GetMapping
    public java.util.List<Producto> listar() { return service.listar(); }

    
    @PostMapping
    public Producto crear(@RequestBody Map<String, Object> body) {
        return procesarDatos(body);
    }
    
    
    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable String id, @RequestBody Map<String, Object> body) {
        body.put("id", id);
        return procesarDatos(body);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) { service.eliminar(id); }


    private Producto procesarDatos(Map<String, Object> body) {
        Producto p = new Producto();
        
        if (body.get("id") != null) {
            p.setId(body.get("id").toString());
        }
        
        if (body.get("name") != null) p.setNombre((String)body.get("name"));
        if (body.get("description") != null) p.setDescripcion((String)body.get("description"));
        if (body.get("image") != null) p.setImagenUrl((String)body.get("image"));
        
        if (body.get("stock") != null) {
            p.setStock(Integer.valueOf(body.get("stock").toString()));
        }
        if (body.get("criticalStock") != null) {
            p.setStockCritico(Integer.valueOf(body.get("criticalStock").toString()));
        }
        if (body.get("price") != null) {
            p.setPrecio(new BigDecimal(body.get("price").toString()));
        }
        
        String categoriaNombre = (String)body.get("category");


        return service.guardar(p, categoriaNombre);
    }
}