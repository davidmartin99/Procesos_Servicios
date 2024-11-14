package Tema3_ComunicacionRed.SocketsTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPejemplo1Cliente {
    public static void main(String[] args) throws IOException {
        String Host = "localhost";
        int Puerto = 6000; // Puerto del servidor

        System.out.println("PROGRAMA CLIENTE INICIADO....");

        // Crear el socket para conectarse al servidor
        Socket Cliente = new Socket(Host, Puerto);

        // Crear flujo de salida hacia el servidor
        DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

        // Enviar un mensaje de saludo al servidor
        flujoSalida.writeUTF("Saludos al servidor desde el cliente");

        // Crear flujo de entrada desde el servidor
        DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());

        // Recibir mensaje del servidor
        System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF());

        // Cerrar flujos y sockets
        flujoEntrada.close();
        flujoSalida.close();
        Cliente.close();
    }
}
