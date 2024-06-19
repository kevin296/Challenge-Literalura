package com.alura.Challenge_Literalura.Principal;

import com.alura.Challenge_Literalura.AutoresRepository;
import com.alura.Challenge_Literalura.LibroRepository;
import com.alura.Challenge_Literalura.models.*;
import com.alura.Challenge_Literalura.service.ConsumoAPI;
import com.alura.Challenge_Literalura.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner input = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String URL_SEARCH = "?search=";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private LibroRepository librosRepository;
    private AutoresRepository autoresRepository;

    public Principal(LibroRepository librosRepository, AutoresRepository autoresRepository) {
        this.librosRepository = librosRepository;
        this.autoresRepository = autoresRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ---------Bienvenido A LiterAlura---------------
                    ----------------Menu------------------
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma

                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = input.nextInt();
            input.nextLine();
            switch (opcion) {
                case 1:
                    GuardarLibros();
                    break;
                case 2:
                    LibrosRegistrados();
                    break;
                case 3:
                    AutoresRegistrados();
                    break;
                case 4:
                    AutoresVivosPorFecha();
                    break;
                case 5:
                    LibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }

    private Optional<DatosLibros> getDatosLibro() {
        System.out.println("\nEscribe el titulo del libro que deseas buscar");
        var tituloLibro = input.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + URL_SEARCH + tituloLibro.toLowerCase().replace(" ", "+"));
        ApiResponse datos = convierteDatos.obtenerDatos(json, ApiResponse.class);
        return datos.libros().stream()
                .map(l -> new DatosLibros(l.titulo(),
                        l.autores().stream()
                                .map(a -> new DatosAutores(a.nombre(), a.fechaNacimiento(), a.fechaFallecimiento()))
                                .collect(Collectors.toList()),
                        l.idiomas(), l.numeroDeDescargas()))
                .findFirst();
    }

    private void GuardarLibros() {
        Optional<DatosLibros> datos = getDatosLibro();
        if (datos.isPresent()) {
            Libro libro = new Libro(datos);
            Libro tituloLibro = librosRepository.findByTitulo(libro.getTitulo());
            if (tituloLibro != null && tituloLibro.getTitulo().equals(libro.getTitulo())) {
                System.out.println("-------------------\nEl Libro ya esta registrado y no puedes volverlo a registrar");
            } else {
                List<Autores> autoresList = datos.get().autores().stream()
                        .map(a -> { Optional<Autores> autorExiste = autoresRepository
                                    .findByNombreAndFechaNacimientoAndFechaFallecimiento(
                                            a.nombre(), a.fechaNacimiento(), a.fechaFallecimiento());
                            if (autorExiste.isPresent()) {
                                return autorExiste.get();
                            } else {
                                Autores nuevoAutor = new Autores(a.nombre(), a.fechaNacimiento(),
                                        a.fechaFallecimiento());
                                return autoresRepository.save(nuevoAutor);
                            }
                        })
                        .collect(Collectors.toList());
                libro.setAutores(autoresList);
                System.out.println(libro.toString());
                librosRepository.save(libro);

            }
        } else {
            System.out.println("No se encontraron el libro especifico.");
        }
    }

    public void LibrosRegistrados() {
        List<Libro> libros = librosRepository.findAll();
        if (!libros.isEmpty()) {
            System.out.println(libros);
        } else {
            System.out.println("No hay Libros registrado");
        }
    }

    public void AutoresRegistrados() {
        List<Autores> autores = autoresRepository.findAll();
        if (!autores.isEmpty()) {
            System.out.println(autores);
        } else {
            System.out.println("No hay autores registrados");
        }
    }

    public void AutoresVivosPorFecha() {
        System.out.println("Ingrese el año vivo del autor que desee buscar");
        int fecha = input.nextInt();
        List<Autores> autores = autoresRepository.buscarAutoresPorDeterminadoAño(fecha);
        if (!autores.isEmpty()) {
            System.out.println(autores.toString());
        } else {
            System.out.println("No hay autores registrados en ese año");
        }
    }

    public void LibrosPorIdioma() {
        System.out.println("""

                Ingrese el idioma del libro que desea buscar
                es - Español
                en - Ingles
                jp - Japones
                fr - Frances
                pt - Portugues

                """);
        String idioma = input.nextLine();

        List<Libro> libro = librosRepository.buscarLibrosPorIdioma(idioma);

        if (!libro.isEmpty()) {
            System.out.println(libro.toString());
        } else {
            System.out.println("No tenemos libros registrados en ese idioma");
        }

    }
}
