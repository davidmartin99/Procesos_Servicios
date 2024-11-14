package Tema3_ComunicacionRed.SocketsTCP.Ejercicio1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Ej_1_ServidorTCP {
    public static void main(String[] args) {
        int puerto = 12345; // Puerto donde escuchará el servidor

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado en el puerto " + puerto);

            for (int i = 1; i <= 2; i++) { // Acepta dos clientes
                Socket cliente = servidor.accept();
                System.out.println("Cliente " + i + " conectado.");
                System.out.println("Puerto local del servidor: " + cliente.getLocalPort());
                System.out.println("Puerto remoto del cliente: " + cliente.getPort());
                System.out.println("Dirección IP del cliente: " + cliente.getInetAddress().getHostAddress());
                System.out.println("--------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

