package com.alura.Challenge_Literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponse(@JsonAlias("results") List<DatosLibros> libros) {
}
