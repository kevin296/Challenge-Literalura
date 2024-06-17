package com.alura.Challenge_Literalura.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "Libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "autor", nullable = false)
    private String autor;

    @ElementCollection
    @CollectionTable(name = "libro_traductores", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "traductor")
    private List<String> traductores;

    @ElementCollection
    @CollectionTable(name = "libro_temas", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "tema")
    private List<String> temas;

    @ElementCollection
    @CollectionTable(name = "libro_estanterias", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "estanteria")
    private List<String> estanterias;

    @Column(name = "idioma", nullable = false)
    private String idioma;

    @Column(name = "descargable", nullable = false)
    private boolean descargable;

    @Column(name = "tipo_medio", nullable = false)
    private String tipoMedio;

    @ElementCollection
    @CollectionTable(name = "libro_formatos", joinColumns = @JoinColumn(name = "libro_id"))
    @MapKeyColumn(name = "formato")
    @Column(name = "url")
    private Map<String, String> formatos;

    @Column(name = "cantidad_descargas")
    private int cantidadDescargas;



    public Libro(DatosLibros datosLibros) {
        this.autor =datosLibros.autor();
        this.cantidadDescargas = datosLibros.cantidad_descargas();
        this.descargable = datosLibros.descargable();
        this.idioma = datosLibros.idioma();
        this.tipoMedio = datosLibros.tipo_medio();
        this.titulo = datosLibros.titulo();
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<String> getTraductores() {
        return traductores;
    }

    public void setTraductores(List<String> traductores) {
        this.traductores = traductores;
    }

    public List<String> getTemas() {
        return temas;
    }

    public void setTemas(List<String> temas) {
        this.temas = temas;
    }

    public List<String> getEstanterias() {
        return estanterias;
    }

    public void setEstanterias(List<String> estanterias) {
        this.estanterias = estanterias;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public boolean isDescargable() {
        return descargable;
    }

    public void setDescargable(boolean descargable) {
        this.descargable = descargable;
    }

    public String getTipoMedio() {
        return tipoMedio;
    }

    public void setTipoMedio(String tipoMedio) {
        this.tipoMedio = tipoMedio;
    }

    public Map<String, String> getFormatos() {
        return formatos;
    }

    public void setFormatos(Map<String, String> formatos) {
        this.formatos = formatos;
    }

    public int getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(int cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }
}
