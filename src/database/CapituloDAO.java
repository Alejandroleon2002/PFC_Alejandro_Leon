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
import model.Capitulo;
import prueba.Conexion;

public class CapituloDAO {
    
 
    
    public int obtenerIDAnime(String nombreAnime) {
    try (Connection con = Conexion.obtenerConexion();
         PreparedStatement ps = con.prepareStatement("SELECT anime_id FROM Anime WHERE nombre = ?")) {
        ps.setString(1, nombreAnime);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("anime_id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Si no se encontró el anime, se devuelve -1
}
    
    public List<Capitulo> listarCapitulosPorAnime(int idAnime) {
        List<Capitulo> capitulos = new ArrayList<>();
        String sql = "SELECT * FROM Capitulo WHERE anime_id = ?";
        
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idAnime);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Capitulo capitulo = new Capitulo();
                    capitulo.setCapituloId(rs.getInt("capitulo_id"));
                    capitulo.setAnimeId(rs.getInt("anime_id"));
                    capitulo.setNumeroCapitulo(rs.getInt("numero_capitulo"));
                    capitulo.setTitulo(rs.getString("titulo"));
                    capitulo.setDuracion(rs.getInt("duracion"));
                    capitulos.add(capitulo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        
        return capitulos;
    }

    public List<Capitulo> listar() {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM capitulo");
            ResultSet rs = ps.executeQuery();

            List<Capitulo> capitulos = new ArrayList<>();
            while (rs.next()) {
                Capitulo capitulo = new Capitulo();
                capitulo.setCapituloId(rs.getInt("capitulo_id"));
                capitulo.setAnimeId(rs.getInt("anime_id"));
                capitulo.setNumeroCapitulo(rs.getInt("numero_capitulo"));
                capitulo.setTitulo(rs.getString("titulo"));
                capitulo.setDuracion(rs.getInt("duracion"));
                capitulos.add(capitulo);
            }
            return capitulos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public boolean guardar(Capitulo capitulo) {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO capitulo (anime_id, numero_capitulo, titulo, duracion) VALUES (?, ?, ?, ?)");
            ps.setInt(1, capitulo.getAnimeId());
            ps.setInt(2, capitulo.getNumeroCapitulo());
            ps.setString(3, capitulo.getTitulo());
            ps.setInt(4, capitulo.getDuracion());
            int filasInsertadas = ps.executeUpdate();
            return filasInsertadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public boolean eliminar(int capituloId) {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("DELETE FROM capitulo WHERE capitulo_id = ?");
            ps.setInt(1, capituloId);
            int filasEliminadas = ps.executeUpdate();
            return filasEliminadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public boolean actualizar(Capitulo capitulo) {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE capitulo SET anime_id = ?, numero_capitulo = ?, titulo = ?, duracion = ? WHERE capitulo_id = ?");
            ps.setInt(1, capitulo.getAnimeId());
            ps.setInt(2, capitulo.getNumeroCapitulo());
            ps.setString(3, capitulo.getTitulo());
            ps.setInt(4, capitulo.getDuracion());
            ps.setInt(5, capitulo.getCapituloId());
            int filasActualizadas = ps.executeUpdate();
            return filasActualizadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.cerrarConexion();
        }
    }
}
