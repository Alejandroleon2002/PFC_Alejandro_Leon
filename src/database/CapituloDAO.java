/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Anime;
import model.Capitulo;
import model.Comentario;
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
    return -1;
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
    
   public List<Capitulo> listarTodosLosCapitulos() {
    List<Capitulo> capitulos = new ArrayList<>();
    String sql = "SELECT * FROM Capitulo";

    try (Connection con = Conexion.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Capitulo capitulo = new Capitulo();
            capitulo.setCapituloId(rs.getInt("capitulo_id"));
            capitulo.setAnimeId(rs.getInt("anime_id"));
            capitulo.setNumeroCapitulo(rs.getInt("numero_capitulo"));
            capitulo.setTitulo(rs.getString("titulo"));
            capitulo.setDuracion(rs.getInt("duracion"));
            capitulos.add(capitulo);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    }

    return capitulos;
}


    public boolean actualizarCapitulo(Capitulo capitulo) {
    String sql = "UPDATE Capitulo SET titulo = ?, numero_capitulo = ?, duracion = ?, anime_id = ? WHERE capitulo_id = ?";

    try (Connection con = Conexion.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, capitulo.getTitulo());
        ps.setInt(2, capitulo.getNumeroCapitulo());
        ps.setInt(3, capitulo.getDuracion());
        ps.setInt(4, capitulo.getAnimeId());
        ps.setInt(5, capitulo.getCapituloId());

        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


   
   public void insertarCapitulo(Capitulo capitulo) throws SQLException {
        String sql = "INSERT INTO Capitulo (anime_id, numero_capitulo, titulo, duracion) VALUES (?, ?, ?, ?)";
        
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, capitulo.getAnimeId());
            ps.setInt(2, capitulo.getNumeroCapitulo());
            ps.setString(3, capitulo.getTitulo());
            ps.setInt(4, capitulo.getDuracion());
            ps.executeUpdate();
        }
    }


    
   public Capitulo obtenerCapituloPorId(int codCap, int codAnime) {
        String sql = "SELECT A.nombre AS nombre_anime, C.numero_capitulo, C.titulo AS titulo_capitulo, C.duracion " +
                     "FROM Anime A " +
                     "INNER JOIN Capitulo C ON A.anime_id = C.anime_id " +
                     "WHERE A.anime_id = ? AND C.capitulo_id = ?";
        
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codAnime);
            ps.setInt(2, codCap);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Capitulo capitulo = new Capitulo();
                    capitulo.setNumeroCapitulo(rs.getInt("numero_capitulo"));
                    capitulo.setTitulo(rs.getString("titulo_capitulo"));
                    capitulo.setDuracion(rs.getInt("duracion"));
                    
                    Anime anime = new Anime();
                    anime.setNombre(rs.getString("nombre_anime"));
                    capitulo.setAnime(anime);
                    
                    capitulo.setComentarios(obtenerComentariosPorCapitulo(codCap));
                     
                    return capitulo;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
   
   public Capitulo obtenerCapituloPorId(int idCapitulo) {
    String sql = "SELECT C.numero_capitulo, C.titulo AS titulo_capitulo, C.duracion, " +
                 "A.nombre AS nombre_anime " +
                 "FROM Capitulo C " +
                 "INNER JOIN Anime A ON C.anime_id = A.anime_id " +
                 "WHERE C.capitulo_id = ?";
    
    try (Connection con = Conexion.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idCapitulo);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                Capitulo capitulo = new Capitulo();
                capitulo.setNumeroCapitulo(rs.getInt("numero_capitulo"));
                capitulo.setTitulo(rs.getString("titulo_capitulo"));
                capitulo.setDuracion(rs.getInt("duracion"));
                
                Anime anime = new Anime();
                anime.setNombre(rs.getString("nombre_anime"));
                capitulo.setAnime(anime);
                
                capitulo.setComentarios(obtenerComentariosPorCapitulo(idCapitulo));
                 
                return capitulo;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

   
   public boolean eliminarCapitulo(int idCapitulo) {
    String sqlComentarios = "DELETE FROM Comentario WHERE capitulo_id = ?";
    String sqlCapitulo = "DELETE FROM Capitulo WHERE capitulo_id = ?";

    try (Connection con = Conexion.obtenerConexion();
         PreparedStatement psComentarios = con.prepareStatement(sqlComentarios);
         PreparedStatement psCapitulo = con.prepareStatement(sqlCapitulo)) {

        
        psComentarios.setInt(1, idCapitulo);
        psComentarios.executeUpdate();

      
        psCapitulo.setInt(1, idCapitulo);
        int rowsAffected = psCapitulo.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}



   public List<Comentario> obtenerComentariosPorCapitulo(int codCap) {
        String sql = "SELECT Co.comentario, Co.fecha_comentario, U.nombre_usuario " +
                     "FROM Comentario Co " +
                     "LEFT JOIN Usuario U ON Co.usuario_id = U.usuario_id " +
                     "WHERE Co.capitulo_id = ?";
        
        List<Comentario> comentarios = new ArrayList<>();
        
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codCap);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Comentario comentario = new Comentario();
                    comentario.setComentario(rs.getString("comentario"));
                    comentario.setFechaComentario(rs.getDate("fecha_comentario"));
                    comentario.setNombreUsuario(rs.getString("nombre_usuario"));
                    comentarios.add(comentario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comentarios;
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

    public void agregarComentario(int idAnime, int idCapitulo, Comentario comentario) {
    String sql = "INSERT INTO Comentario (usuario_id, capitulo_id, id_Anime, comentario, fecha_comentario) VALUES (?, ?, ?, ?, ?)";

    try (Connection con = Conexion.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, comentario.getUsuarioId());
        ps.setInt(2, idCapitulo);
        ps.setInt(3, idAnime);
        ps.setString(4, comentario.getComentario());
        ps.setDate(5, (Date) comentario.getFechaComentario());

        ps.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al agregar comentario: " + e.getMessage());
        e.printStackTrace();
    }
}

    public void agregarComentario(int idCapitulo, Comentario comentario) {
    String sql = "INSERT INTO Comentario (usuario_id, capitulo_id, comentario, fecha_comentario) VALUES (?, ?, ?, ?)";

    try (Connection con = Conexion.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, comentario.getUsuarioId());
        ps.setInt(2, idCapitulo);
        ps.setString(3, comentario.getComentario());
        ps.setDate(4, new Date(comentario.getFechaComentario().getTime()));

        ps.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al agregar comentario: " + e.getMessage());
        e.printStackTrace();
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
