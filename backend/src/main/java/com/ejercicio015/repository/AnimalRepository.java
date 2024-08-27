package com.ejercicio015.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejercicio015.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}