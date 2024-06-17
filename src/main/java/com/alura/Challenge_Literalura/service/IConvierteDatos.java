package com.alura.Challenge_Literalura.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String jsom,Class<T> clase);
}
