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
import model.Categoria;
import prueba.Conexion;

public class CategoriaDAO {

    public List<Categoria> listar() {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM categoria");
            ResultSet rs = ps.executeQuery();

            List<Categoria> categorias = new ArrayList<>();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setCategoriaId(rs.getInt("categoria_id"));
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }
            return categorias;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public boolean guardar(Categoria categoria) {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO categoria (nombre) VALUES (?)");
            ps.setString(1, categoria.getNombre());
            int filasInsertadas = ps.executeUpdate();
            return filasInsertadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public boolean eliminar(int categoriaId) {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("DELETE FROM categoria WHERE categoria_id = ?");
            ps.setInt(1, categoriaId);
            int filasEliminadas = ps.executeUpdate();
            return filasEliminadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.cerrarConexion();
        }
    }

    public boolean actualizar(Categoria categoria) {
        Connection con = null;
        try {
            con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE categoria SET nombre = ? WHERE categoria_id = ?");
            ps.setString(1, categoria.getNombre());
            ps.setInt(2, categoria.getCategoriaId());
            int filasActualizadas = ps.executeUpdate();
            return filasActualizadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.cerrarConexion();
        }
    }
    
    public ResultSet obtenerNombresCategorias() throws SQLException {
        Connection con = Conexion.obtenerConexion();
        String sql = "SELECT DISTINCT NOMBRE from Categoria";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }
}
