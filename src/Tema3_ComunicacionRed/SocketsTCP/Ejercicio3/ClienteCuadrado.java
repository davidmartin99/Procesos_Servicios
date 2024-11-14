package Tema3_ComunicacionRed.SocketsTCP.Ejercicio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteCuadrado {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000; // Puerto del servidor

        try (Socket cliente = new Socket(host, puerto)) {
            System.out.println("Conectado al servidor.");

            // Leer número entero desde el teclado
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce un número entero: ");
            int numero = scanner.nextInt();

            // Enviar el número al servidor
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            flujoSalida.writeInt(numero);

            // Recibir el cuadrado del número desde el servidor
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            int cuadrado = flujoEntrada.readInt();
            System.out.println("Cuadrado del número recibido del servidor: " + cuadrado);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


