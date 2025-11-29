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
        return guardarDesdeMapa(body);
    }
    
    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable String id, @RequestBody Map<String, Object> body) {
        body.put("id", id);
        return guardarDesdeMapa(body);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) { service.eliminar(id); }

    
    private Producto guardarDesdeMapa(Map<String, Object> body) {
        Producto p = new Producto();
        p.setId((String)body.get("id"));
        p.setNombre((String)body.get("name"));
        p.setDescripcion((String)body.get("description"));
        p.setStock(Integer.valueOf(body.get("stock").toString()));
        p.setStockCritico(Integer.valueOf(body.get("criticalStock").toString()));
        p.setPrecio(new BigDecimal(body.get("price").toString()));
        p.setImagenUrl((String)body.get("image"));
        return service.guardar(p, (String)body.get("category"));
    }
}