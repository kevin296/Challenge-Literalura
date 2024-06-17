package com.alura.Challenge_Literalura.Principal;

import com.alura.Challenge_Literalura.LibroRepository;
import com.alura.Challenge_Literalura.models.DatosLibros;
import com.alura.Challenge_Literalura.models.Libro;
import com.alura.Challenge_Literalura.service.ConsumoAPI;
import com.alura.Challenge_Literalura.service.ConvierteDatos;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String URL_SEARCH_BY_NAME = "?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosLibros> datosLibros = new ArrayList<>();
    private LibroRepository libroRepository;
    private Optional<Libro> libroBuscado;

    public Principal(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            String menu = """
                    Elije la opción a través de un número:
                    1 - buscar libro por título
                    2 - listar libros registrados
                    3 - listar autores registrados
                    4 - listar autores vivos en un determinado año
                    5 - listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine(); // Consume newline character

            switch (opcion) {
                case 1:
                    buscarSerieLibro();
                    break;
                // Implementa otros casos del menú según tus necesidades
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    private void buscarSerieLibro() {
        DatosLibros datos = getDatosLibros();
        if (datos != null) {
            Libro libro = new Libro(datos);
            libroRepository.save(libro);
            System.out.println("Libro encontrado y guardado: " + libro);
        } else {
            System.out.println("No se pudo encontrar el libro.");
        }
    }

    private DatosLibros getDatosLibros() {
        System.out.println("Escriba el nombre del libro que desea buscar: ");
        String nombreLibro = teclado.nextLine();
        String json = consumoAPI.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+") + URL_SEARCH_BY_NAME);

        if (json == null || json.isEmpty()) {
            System.out.println("La respuesta de la API está vacía.");
            return null;
        }

        try {
            JsonNode jsonNode = conversor.obtenerDatos(json, JsonNode.class);
            return null;
        } catch (Exception e) {
            System.out.println("Error al procesar la respuesta JSON: " + e.getMessage());
            return null;
        }
    }
    private void buscarLibrosPorTitulo(){
        System.out.println("Escribe el nombre de la serie que deseas buscar");
        var nombreLibros = teclado.nextLine();
        libroBuscado = libroRepository.findByTituloContainsIgnoreCase(nombreLibros);

        if(libroBuscado.isPresent()){
            System.out.println("La serie buscada es: " + libroBuscado.get());
        } else {
            System.out.println("Serie no encontrada");
        }

    }
}
