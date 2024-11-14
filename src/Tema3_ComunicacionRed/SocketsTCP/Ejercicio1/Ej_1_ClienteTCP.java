package Tema3_ComunicacionRed.SocketsTCP.Ejercicio1;

import java.io.IOException;
import java.net.Socket;

public class Ej_1_ClienteTCP {
    public static void main(String[] args) {
        String direccionServidor = "127.0.0.1"; // IP del servidor
        int puerto = 12345; // Puerto del servidor

        try (Socket socket = new Socket(direccionServidor, puerto)) {
            System.out.println("Conectado al servidor.");
            System.out.println("Puerto local del cliente: " + socket.getLocalPort());
            System.out.println("Puerto remoto del servidor: " + socket.getPort());
            System.out.println("Dirección IP del servidor: " + socket.getInetAddress().getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

