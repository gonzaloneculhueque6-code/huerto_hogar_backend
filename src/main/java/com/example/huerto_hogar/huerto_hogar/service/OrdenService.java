package com.example.huerto_hogar.huerto_hogar.service;

import com.example.huerto_hogar.huerto_hogar.model.*;
import com.example.huerto_hogar.huerto_hogar.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List; 

@Service
public class OrdenService {
    @Autowired private OrdenRepository ordenRepo;
    @Autowired private ProductoRepository prodRepo;
    @Autowired private UsuarioRepository userRepo;
    @Autowired private DetalleOrdenRepository detalleRepo;

    @Transactional
    public Orden procesarCompra(SolicitudCompra sol) {
        //Buscar usuario
        Usuario u = userRepo.findById(sol.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Error: El usuario (ID " + sol.getIdUsuario() + ") no existe."));
        
        Orden orden = new Orden();
        orden.setUsuario(u);
        orden.setTotal(BigDecimal.valueOf(sol.getTotal()));
        orden.setEstado("Pendiente");
        orden.setDireccionEnvio(sol.getCalle());
        orden.setComunaEnvio(sol.getComuna());
        orden.setRegionEnvio(sol.getRegion());
        orden.setIndicaciones(sol.getIndicaciones());
        
        Orden guardada = ordenRepo.save(orden);

        for (SolicitudCompra.ItemCompra item : sol.getItems()) {
            Producto p = prodRepo.findById(item.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Error: El producto ID '" + item.getIdProducto() + "' no existe. Vacíe el carrito."));
            
            if (p.getStock() < item.getCantidad()) {
                throw new RuntimeException("Error: Stock insuficiente para " + p.getNombre());
            }
            p.setStock(p.getStock() - item.getCantidad());
            prodRepo.save(p);

            DetalleOrden detalle = new DetalleOrden();
            detalle.setOrden(guardada);
            detalle.setProducto(p);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(BigDecimal.valueOf(item.getPrecio()));
            detalleRepo.save(detalle);
        }
        return guardada;
    }

    //Lista las ordenes
    public List<Orden> listarTodas() {
        return ordenRepo.findAll();
    }
    public Orden cambiarEstado(Long idOrden, String nuevoEstado) {
        Orden orden = ordenRepo.findById(idOrden)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        
        orden.setEstado(nuevoEstado);
        return ordenRepo.save(orden);
    }
}