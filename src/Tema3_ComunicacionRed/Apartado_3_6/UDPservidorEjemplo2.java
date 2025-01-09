package Tema3_ComunicacionRed.Apartado_3_6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPservidorEjemplo2 {

	public static void main(String[] args) throws IOException {

		// Asocio el socket al puerto 12345
		DatagramSocket datagramSocket = new DatagramSocket(12345);

		System.out.println("Servidor Esperando Datagrama .......... ");
		DatagramPacket datagramPacketRecibir;

		byte[] bufer = new byte[1024]; // para recibir el datagrama
		datagramPacketRecibir = new DatagramPacket(bufer, bufer.length);
		datagramSocket.receive(datagramPacketRecibir); // recibo datagrama

		String mensaje = new String(datagramPacketRecibir.getData()).trim();// obtengo mensaje
		System.out.println("Servidor Recibe:" + mensaje);

		// cuento el número de letras a
		int contador = 0;
		for (int i = 0; i < mensaje.length(); i++)
			if (mensaje.charAt(i) == 'a')
				contador++;

		// DIRECCION ORIGEN DEL MENSAJE
		InetAddress inetAddress = datagramPacketRecibir.getAddress();
		int puerto = datagramPacketRecibir.getPort();

		// ENVIANDO DATAGRAMA AL CLIENTE
		System.out.println("Enviando número de apariciones de la letra a=> " + contador);
		byte contadorBytes = (byte) contador; // paso entero a byte
		byte[] msgEnviarBytes = new byte[2];
		msgEnviarBytes[0] = contadorBytes;

		DatagramPacket datagramPacketEnviar = new DatagramPacket(
				msgEnviarBytes, 
				msgEnviarBytes.length, 
				inetAddress,
				puerto);
		datagramSocket.send(datagramPacketEnviar);

		// CERRAR STREAMS Y SOCKETS
		System.out.println("Cerrando conexión...");
		datagramSocket.close();
	}
}





