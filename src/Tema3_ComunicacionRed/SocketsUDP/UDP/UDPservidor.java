package Tema3_ComunicacionRed.SocketsUDP.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPservidor {
    public static void main(String[] argv) throws Exception {
        // Buffer para recibir el datagrama
        byte[] bufer = new byte[1024];

        // Asocio el socket al puerto 12345
        DatagramSocket socket = new DatagramSocket(12345);

        // Esperando datagrama
        System.out.println("Esperando Datagrama...");

        // Construyo el datagrama para recibir datos
        DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);
        socket.receive(recibo); // Recibo el datagrama

        // Obtengo el número de bytes recibidos
        int bytesRec = recibo.getLength();
        // Obtengo el contenido del paquete como String
        String paquete = new String(recibo.getData(), 0, bytesRec);

        // Visualización de la información
        System.out.println("Número de Bytes recibidos: " + bytesRec);
        System.out.println("Contenido del Paquete: " + paquete.trim());
        System.out.println("Puerto origen del mensaje: " + recibo.getPort());
        System.out.println("IP de origen: " + recibo.getAddress().getHostAddress());
        System.out.println("Puerto destino del mensaje: " + socket.getLocalPort());

        // Cierro el socket
        socket.close();
    }
}
