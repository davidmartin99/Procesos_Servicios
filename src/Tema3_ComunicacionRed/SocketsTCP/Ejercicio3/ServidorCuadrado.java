package Tema3_ComunicacionRed.SocketsTCP.Ejercicio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCuadrado {
    public static void main(String[] args) {
        int puerto = 6000; // Puerto de escucha del servidor
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor en espera de conexión...");

            // Acepta la conexión del cliente
            try (Socket clienteConectado = servidor.accept()) {
                System.out.println("Cliente conectado.");

                // Crear flujo de entrada para recibir el número del cliente
                DataInputStream flujoEntrada = new DataInputStream(clienteConectado.getInputStream());
                int numero = flujoEntrada.readInt();
                System.out.println("Número recibido del cliente: " + numero);

                // Calcular el cuadrado del número
                int cuadrado = numero * numero;

                // Crear flujo de salida para enviar el resultado al cliente
                DataOutputStream flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());
                flujoSalida.writeInt(cuadrado);
                System.out.println("Enviando el cuadrado al cliente: " + cuadrado);
            } // El socket cliente se cierra automáticamente aquí

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

