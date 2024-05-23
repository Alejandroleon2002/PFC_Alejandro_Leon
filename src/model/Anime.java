/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Usuario
 */
public class Anime {
    private String nombre;
    private int anyo;
    private String director;
    private String estudio;
    private Categoria categoria;
    private Genero genero;
    private int idAnime;
    private String descripcion;
    private int idCategoria;
    private int idGenero;

    // Getters y setters

    
    public Anime() {
        
    }
    
    public Anime(int idAnime, String nombre, int anyo, String descripcion, String director, String estudio, int idCategoria, int idGenero) {
        this.idAnime = idAnime;
        this.nombre = nombre;
        this.anyo = anyo;
        this.descripcion = descripcion;
        this.director = director;
        this.estudio = estudio;
        this.idCategoria = idCategoria;
        this.idGenero = idGenero;
    }
    public Anime(int idAnime, String nombre, int anyo, String descripcion, String director, String estudio, Categoria categoria, Genero genero) {
        this.idAnime = idAnime;
        this.nombre = nombre;
        this.anyo = anyo;
        this.descripcion = descripcion;
        this.director = director;
        this.estudio = estudio;
        this.categoria = categoria;
        this.genero = genero;
        
    }
    public Anime(String nombre, int anyo, String descripcion, String director, String estudio, Categoria categoria, Genero genero) {
        this.nombre = nombre;
        this.anyo = anyo;
        this.descripcion = descripcion;
        this.director = director;
        this.estudio = estudio;
        this.categoria = categoria;
        this.genero = genero;
    }
   
    public Anime(String nombre, int anyo, String descripcion, String director, String estudio, int idCategoria, int idGenero) {
        this.nombre = nombre;
        this.anyo = anyo;
        this.descripcion = descripcion;
        this.director = director;
        this.estudio = estudio;
        this.idCategoria = idCategoria;
        this.idGenero = idGenero;
    }
    

    
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public int getIdAnime() {
        return idAnime;
    }

    public void setIdAnime(int idAnime) {
        this.idAnime = idAnime;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    
}
