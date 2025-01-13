package Tema3_ComunicacionRed.Apartado3_8_Multihilos;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
    public static void main(String[] args) throws IOException {
        // Crear el servidor en el puerto 6000
        ServerSocket servidor = new ServerSocket(6000);
        System.out.println("Servidor iniciado...");

        // Bucle infinito para aceptar conexiones de clientes
        while (true) {
            // Aceptar la conexión de un cliente
            Socket cliente = servidor.accept();

            // Crear un hilo para manejar al cliente
            HiloServidor hilo = new HiloServidor(cliente);

            // Iniciar el hilo que atenderá al cliente
            hilo.start();
        }
    }
}
