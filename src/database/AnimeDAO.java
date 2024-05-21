/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Anime;
import model.Categoria;
import model.Genero;
import prueba.Conexion;

public class AnimeDAO {
    
    

   public List<Anime> listarAnimesConDetalles() {
        List<Anime> listaAnimes = new ArrayList<>();
        String sql = "SELECT A.nombre, A.anyo, A.director, A.estudio, C.nombre AS nombre_categoria, G.nombre AS nombre_genero, A.anime_id, A.descripcion " +
                     "FROM Anime A " +
                     "JOIN Categoria C ON A.id_categoria = C.categoria_id " +
                     "JOIN Genero G ON A.id_genero = G.genero_id";
        
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Anime anime = new Anime();
                anime.setNombre(rs.getString("nombre"));
                anime.setAnyo(rs.getInt("anyo"));
                anime.setDirector(rs.getString("director"));
                anime.setEstudio(rs.getString("estudio"));
                Categoria categoria = new Categoria();
                categoria.setNombre(rs.getString("nombre_categoria"));
                anime.setCategoria(categoria);
                Genero genero = new Genero();
                genero.setNombre(rs.getString("nombre_genero"));
                anime.setGenero(genero);
                anime.setIdAnime(rs.getInt("anime_id"));
                anime.setDescripcion(rs.getString("descripcion"));
                listaAnimes.add(anime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        
        return listaAnimes;
    }
   
   public List<Anime> listar() {
    List<Anime> animes = new ArrayList<>();
    String sql = "SELECT * FROM anime";
    
    try (Connection con = Conexion.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Anime anime = new Anime();
            anime.setIdAnime(rs.getInt("anime_id"));
            anime.setNombre(rs.getString("nombre"));
            anime.setDescripcion(rs.getString("descripcion"));
            anime.setAnyo(rs.getInt("anyo"));
            anime.setDirector(rs.getString("director"));
            anime.setEstudio(rs.getString("estudio"));
            Categoria categoria = new Categoria();
                categoria.setNombre(rs.getString("nombre_categoria"));
                anime.setCategoria(categoria);
                Genero genero = new Genero();
                genero.setNombre(rs.getString("nombre_genero"));
                anime.setGenero(genero);
            animes.add(anime);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    }
    
    return animes;
}
   
   
   
    public List<Anime> filtrarAnime(String director, String estudio, String categoria, String genero, String nombre, String anyo) {
    List<Anime> animes = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT A.NOMBRE, A.ANYO, A.DIRECTOR, A.ESTUDIO, C.NOMBRE AS nombre_categoria, G.NOMBRE AS nombre_genero, A.ANIME_ID, A.DESCRIPCION " +
                                          "FROM Anime A " +
                                          "JOIN Categoria C ON A.ID_CATEGORIA = C.CATEGORIA_ID " +
                                          "JOIN Genero G ON A.ID_GENERO = G.GENERO_ID " +
                                          "WHERE 1=1");

    if (!"Elija uno".equals(director)) {
        sql.append(" AND A.DIRECTOR = ?");
    }

    if (!"Elija uno".equals(estudio)) {
        sql.append(" AND A.ESTUDIO = ?");
    }

    if (!"Elija uno".equals(categoria)) {
        sql.append(" AND C.NOMBRE = ?");
    }

    if (!"Elija uno".equals(genero)) {
        sql.append(" AND G.NOMBRE = ?");
    }

    if (nombre != null && !nombre.isEmpty()) {
        sql.append(" AND LOWER(A.NOMBRE) LIKE LOWER(?)");
    }

    if (anyo != null && !anyo.isEmpty()) {
         sql.append(" AND A.ANYO LIKE ?");
         anyo = anyo.trim() + "%";
    }

    try (Connection conexion = Conexion.obtenerConexion();
         PreparedStatement ps = conexion.prepareStatement(sql.toString())) {
        
        int paramIndex = 1;
        if (!"Elija uno".equals(director)) {
            ps.setString(paramIndex++, director);
        }
        if (!"Elija uno".equals(estudio)) {
            ps.setString(paramIndex++, estudio);
        }
        if (!"Elija uno".equals(categoria)) {
            ps.setString(paramIndex++, categoria);
        }
        if (!"Elija uno".equals(genero)) {
            ps.setString(paramIndex++, genero);
        }
        if (nombre != null && !nombre.isEmpty()) {
            ps.setString(paramIndex++, nombre + "%");
        }
        if (anyo != null && !anyo.isEmpty()) {
            ps.setString(paramIndex++, anyo + "%");
        }

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Anime anime = new Anime();
                anime.setNombre(rs.getString(1));
                anime.setAnyo(rs.getInt(2));
                anime.setDirector(rs.getString(3));
                anime.setEstudio(rs.getString(4));
                anime.setCategoria(new Categoria(rs.getString(5))); // Usamos el constructor adecuado de Categoria
                anime.setGenero(new Genero(rs.getString(6))); // Usamos el constructor adecuado de Genero
                anime.setIdAnime(rs.getInt(7));
                anime.setDescripcion(rs.getString(8));
                animes.add(anime);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    }
    return animes;
}
   
   
    public boolean guardar(Anime anime) {
        String sql = "INSERT INTO anime (nombre, descripcion, anyo, director, estudio,  id_categoria, id_genero) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, anime.getNombre());
            ps.setString(2, anime.getDescripcion());
            ps.setInt(3, anime.getAnyo());
            ps.setString(4, anime.getDirector());
            ps.setString(5, anime.getEstudio());
            ps.setInt(6, anime.getCategoria().getCategoriaId());
            ps.setInt(7, anime.getGenero().getGeneroId());
            
            int filasInsertadas = ps.executeUpdate();
            return filasInsertadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    public boolean actualizarAnime(Anime anime) {
    String sql = "UPDATE Anime SET nombre = ?, anyo = ?, descripcion = ?, director = ?, estudio = ?, id_Categoria = ?, id_Genero = ? WHERE anime_id = ?";

    try (Connection con = Conexion.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, anime.getNombre());
        ps.setInt(2, anime.getAnyo());
        ps.setString(3, anime.getDescripcion());
        ps.setString(4, anime.getDirector());
        ps.setString(5, anime.getEstudio());
        ps.setInt(6, anime.getCategoria().getCategoriaId());
        ps.setInt(7, anime.getGenero().getGeneroId());
        ps.setInt(8, anime.getIdAnime());

        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    public boolean actualizar(Anime anime) {
        String sql = "UPDATE anime SET nombre = ?, descripcion = ?, anyo = ?, director = ?, estudio = ?, id_categoria = ?, id_genero = ? WHERE anime_id = ?";
        
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, anime.getNombre());
            ps.setString(2, anime.getDescripcion());
            ps.setInt(3, anime.getAnyo());
            ps.setString(4, anime.getDirector());
            ps.setString(5, anime.getEstudio());
            ps.setInt(6, anime.getCategoria().getCategoriaId());
            ps.setInt(7, anime.getGenero().getGeneroId());
            ps.setInt(8, anime.getIdAnime());
            
            int filasActualizadas = ps.executeUpdate();
            return filasActualizadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarAnime(int idAnime) {
    String sqlMeGusta = "DELETE FROM MeGusta WHERE anime_id = ?";
    String sqlComentarios = "DELETE FROM Comentario WHERE anime_id = ?";
    String sqlCapitulos = "DELETE FROM Capitulo WHERE anime_id = ?";
    String sqlAnime = "DELETE FROM Anime WHERE anime_id = ?";

    try (Connection con = Conexion.obtenerConexion();
         PreparedStatement psMeGusta = con.prepareStatement(sqlMeGusta);
         PreparedStatement psComentarios = con.prepareStatement(sqlComentarios);
         PreparedStatement psCapitulos = con.prepareStatement(sqlCapitulos);
         PreparedStatement psAnime = con.prepareStatement(sqlAnime)) {

        // Eliminar las filas en MeGusta asociadas
        psMeGusta.setInt(1, idAnime);
        psMeGusta.executeUpdate();

        // Eliminar los comentarios asociados
        psComentarios.setInt(1, idAnime);
        psComentarios.executeUpdate();

        // Eliminar los capítulos asociados
        psCapitulos.setInt(1, idAnime);
        psCapitulos.executeUpdate();

        // Eliminar el anime
        psAnime.setInt(1, idAnime);
        int rowsAffected = psAnime.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
    
    public boolean eliminar(int animeId) {
        String sql = "DELETE FROM anime WHERE anime_id = ?";
        
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setInt(1, animeId);
            int filasEliminadas = ps.executeUpdate();
            return filasEliminadas == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet obtenerAnimesConDetalles() throws SQLException {
        Connection con = Conexion.obtenerConexion();
        String sql = "SELECT A.nombre, A.anyo, A.director, A.estudio, C.nombre AS nombre_categoria, G.nombre AS nombre_genero, A.anime_id, A.descripcion " +
                     "FROM Anime A " +
                     "JOIN Categoria C ON A.id_categoria = C.categoria_id " +
                     "JOIN Genero G ON A.id_genero = G.genero_id";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }

    public ResultSet obtenerDirectores() throws SQLException {
        Connection con = Conexion.obtenerConexion();
        String sql = "SELECT DISTINCT director FROM Anime";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }

    public ResultSet obtenerEstudios() throws SQLException {
        Connection con = Conexion.obtenerConexion();
        String sql = "SELECT DISTINCT estudio FROM Anime";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }

    public ResultSet obtenerCategorias() throws SQLException {
        Connection con = Conexion.obtenerConexion();
        String sql = "SELECT DISTINCT nombre FROM Categoria";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }

    public ResultSet obtenerGeneros() throws SQLException {
        Connection con = Conexion.obtenerConexion();
        String sql = "SELECT DISTINCT nombre FROM Genero";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }
    
    
    public Anime obtenerAnimePorId(int id) {
    String sql = "SELECT A.nombre, A.anyo, A.descripcion, A.director, A.estudio, C.nombre AS categoria, G.nombre AS genero " +
                 "FROM Anime A " +
                 "INNER JOIN Categoria C ON A.id_Categoria = C.categoria_id " +
                 "INNER JOIN Genero G ON A.id_Genero = G.genero_id " +
                 "WHERE A.anime_id = ?";
    
    try (Connection con = Conexion.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                Anime anime = new Anime();
                anime.setNombre(rs.getString("nombre"));
                anime.setAnyo(rs.getInt("anyo"));
                anime.setDescripcion(rs.getString("descripcion"));
                anime.setDirector(rs.getString("director"));
                anime.setEstudio(rs.getString("estudio"));
                Categoria categoria = new Categoria();
                categoria.setNombre(rs.getString("categoria"));
                anime.setCategoria(categoria);
                Genero genero = new Genero();
                genero.setNombre(rs.getString("genero"));
                anime.setGenero(genero);
                return anime;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    
    
    
}