package com.example.huerto_hogar.huerto_hogar.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


//Generador de Tokens
//Esta clase es la "máquina de firmas". 
//Toma el correo del usuario y le pone un sello criptográfico seguro.
@Component
public class JwtUtil {

    
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    //Duración del token
    private final long EXPIRATION_TIME = 86400000; 

    //Método para crear el Token
    public String generateToken(String correo, String rol) {
        return Jwts.builder()
                .setSubject(correo)          // El Dueño del token
                .claim("rol", rol)     // Guardamos el rol dentro
                .setIssuedAt(new Date())     // Fecha de creación
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Fecha de expiración
                .signWith(key)               // Firmamos con la clave secreta
                .compact();
    }
}