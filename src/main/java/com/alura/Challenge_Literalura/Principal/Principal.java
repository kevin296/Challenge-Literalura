package com.alura.Challenge_Literalura.Principal;

import com.alura.Challenge_Literalura.models.DatosLibros;
import com.alura.Challenge_Literalura.service.ConsumoAPI;
import com.alura.Challenge_Literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosLibros> datosLibros = new ArrayList<>();



}
