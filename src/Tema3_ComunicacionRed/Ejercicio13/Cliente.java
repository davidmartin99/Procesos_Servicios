package Tema3_ComunicacionRed.Ejercicio13;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String direccionServidor = "localhost";
        int puerto = 11223;

        try (Socket socket = new Socket(direccionServidor, puerto);
             DataInputStream entrada = new DataInputStream(socket.getInputStream());
             DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado al servidor.");
            String mensaje;

            while (true) {
                System.out.print("Introduce un mensaje (o * para salir): ");
                mensaje = scanner.nextLine(); // Lee el mensaje desde el teclado
                salida.writeUTF(mensaje); // Envía el mensaje al servidor

                if ("*".equals(mensaje)) { // Salida si el mensaje es un asterisco
                    System.out.println("Desconexión del servidor.");
                    break;
                }

                System.out.println("Mensaje enviado al servidor: " + mensaje);

                String respuesta = entrada.readUTF(); // Lee la respuesta del servidor
                System.out.println("Respuesta del servidor: " + respuesta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

