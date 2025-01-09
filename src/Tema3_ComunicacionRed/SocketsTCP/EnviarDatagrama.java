package Tema3_ComunicacionRed.SocketsTCP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;

public class EnviarDatagrama {
    public static void main(String[] args) {
        try {
            // Dirección IP del host local
            InetAddress destino = InetAddress.getLocalHost();

            // Puerto por el que escucha
            int port = 12345;

            // Mensaje que se va a enviar
            String saludo = "Enviando Saludos!!";

            // Convertir el mensaje a un array de bytes
            byte[] mensaje = saludo.getBytes();

            // Construir el datagrama a enviar
            DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, port);

            // Crear un socket para enviar el datagrama
            DatagramSocket socket = new DatagramSocket();

            // Enviar el datagrama
            socket.send(envio);
            System.out.println("Mensaje enviado: " + saludo);

            // Cerrar el socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
