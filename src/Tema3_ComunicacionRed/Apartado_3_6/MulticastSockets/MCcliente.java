package Tema3_ComunicacionRed.Apartado_3_6.MulticastSockets;

import java.net.*;

public class MCcliente {
	public static void main(String args[]) throws Exception {
		// Se crea el socket multicast
		int puerto = 12345;// Puerto multicast
		MulticastSocket multicastSocket = new MulticastSocket(puerto);

		InetAddress inetAddress = InetAddress.getByName("225.0.0.1");// Grupo

		// Nos unimos al grupo
		multicastSocket.joinGroup(inetAddress);

		String msg = "";

		//
		while (!msg.trim().equals("*")) {
			byte[] buffer = new byte[1000];
			// Recibe el paquete del servidor multicast
			DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
			multicastSocket.receive(datagramPacket);

			msg = new String(datagramPacket.getData());
			System.out.println("Recibo: " + msg.trim());
		}
		multicastSocket.leaveGroup(inetAddress); // abandonamos grupo
		multicastSocket.close(); // cierra socket
		System.out.println("Socket cerrado...");
	}
}
