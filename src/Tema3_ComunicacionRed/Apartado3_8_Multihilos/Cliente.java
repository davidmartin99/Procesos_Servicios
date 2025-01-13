package Tema3_ComunicacionRed.Apartado3_8_Multihilos;
import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) throws IOException {
        // Definimos la dirección del servidor y el puerto de conexión
        String host = "localhost";  // Dirección del servidor (en este caso, localhost)
        int puerto = 6000;          // Puerto remoto donde se conecta el servidor

        // Creamos un socket para conectar al servidor
        Socket cliente = new Socket(host, puerto);

        // Creamos flujo de salida para enviar datos al servidor
        PrintWriter fsalida = new PrintWriter(cliente.getOutputStream(), true);

        // Creamos flujo de entrada para recibir datos del servidor
        BufferedReader fentrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

        // Flujo para entrada estándar, para leer lo que el usuario escribe
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String cadena, eco = "";

        // Bucle para enviar y recibir mensajes del servidor
        while (true) {
            // Pedimos al usuario que ingrese una cadena
            System.out.print("Introduce un mensaje para el servidor: ");
            cadena = in.readLine();

            // Si el usuario escribe "salir", salimos del bucle
            if (cadena.equalsIgnoreCase("salir")) {
                break;
            }

            // Enviamos el mensaje al servidor
            fsalida.println(cadena);

            // Leemos el mensaje de respuesta del servidor
            eco = fentrada.readLine();

            // Mostramos el mensaje recibido del servidor
            System.out.println("Respuesta del servidor: " + eco);
        }

        // Cerramos los flujos y el socket al finalizar la comunicación
        fsalida.close();
        fentrada.close();
        cliente.close();
    }
}
