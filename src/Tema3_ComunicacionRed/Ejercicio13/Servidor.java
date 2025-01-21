package Tema3_ComunicacionRed.Ejercicio13;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 11223;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            while (true) {
                Socket socketCliente = servidor.accept(); // Acepta nuevas conexiones
                System.out.println("Cliente conectado: " + socketCliente.getInetAddress());
                // Crea un nuevo hilo para manejar al cliente
                new Thread(new ManejadorCliente(socketCliente)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

