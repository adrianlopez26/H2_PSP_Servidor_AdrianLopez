package com.empresa.servidor;

import com.empresa.datos.AccesoDatos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 8080;
        AccesoDatos accesoDatos = new AccesoDatos();

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado en el puerto " + puerto);

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado.");

                new Thread(() -> {
                    try (BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                         PrintWriter salida = new PrintWriter(new OutputStreamWriter(clienteSocket.getOutputStream()), true)) {

                        String operacion;
                        while ((operacion = entrada.readLine()) != null) {
                            String respuesta = "";

                            switch (operacion) {
                                case "LISTAR":
                                    respuesta = accesoDatos.listarLibros();
                                    break;
                                case "AGREGAR":
                                    String titulo = entrada.readLine(); // Leer título del cliente
                                    String autor = entrada.readLine();  // Leer autor del cliente
                                    int anio = Integer.parseInt(entrada.readLine()); // Leer año del cliente

                                    respuesta = accesoDatos.agregarLibro(titulo, autor, anio);
                                    break;
                                case "ACTUALIZAR":
                                    int idActualizar = Integer.parseInt(entrada.readLine());
                                    String nuevoTitulo = entrada.readLine();
                                    String nuevoAutor = entrada.readLine();
                                    int nuevoAnio = Integer.parseInt(entrada.readLine());
                                    respuesta = accesoDatos.actualizarLibro(idActualizar, nuevoTitulo, nuevoAutor, nuevoAnio);
                                    break;
                                case "ELIMINAR":
                                    int idEliminar = Integer.parseInt(entrada.readLine());
                                    respuesta = accesoDatos.eliminarLibro(idEliminar);
                                    break;
                                case "BUSCAR_POR_ANIO":
                                    int anioBusqueda = Integer.parseInt(entrada.readLine());
                                    respuesta = accesoDatos.buscarLibrosPorAnio(anioBusqueda);
                                    break;
                                default:
                                    respuesta = "Operación no válida.";
                            }
                            salida.println(respuesta);
                        }
                    } catch (java.net.SocketException e) {
                        System.err.println("Connection reset: " + e.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            clienteSocket.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}