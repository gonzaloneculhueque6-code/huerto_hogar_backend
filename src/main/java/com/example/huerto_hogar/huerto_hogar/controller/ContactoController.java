package com.example.huerto_hogar.huerto_hogar.controller;

import com.example.huerto_hogar.huerto_hogar.model.MensajeContacto;
import com.example.huerto_hogar.huerto_hogar.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contacto")
@CrossOrigin(origins = "*")
public class ContactoController {

    @Autowired
    private ContactoRepository repo;

    //Guardar mensaje 
    //Para el formulario público
    @PostMapping
    public MensajeContacto enviarMensaje(@RequestBody MensajeContacto mensaje) {
        return repo.save(mensaje);
    }

    // Listar mensajes en el admin
    @GetMapping
    public List<MensajeContacto> verMensajes() {
        return repo.findAll();
    }
}