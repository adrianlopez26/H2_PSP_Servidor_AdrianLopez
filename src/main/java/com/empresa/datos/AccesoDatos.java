// src/main/java/com/empresa/datos/AccesoDatos.java
package com.empresa.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccesoDatos {
    public String listarLibros() {
        StringBuilder resultado = new StringBuilder();
        String query = "SELECT * FROM libros";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                resultado.append("ID: ").append(rs.getInt("id"))
                        .append(", Título: ").append(rs.getString("titulo"))
                        .append(", Autor: ").append(rs.getString("autor"))
                        .append(", Año: ").append(rs.getInt("anio"))
                        .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al listar libros.";
        }
        return resultado.toString().isEmpty() ? "No hay libros disponibles." : resultado.toString();
    }

    public String agregarLibro(String titulo, String autor, int anio) {
        String query = "INSERT INTO libros (titulo, autor, anio) VALUES (?, ?, ?)";
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {

            statement.setString(1, titulo);
            statement.setString(2, autor);
            statement.setInt(3, anio);
            statement.executeUpdate();
            return "Libro agregado correctamente.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al agregar el libro.";
        }
    }

    public String actualizarLibro(int id, String titulo, String autor, int anio) {
        String query = "UPDATE libros SET titulo = ?, autor = ?, anio = ? WHERE id = ?";
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {

            statement.setString(1, titulo);
            statement.setString(2, autor);
            statement.setInt(3, anio);
            statement.setInt(4, id);
            int filas = statement.executeUpdate();
            return filas > 0 ? "Libro actualizado correctamente." : "Libro no encontrado.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al actualizar el libro.";
        }
    }

    public String eliminarLibro(int id) {
        String query = "DELETE FROM libros WHERE id = ?";
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {

            statement.setInt(1, id);
            int filas = statement.executeUpdate();
            return filas > 0 ? "Libro eliminado correctamente." : "Libro no encontrado.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al eliminar el libro.";
        }
    }

    public String buscarLibrosPorAnio(int anio) {
        StringBuilder resultado = new StringBuilder();
        String query = "SELECT * FROM libros WHERE anio = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {

            statement.setInt(1, anio);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                resultado.append("ID: ").append(rs.getInt("id"))
                        .append(", Título: ").append(rs.getString("titulo"))
                        .append(", Autor: ").append(rs.getString("autor"))
                        .append(", Año: ").append(rs.getInt("anio"))
                        .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al buscar libros por año.";
        }
        return resultado.toString().isEmpty() ? "No hay libros disponibles para el año " + anio + "." : resultado.toString();
    }

    public String buscarLibrosPorId(int id) {
        StringBuilder resultado = new StringBuilder();
        String query = "SELECT * FROM libros WHERE id = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                resultado.append("ID: ").append(rs.getInt("id"))
                        .append(", Título: ").append(rs.getString("titulo"))
                        .append(", Autor: ").append(rs.getString("autor"))
                        .append(", Año: ").append(rs.getInt("anio"))
                        .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al buscar libro por ID.";
        }
        return resultado.toString().isEmpty() ? "No hay libro disponible con el ID " + id + "." : resultado.toString();
    }

    public String buscarLibrosPorTitulo(String titulo) {
        StringBuilder resultado = new StringBuilder();
        String query = "SELECT * FROM libros WHERE titulo LIKE ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {

            statement.setString(1, "%" + titulo + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                resultado.append("ID: ").append(rs.getInt("id"))
                        .append(", Título: ").append(rs.getString("titulo"))
                        .append(", Autor: ").append(rs.getString("autor"))
                        .append(", Año: ").append(rs.getInt("anio"))
                        .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al buscar libros por título.";
        }
        return resultado.toString().isEmpty() ? "No hay libros disponibles con el título " + titulo + "." : resultado.toString();
    }

    public String buscarLibrosPorAutor(String autor) {
        StringBuilder resultado = new StringBuilder();
        String query = "SELECT * FROM libros WHERE autor LIKE ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {

            statement.setString(1, "%" + autor + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                resultado.append("ID: ").append(rs.getInt("id"))
                        .append(", Título: ").append(rs.getString("titulo"))
                        .append(", Autor: ").append(rs.getString("autor"))
                        .append(", Año: ").append(rs.getInt("anio"))
                        .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al buscar libros por autor.";
        }
        return resultado.toString().isEmpty() ? "No hay libros disponibles con el autor " + autor + "." : resultado.toString();
    }
}
