package com.ejercicio015.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejercicio015.model.Detalle;

public interface DetalleRepository extends JpaRepository<Detalle, Long> {
}