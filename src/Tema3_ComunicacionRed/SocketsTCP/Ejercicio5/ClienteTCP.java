package Tema3_ComunicacionRed.SocketsTCP.Ejercicio5;

import java.io.*;
import java.net.*;

public class ClienteTCP {
    public static void main(String[] args) {
        final String HOST = "localhost"; // Dirección del servidor
        final int PUERTO = 5000;         // Puerto del servidor

        try (Socket socket = new Socket(HOST, PUERTO)) {
            System.out.println("Conectado al servidor: " + HOST + ":" + PUERTO);

            // Flujo para leer y escribir datos
            try (BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
                 BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

                String mensaje;
                while (true) {
                    // Leer mensaje desde el teclado
                    System.out.print("Introduce un mensaje (* para salir): ");
                    mensaje = teclado.readLine();

                    // Enviar mensaje al servidor
                    salida.println(mensaje);

                    if (mensaje.equals("*")) {
                        System.out.println("Cerrando conexión...");
                        break;
                    }

                    // Leer respuesta del servidor
                    String respuesta = entrada.readLine();
                    System.out.println("Respuesta del servidor: " + respuesta);
                }
            }
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
