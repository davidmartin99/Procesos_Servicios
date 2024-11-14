package Tema3_ComunicacionRed.SocketsTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPejemplo1Servidor {
    public static void main(String[] arg) throws IOException {
        int numeroPuerto = 6000; // Puerto del servidor
        ServerSocket servidor = new ServerSocket(numeroPuerto);
        System.out.println("Esperando al cliente.....");

        // Aceptar la conexión del cliente
        Socket clienteConectado = servidor.accept();
        System.out.println("Cliente conectado.");

        // Crear flujo de entrada desde el cliente
        InputStream entrada = clienteConectado.getInputStream();
        DataInputStream flujoEntrada = new DataInputStream(entrada);

        // Recibir mensaje del cliente
        System.out.println("Recibiendo del CLIENTE: \n\t" + flujoEntrada.readUTF());

        // Crear flujo de salida hacia el cliente
        OutputStream salida = clienteConectado.getOutputStream();
        DataOutputStream flujoSalida = new DataOutputStream(salida);

        // Enviar un mensaje de saludo al cliente
        flujoSalida.writeUTF("Saludos al cliente desde el servidor");

        // Cerrar flujos y sockets
        flujoEntrada.close();
        flujoSalida.close();
        clienteConectado.close();
        servidor.close();
    }
}
