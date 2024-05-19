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
import model.MeGusta;
import prueba.Conexion;

public class MeGustaDAO {

    public List<MeGusta> listar() {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM megusta");
            ResultSet rs = ps.executeQuery();

            List<MeGusta> meGustas = new ArrayList<>();
            while (rs.next()) {
                MeGusta meGusta = new MeGusta();
                meGusta.setUsuarioId(rs.getInt("usuario_id"));
                meGusta.setAnimeId(rs.getInt("anime_id"));
                meGusta.setMeGusta(rs.getBoolean("megusta"));
                meGustas.add(meGusta);
            }
            return meGustas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public boolean guardar(MeGusta meGusta) {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO megusta (usuario_id, anime_id, megusta) VALUES (?, ?, ?)");
            ps.setInt(1, meGusta.getUsuarioId());
            ps.setInt(2, meGusta.getAnimeId());
            ps.setBoolean(3, meGusta.getMeGusta());
            int filasInsertadas = ps.executeUpdate();
            return filasInsertadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public boolean eliminar(int usuarioId, int animeId) {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("DELETE FROM megusta WHERE usuario_id = ? AND anime_id = ?");
            ps.setInt(1, usuarioId);
            ps.setInt(2, animeId);
            int filasEliminadas = ps.executeUpdate();
            return filasEliminadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public boolean actualizar(MeGusta meGusta) {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE megusta SET megusta = ? WHERE usuario_id = ? AND anime_id = ?");
            ps.setBoolean(1, meGusta.getMeGusta());
            ps.setInt(2, meGusta.getUsuarioId());
            ps.setInt(3, meGusta.getAnimeId());
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
