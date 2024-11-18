package Tema3_ComunicacionRed.SocketsTCP.Clase;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        try (Socket socket = new Socket("192.168.30.20", 1234);//En caso de ser otro ordenador poner 192.168.30.20
             BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Conectado al servidor.");

            // Leer mensaje del servidor (solicitud de nombre)
            System.out.println(serverInput.readLine());
            String nombre = input.readLine();  // Ingresar nombre en la consola
            output.println(nombre);  // Enviar nombre al servidor

            // Crear un hilo para leer mensajes del servidor
            new Thread(() -> {
                String serverMensaje;
                try {
                    while ((serverMensaje = serverInput.readLine()) != null) {
                        System.out.println(serverMensaje);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Leer y enviar mensajes al servidor
            String mensaje;
            while ((mensaje = input.readLine()) != null) {
                output.println(mensaje);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

