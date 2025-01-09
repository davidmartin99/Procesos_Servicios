package Tema3_ComunicacionRed.SocketsTCP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class EnviarDatagramSocket {
    public static void main(String[] args) throws SocketException {
        try {
            // Mensaje a enviar
            String message = "¡Hola, este es un mensaje UDP!";
            byte[] sendData = message.getBytes();

            // Dirección y puerto del destino
            InetAddress destino = InetAddress.getLocalHost();
            int sendPort = 12345;

            // Crear y enviar el datagrama
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, destino, sendPort);
            DatagramSocket socket = new DatagramSocket();

            System.out.println("Enviando datagrama...");
            socket.send(sendPacket);

            // Preparar para recibir un datagrama
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            System.out.println("Esperando datagrama...");
            socket.receive(receivePacket);

            // Procesar el datagrama recibido
            int bytesReceived = receivePacket.getLength();
            String receivedMessage = new String(receivePacket.getData(), 0, bytesReceived);

            System.out.println("Número de bytes recibidos: " + bytesReceived);
            System.out.println("Contenido del paquete: " + receivedMessage);
            System.out.println("Puerto origen del mensaje: " + receivePacket.getPort());
            System.out.println("IP de origen: " + receivePacket.getAddress().getHostAddress());
            System.out.println("Puerto destino del mensaje: " + socket.getLocalPort());

            // Cerrar el socket
            socket.close();
            System.out.println("Socket cerrado.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
