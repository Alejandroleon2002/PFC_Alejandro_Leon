/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Genero;
import prueba.Conexion;

public class GeneroDAO {

    public List<Genero> listar() {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM genero");
            ResultSet rs = ps.executeQuery();

            List<Genero> generos = new ArrayList<>();
            while (rs.next()) {
                Genero genero = new Genero();
                genero.setGeneroId(rs.getInt("genero_id"));
                genero.setNombre(rs.getString("nombre"));
                generos.add(genero);
            }
            return generos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public boolean guardar(Genero genero) {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO genero (nombre) VALUES (?)");
            ps.setString(1, genero.getNombre());
            int filasInsertadas = ps.executeUpdate();
            return filasInsertadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public boolean eliminar(int generoId) {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("DELETE FROM genero WHERE genero_id = ?");
            ps.setInt(1, generoId);
            int filasEliminadas = ps.executeUpdate();
            return filasEliminadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public boolean actualizar(Genero genero) {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE genero SET nombre = ? WHERE genero_id = ?");
            ps.setString(1, genero.getNombre());
            ps.setInt(2, genero.getGeneroId());
            int filasActualizadas = ps.executeUpdate();
            return filasActualizadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.cerrarConexion();
        }
    }
    
    public ResultSet obtenerNombresGeneros() throws SQLException {
        Connection con = Conexion.obtenerConexion();
        String sql = "SELECT DISTINCT NOMBRE from Genero";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }
    
    public List<Genero> obtenerTodosLosGeneros() {
        List<Genero> generos = new ArrayList<>();
        String sql = "SELECT * FROM Genero";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Genero genero = new Genero();
                genero.setGeneroId(rs.getInt("genero_id"));
                genero.setNombre(rs.getString("nombre"));
                generos.add(genero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generos;
    }
    
    public List<Genero> obtenerGenerosPorAnime(int codAnime) {
        List<Genero> generos = new ArrayList<>();
        String sql = "SELECT g.* FROM Genero g INNER JOIN AnimeGenero ag ON g.genero_id = ag.genero_id WHERE ag.anime_id = ?";
        
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codAnime);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Genero genero = new Genero();
                    genero.setGeneroId(rs.getInt("genero_id"));
                    genero.setNombre(rs.getString("nombre"));
                    generos.add(genero);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generos;
    }
    
    
    public Genero obtenerGeneroPorId(int id) {
    Genero genero = null;
    String sql = "SELECT * FROM Genero WHERE genero_id = ?";
    
    try (Connection con = Conexion.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                genero = new Genero();
                genero.setGeneroId(rs.getInt("genero_id"));
                genero.setNombre(rs.getString("nombre"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return genero;
}
    
    
   

    public int obtenerIdGeneroPorNombre(String nombreGenero) {
    int id = -1;
    String sql = "SELECT genero_id FROM Genero WHERE nombre = ?";
    
    try (Connection con = Conexion.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, nombreGenero);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                id = rs.getInt("genero_id");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return id;
}
}

