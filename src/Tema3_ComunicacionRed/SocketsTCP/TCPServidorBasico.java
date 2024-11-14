package Tema3_ComunicacionRed.SocketsTCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServidorBasico {
    public static void main(String[] args) throws IOException {
        int Puerto = 6000; // Puerto
        ServerSocket Servidor = new ServerSocket(Puerto);
        System.out.println("Escuchando en " + Servidor.getLocalPort());

        // Esperando a un cliente
        Socket cliente1 = Servidor.accept();
        System.out.println("Cliente 1 conectado");

        // Realizar acciones con cliente1 aquí

        // Esperando a otro cliente
        Socket cliente2 = Servidor.accept();
        System.out.println("Cliente 2 conectado");

        // Realizar acciones con cliente2 aquí

        Servidor.close(); // Cierra el socket del servidor
        System.out.println("Servidor cerrado");
    }
}
