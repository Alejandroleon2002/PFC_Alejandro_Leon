/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author Usuario
 */
public class Capitulo {
    private int capituloId;
    private int animeId;
    private int numeroCapitulo;
    private String titulo;
    private int duracion;
    private Anime anime;
    private List<Comentario> comentarios;

    public Capitulo() {
    }

    public Capitulo(int capituloId, int animeId, String titulo, int numeroCapitulo, int duracion) {
        this.capituloId = capituloId;
        this.animeId = animeId;
        this.titulo = titulo;
        this.numeroCapitulo = numeroCapitulo;
        this.duracion = duracion;
    }

    public int getCapituloId() {
        return capituloId;
    }

    public void setCapituloId(int capituloId) {
        this.capituloId = capituloId;
    }

    public int getAnimeId() {
        return animeId;
    }

    public void setAnimeId(int animeId) {
        this.animeId = animeId;
    }

    public int getNumeroCapitulo() {
        return numeroCapitulo;
    }

    public void setNumeroCapitulo(int numeroCapitulo) {
        this.numeroCapitulo = numeroCapitulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    
    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }
    
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}

