package Tema3_ComunicacionRed.Apartado_3_6;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPcliente {
	public static void main(String[] argv) throws Exception {
		InetAddress inetAddress = InetAddress.getLocalHost();
		int port = 12345; // puerto al que envio el datagrama
		byte[] mensaje = new byte[1024];

		String saludo = "Enviando Saludos!!";
		mensaje = saludo.getBytes(); // codifico String a bytes

		// CONSTRUYO EL DATAGRAMA A ENVIAR
		DatagramPacket datagramPacket = new DatagramPacket(mensaje, mensaje.length, inetAddress, port);
		DatagramSocket datagramSocket = new DatagramSocket(34567);// Puerto local

		System.out.println("Enviando Datagrama de longitud: " + mensaje.length);
		System.out.println("Host destino : " + inetAddress.getHostName());
		System.out.println("IP Destino   : " + inetAddress.getHostAddress());
		System.out.println("Puerto local del socket: " + datagramSocket.getLocalPort());
		System.out.println("Puerto al que envio: " + datagramPacket.getPort());

		// ENVIO DATAGRAMA
		datagramSocket.send(datagramPacket);
		datagramSocket.close(); // cierro el socket
	}
}
