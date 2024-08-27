package com.ejercicio015.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ejercicio015.model.Detalle;
import com.ejercicio015.repository.DetalleRepository;
@RestController
@RequestMapping("/api/detalles")
@CrossOrigin(origins = "http://localhost:8080")
public class DetalleController {

    @Autowired
    private DetalleRepository detalleRepository;

    @GetMapping
    public List<Detalle> getAllDetalles() {
        return detalleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detalle> getDetalleById(@PathVariable Long id) {
        return detalleRepository.findById(id)
                .map(detalle -> ResponseEntity.ok().body(detalle))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Detalle createDetalle(@RequestBody Detalle detalle) {
        return detalleRepository.save(detalle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Detalle> updateDetalle(@PathVariable Long id, @RequestBody Detalle detalle) {
        return detalleRepository.findById(id)
                .map(existingDetalle -> {
                    existingDetalle.setDescripcion(detalle.getDescripcion());
                    existingDetalle.setAnimal(detalle.getAnimal());
                    detalleRepository.save(existingDetalle);
                    return ResponseEntity.ok().body(existingDetalle);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalle(@PathVariable Long id) {
        if (detalleRepository.existsById(id)) {
            detalleRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}