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
import java.util.Date;
import java.util.List;
import model.Comentario;
import prueba.Conexion;

public class ComentarioDAO {

    public List<Comentario> listar() {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM comentario");
            ResultSet rs = ps.executeQuery();

            List<Comentario> comentarios = new ArrayList<>();
            while (rs.next()) {
                Comentario comentario = new Comentario();
                comentario.setComentarioId(rs.getInt("comentario_id"));
                comentario.setUsuarioId(rs.getInt("usuario_id"));
                comentario.setCapituloId(rs.getInt("capitulo_id"));
                comentario.setIdAnime(rs.getInt("id_anime"));
                comentario.setComentario(rs.getString("comentario"));
                comentario.setFechaComentario(rs.getDate("fecha_comentario"));
                comentarios.add(comentario);
            }
            return comentarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public boolean guardar(Comentario comentario) {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO comentario (usuario_id, capitulo_id, id_anime, comentario, fecha_comentario) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, comentario.getUsuarioId());
            ps.setInt(2, comentario.getCapituloId());
            ps.setInt(3, comentario.getIdAnime());
            ps.setString(4, comentario.getComentario());
            ps.setDate(5, new java.sql.Date(comentario.getFechaComentario().getTime()));
            int filasInsertadas = ps.executeUpdate();
            return filasInsertadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.cerrarConexion();
        }
    }
    
    

    public boolean eliminar(int comentarioId) {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("DELETE FROM comentario WHERE comentario_id = ?");
            ps.setInt(1, comentarioId);
            int filasEliminadas = ps.executeUpdate();
            return filasEliminadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public boolean actualizar(Comentario comentario) {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE comentario SET usuario_id = ?, capitulo_id = ?, id_anime = ?, comentario = ?, fecha_comentario = ? WHERE comentario_id = ?");
            ps.setInt(1, comentario.getUsuarioId());
            ps.setInt(2, comentario.getCapituloId());
            ps.setInt(3, comentario.getIdAnime());
            ps.setString(4, comentario.getComentario());
            ps.setDate(5, new java.sql.Date(comentario.getFechaComentario().getTime()));
            ps.setInt(6, comentario.getComentarioId());
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
