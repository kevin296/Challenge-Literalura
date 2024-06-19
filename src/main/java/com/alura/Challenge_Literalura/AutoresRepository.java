package com.alura.Challenge_Literalura;

import com.alura.Challenge_Literalura.models.Autores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AutoresRepository extends JpaRepository<Autores, Long> {
    Autores findByNombreContainsIgnoreCase(String nombre);

    Optional<Autores> findByNombreAndFechaNacimientoAndFechaFallecimiento(String nombre, Integer fechaNacimiento, Integer fechaFallecimiento);

    @Query("SELECT a FROM Autores a WHERE a.fechaNacimiento <= :fecha AND a.fechaFallecimiento >= :fecha")
    List<Autores> buscarAutoresPorDeterminadoAño(int fecha);
}
