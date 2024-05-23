/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Usuario
 */
public class Categoria {
    private int categoriaId;
    private String nombre;
    
    
    public Categoria() {
        // Constructor por defecto
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }
    
    public Categoria(int categoriaId, String nombre) {
        this.categoriaId = categoriaId;
        this.nombre = nombre;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        return nombre;
    }
}