package Tema3_ComunicacionRed.SocketsUDP.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPcliente {
    public static void main(String[] argv) throws Exception {
        // Obtengo la dirección de destino (host local en este caso)
        InetAddress destino = InetAddress.getLocalHost();
        int port = 12345; // Puerto al que envío el datagrama

        // Mensaje a enviar
        String saludo = "Enviando Saludos!!";
        byte[] mensaje = saludo.getBytes(); // Codifico el String a bytes

        // Construyo el datagrama a enviar
        DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, port);

        // Puerto local del cliente
        DatagramSocket socket = new DatagramSocket(34567); // El puerto local desde donde se envía

        // Información sobre el datagrama
        System.out.println("Enviando Datagrama de longitud: " + mensaje.length);
        System.out.println("Host destino: " + destino.getHostName());
        System.out.println("IP Destino: " + destino.getHostAddress());
        System.out.println("Puerto local del socket: " + socket.getLocalPort());
        System.out.println("Puerto al que envío: " + envio.getPort());

        // Envío el datagrama
        socket.send(envio);

        // Cierro el socket
        socket.close();
    }
}
