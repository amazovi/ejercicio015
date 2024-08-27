package com.ejercicio015.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ejercicio015.model.Animal;
import com.ejercicio015.repository.AnimalRepository;

@RestController
@RequestMapping("/api/animals")
@CrossOrigin(origins = "http://localhost:8080")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        return animalRepository.findById(id)
                .map(animal -> ResponseEntity.ok().body(animal))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Animal createAnimal(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        return animalRepository.findById(id)
                .map(existingAnimal -> {
                    existingAnimal.setNombre(animal.getNombre());
                    existingAnimal.setTipo(animal.getTipo());
                    animalRepository.save(existingAnimal);
                    return ResponseEntity.ok().body(existingAnimal);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        if (animalRepository.existsById(id)) {
            animalRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}