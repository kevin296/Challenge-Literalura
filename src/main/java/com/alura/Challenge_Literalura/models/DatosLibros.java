package com.alura.Challenge_Literalura.models;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
                            @JsonAlias("authors") String autor,
                            @JsonAlias("download_count")Integer cantidad_descargas,
                            @JsonAlias("downloadable") boolean descargable,
                            @JsonAlias("languages") String idioma,
                            @JsonAlias("media_type") String tipo_medio,
                            @JsonAlias("title") String titulo



){

}
