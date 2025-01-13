package Tema3_ComunicacionRed.Apartado3_8_Multihilos.HilosCompartenObjetos;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteAdivina {
    private static final String DIRECCION_SERVIDOR = "localhost";
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(DIRECCION_SERVIDOR, PUERTO);
             ObjectOutputStream fsalida = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream fentrada = new ObjectInputStream(socket.getInputStream())) {

            // Recibir el primer mensaje del servidor
            Datos datos = (Datos) fentrada.readObject();
            System.out.println(datos.getCadena());

            // Crear un scanner para leer la entrada del usuario
            Scanner scanner = new Scanner(System.in);

            while (datos.isJuega()) {
                // Leer el número del usuario desde el teclado
                System.out.print("Introduce un número entre 1 y 25: ");
                int intento = scanner.nextInt();  // Leer el número introducido por el jugador

                // Validar que el número esté en el rango correcto
                if (intento < 1 || intento > 25) {
                    System.out.println("Por favor, introduce un número entre 1 y 25.");
                    continue;
                }

                // Enviar el número al servidor
                datos.setCadena(String.valueOf(intento));
                fsalida.writeObject(datos);

                // Leer la respuesta del servidor
                datos = (Datos) fentrada.readObject();
                System.out.println(datos.getCadena());

                if (!datos.isJuega()) {
                    break;
                }
            }

            // Cerrar el scanner
            scanner.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
