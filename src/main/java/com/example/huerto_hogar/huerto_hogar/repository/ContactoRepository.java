package com.example.huerto_hogar.huerto_hogar.repository;

import com.example.huerto_hogar.huerto_hogar.model.MensajeContacto;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<MensajeContacto, Long> {
}