package Tema3_ComunicacionRed.Apartado_3_6;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPservidor {
	public static void main(String[] argv) throws Exception {
		byte[] bufer = new byte[1024];// bufer para recibir el datagrama
		// ASOCIO EL SOCKET AL PUERTO 12345
		DatagramSocket datagramSocket = new DatagramSocket(12345);

		// ESPERANDO DATAGRAMA
		System.out.println("Esperando Datagrama .......... ");
		DatagramPacket datagramPacket = new DatagramPacket(bufer, bufer.length);
		datagramSocket.receive(datagramPacket);// recibo datagrama
		int bytesRec = datagramPacket.getLength();// obtengo numero de bytes
		String paquete = new String(datagramPacket.getData());// obtengo String

		// VISUALIZO INFORMACIÓN
		System.out.println("Número de Bytes recibidos: " + bytesRec);
		System.out.println("Contenido del Paquete    : " + paquete.trim());
		System.out.println("Puerto origen del mensaje: " + datagramPacket.getPort());
		System.out.println("IP de origen             : " + datagramPacket.getAddress().getHostAddress());
		System.out.println("Puerto destino del mensaje:" + datagramSocket.getLocalPort());
		datagramSocket.close(); // cierro el socket
	}
}
