package Tema3_ComunicacionRed.ExamenDavidMartin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Clase Cliente para conectarnos al Servidor
 */
public class Cliente {
    public static void main(String[] args) {
        String direccionServidor = "localhost";
        int puerto = 2088;

        try (Socket socket = new Socket(direccionServidor, puerto);
             // Creamos los datainputstream y dataoutputstream para enviar y recibir mensajes del servidor
             DataInputStream entrada = new DataInputStream(socket.getInputStream());
             DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado al servidor!");
            // Variables
            String numero;


            while (true) {
                System.out.print("Introduce un numero (o * para salir): ");
                numero = scanner.nextLine();
                salida.writeUTF(numero); // Envía el mensaje al servidor

                // El programa finaliza si el cliente introduce "*"
                if ("*".equals(numero)) { // Salida si el mensaje es un asterisco
                    String ultimoNumero = entrada.readUTF(); // Lee el último número del servidor
                    System.out.println("Último número introducido: " + ultimoNumero);
                    System.out.println("Desconexión del servidor.");
                    break;
                }

                // String respuesta = entrada.readUTF(); // Lee la respuesta del servidor
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//Fin main

}//Fin Cliente
