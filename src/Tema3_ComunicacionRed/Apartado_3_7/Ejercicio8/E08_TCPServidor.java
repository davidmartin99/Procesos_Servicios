package Tema3_ComunicacionRed.Apartado_3_7.Ejercicio8;

import java.io.*;
import java.net.*;

public class E08_TCPServidor {
    public static void main(String[] args) {
        int puerto = 6000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            while (true) {
                System.out.println("Esperando conexión...");
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado.");

                // Flujos para objetos
                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());

                while (true) {
                    // Recibir objeto Numeros del cliente
                    Numeros numeros = (Numeros) entrada.readObject();
                    System.out.println("Recibido: " + numeros);

                    // Verificar si el número es <= 0 para finalizar
                    if (numeros.getNumero() <= 0) {
                        System.out.println("Número <= 0 recibido. Finalizando conexión.");
                        break;
                    }

                    // Calcular cuadrado y cubo
                    numeros.setCuadrado((long) Math.pow(numeros.getNumero(), 2));
                    numeros.setCubo((long) Math.pow(numeros.getNumero(), 3));

                    // Enviar objeto de vuelta al cliente
                    salida.writeObject(numeros);
                    salida.flush();
                    System.out.println("Enviado: " + numeros);
                }

                // Cerrar conexión con el cliente
                entrada.close();
                salida.close();
                cliente.close();
                System.out.println("Conexión con el cliente cerrada.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
