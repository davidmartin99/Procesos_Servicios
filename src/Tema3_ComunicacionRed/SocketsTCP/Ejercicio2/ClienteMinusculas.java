package Tema3_ComunicacionRed.SocketsTCP.Ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteMinusculas {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int puerto = 5000; // Puerto del servidor
        Socket cliente = new Socket(host, puerto);
        System.out.println("Conectado al servidor.");

        // Leer mensaje del usuario
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce un mensaje para enviar al servidor: ");
        String mensaje = scanner.nextLine();

        // Enviar mensaje al servidor
        DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
        flujoSalida.writeUTF(mensaje);

        // Recibir mensaje en mayúsculas del servidor
        DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
        String mensajeServidor = flujoEntrada.readUTF();
        System.out.println("Mensaje recibido en mayúsculas del servidor: " + mensajeServidor);

        // Convertir el mensaje a minúsculas y enviarlo de vuelta al servidor
        String mensajeMinusculas = mensajeServidor.toLowerCase();
        flujoSalida.writeUTF(mensajeMinusculas);

        // Cerrar conexiones
        flujoEntrada.close();
        flujoSalida.close();
        cliente.close();
        scanner.close();
    }
}

