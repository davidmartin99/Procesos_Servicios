package Tema3_ComunicacionRed.SocketsTCP;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClienteBasico {
    public static void main(String[] args) throws UnknownHostException, IOException {
        String Host = "localhost"; // Host del servidor
        int Puerto = 6000; // Puerto remoto

        // ABRIR SOCKET
        Socket Cliente = new Socket(Host, Puerto); // Conecta al servidor

        InetAddress i = Cliente.getInetAddress(); // Obtener información del cliente

        System.out.println("Puerto local: " + Cliente.getLocalPort()); // Puerto local
        System.out.println("Puerto remoto: " + Cliente.getPort()); // Puerto remoto
        System.out.println("Nombre Host/IP: " + Cliente.getInetAddress()); // Dirección IP del cliente
        System.out.println("Host remoto: " + i.getHostName()); // Nombre del host remoto
        System.out.println("IP Host remoto: " + i.getHostAddress()); // Dirección IP del host remoto

        Cliente.close(); // Cierra el socket
    }
}
