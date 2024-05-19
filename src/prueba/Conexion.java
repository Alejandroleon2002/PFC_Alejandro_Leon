/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String BBDD_URL = "jdbc:hsqldb:hsql://localhost/";
    private static final String USER = "SA";
    private static final String PASSWORD = "SA";

    private static Connection conexion;

    static {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            conexion = DriverManager.getConnection(BBDD_URL, USER, PASSWORD);
            if (conexion != null) {
                System.out.println("Conexión creada exitosamente");
            } else {
                System.out.println("Problema al crear la conexión");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public static Connection obtenerConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(BBDD_URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return conexion;
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada exitosamente");
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
    }
}


