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
import model.Usuario;
import prueba.Conexion;

public class UsuarioDAO {

    public List<Usuario> listar() {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario");
            ResultSet rs = ps.executeQuery();

            List<Usuario> usuarios = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setUsuarioId(rs.getInt("usuario_id"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public boolean guardar(Usuario usuario) {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO usuario (nombre_usuario, correo, contraseña) VALUES (?, ?, ?)");
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getContraseña());
            int filasInsertadas = ps.executeUpdate();
            return filasInsertadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public boolean eliminar(int usuarioId) {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("DELETE FROM usuario WHERE usuario_id = ?");
            ps.setInt(1, usuarioId);
            int filasEliminadas = ps.executeUpdate();
            return filasEliminadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public boolean actualizar(Usuario usuario) {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE usuario SET nombre_usuario = ?, correo = ?, contraseña = ? WHERE usuario_id = ?");
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getContraseña());
            ps.setInt(4, usuario.getUsuarioId());
            int filasActualizadas = ps.executeUpdate();
            return filasActualizadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.cerrarConexion();
        }
    }
    
    public boolean verificarCredenciales(String nombreUsuario, String contraseña) {
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM Usuario WHERE nombre_usuario = ? AND contraseña = ?")) {
            ps.setString(1, nombreUsuario);
            ps.setString(2, contraseña);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int obtenerIDUsuario(String nombreUsuario) {
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement("SELECT usuario_id FROM Usuario WHERE nombre_usuario = ?")) {
            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("usuario_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String obtenerNombreUsuario(int id) {
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement("SELECT nombre_usuario FROM Usuario WHERE usuario_id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nombre_usuario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Usuario no encontrado";
    }

    public boolean usuarioExistente(String nombreUsuario) {
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) AS count FROM Usuario WHERE nombre_usuario = ?")) {
            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public void guardarUsuario(String nombreUsuario, String correo, String contraseña) {
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement("INSERT INTO Usuario (nombre_usuario, correo, contraseña) VALUES (?, ?, ?)")) {
            ps.setString(1, nombreUsuario);
            ps.setString(2, correo);
            ps.setString(3, contraseña);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
}
