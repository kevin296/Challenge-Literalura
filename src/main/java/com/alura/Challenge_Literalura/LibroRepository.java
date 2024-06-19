package com.alura.Challenge_Literalura;

import com.alura.Challenge_Literalura.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    Libro findByTitulo(String titulo);

    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> buscarLibrosPorIdioma(String idioma);
}
