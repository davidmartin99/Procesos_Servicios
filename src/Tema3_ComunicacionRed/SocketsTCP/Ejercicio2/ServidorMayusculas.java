package Tema3_ComunicacionRed.SocketsTCP.Ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMayusculas {
    public static void main(String[] args) throws IOException {
        int puerto = 5000; // Puerto para la conexión
        ServerSocket servidor = new ServerSocket(puerto);
        System.out.println("Esperando conexión del cliente...");

        // Aceptar la conexión del cliente
        Socket clienteConectado = servidor.accept();
        System.out.println("Cliente conectado.");

        // Crear flujo de entrada para recibir mensaje del cliente
        DataInputStream flujoEntrada = new DataInputStream(clienteConectado.getInputStream());
        String mensajeCliente = flujoEntrada.readUTF();
        System.out.println("Mensaje recibido del cliente: " + mensajeCliente);

        // Convertir el mensaje a mayúsculas
        String mensajeMayusculas = mensajeCliente.toUpperCase();

        // Enviar mensaje en mayúsculas al cliente
        DataOutputStream flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());
        flujoSalida.writeUTF(mensajeMayusculas);

        // Recibir mensaje en minúsculas desde el cliente
        mensajeCliente = flujoEntrada.readUTF();
        System.out.println("Mensaje recibido en minúsculas del cliente: " + mensajeCliente);

        // Cerrar conexiones
        flujoEntrada.close();
        flujoSalida.close();
        clienteConectado.close();
        servidor.close();
    }
}
