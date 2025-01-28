package Tema3_ComunicacionRed.ExamenDavidMartin;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

/**
 * Clase ServidorMultihilo que se encargará de aceptar las conexiones de los clientes
 */
public class ServidorMultihilo {
    public static void main(String[] args) {
        int puerto = 2088;
        HashSet<String> numerosRecibidos = new HashSet<>(); // HashSet para almacenar los números


        // Usamos ServerSocket para esperar conexiones entrantes
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            while (true) {
                Socket socketCliente = servidor.accept(); // Acepta nuevas conexiones
                System.out.println("Cliente conectado: " + socketCliente.getInetAddress());
                // Crea un nuevo hilo para manejar al cliente
                new Thread(new ManejadorCliente(socketCliente, numerosRecibidos)).start();
            }
        } catch (IOException e) { // Manejamos las posibles excepciones
            e.printStackTrace();
        }
    }//Fin main
}//Fin ServidorMultihilo
