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
    
    
    private static final String INSERT_QUERY = "INSERT INTO MeGusta (usuario_id, anime_id, me_gusta) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE MeGusta SET me_gusta = ? WHERE usuario_id = ? AND anime_id = ?";
    private static final String SELECT_QUERY = "SELECT me_gusta FROM MeGusta WHERE usuario_id = ? AND anime_id = ?";

    public boolean verificarSiLeGusta(int usuarioId, int animeId) {
        String sql = "SELECT me_gusta FROM MeGusta WHERE usuario_id = ? AND anime_id = ?";
        try (Connection connection = Conexion.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, usuarioId);
            statement.setInt(2, animeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean("me_gusta");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean guardarMeGusta(int usuarioId, int animeId, boolean meGusta) {
        try (Connection connection = Conexion.obtenerConexion();
             PreparedStatement ps = connection.prepareStatement(INSERT_QUERY)) {
            ps.setInt(1, usuarioId);
            ps.setInt(2, animeId);
            ps.setBoolean(3, meGusta);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al guardar MeGusta: " + e.getMessage());
            return false;
        }
    }

     public void actualizarMeGusta(int idUsuario, int codAnime, boolean meGusta) {
    try (Connection connection = Conexion.obtenerConexion();
         PreparedStatement updateStatement = connection.prepareStatement(UPDATE_QUERY)) {
        updateStatement.setBoolean(1, meGusta);
        updateStatement.setInt(2, idUsuario);
        updateStatement.setInt(3, codAnime);
        int rowsUpdated = updateStatement.executeUpdate();
        if (rowsUpdated == 0) {
            // Si no se actualizó ninguna fila, significa que no existe el registro, entonces insertamos uno nuevo
            insertarMeGusta(idUsuario, codAnime, meGusta);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
     
     public void insertarMeGusta(int idUsuario, int codAnime, boolean meGusta) {
    try (Connection connection = Conexion.obtenerConexion();
         PreparedStatement insertStatement = connection.prepareStatement(INSERT_QUERY)) {
        insertStatement.setInt(1, idUsuario);
        insertStatement.setInt(2, codAnime);
        insertStatement.setBoolean(3, meGusta);
        insertStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public boolean obtenerMeGusta(int usuarioId, int animeId) {
        try (Connection connection = Conexion.obtenerConexion();
             PreparedStatement ps = connection.prepareStatement(SELECT_QUERY)) {
            ps.setInt(1, usuarioId);
            ps.setInt(2, animeId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("me_gusta");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener MeGusta: " + e.getMessage());
        }
        return false;
    }

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
