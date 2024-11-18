package Tema3_ComunicacionRed.SocketsTCP.Ejercicio5;

import java.io.*;
import java.net.*;

public class ServidorTCP {
    public static void main(String[] args) {
        final int PUERTO = 5000; // Puerto para escuchar conexiones

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor escuchando en el puerto " + PUERTO);

            while (true) {
                // Aceptar conexión del cliente
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());

                // Flujo para leer y escribir datos
                try (BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                     PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)) {

                    String mensaje;
                    while ((mensaje = entrada.readLine()) != null) {
                        if (mensaje.equals("*")) {
                            System.out.println("El cliente ha terminado la conexión.");
                            break;
                        }

                        // Mostrar mensaje recibido y devolverlo
                        System.out.println("Recibido: " + mensaje);
                        salida.println("Servidor: " + mensaje);
                    }
                } catch (IOException e) {
                    System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
                }

                cliente.close();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
