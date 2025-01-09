package Tema3_ComunicacionRed.Apartado_3_6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.util.Scanner;

public class UDPclienteEjemplo2 {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		DatagramSocket datagramSocket = new DatagramSocket();// socket cliente

		// DATOS DEL SERVIDOR al que enviar mensaje
		InetAddress inetAddress = InetAddress.getLocalHost();// localhost
		int puerto = 12345; // puerto por el que escucha

		// INTRODUCIR DATOS POR TECLADO
		System.out.print("Introduce mensaje: ");
		String mensajeParaEnviar = sc.nextLine();

		byte[] msgEnviarBytes = new byte[1024];
		msgEnviarBytes = mensajeParaEnviar.getBytes();

		// ENVIANDO DATAGRAMA AL SERVIDOR
		DatagramPacket datagramPacketEnviar = new DatagramPacket(
				msgEnviarBytes, 
				msgEnviarBytes.length, 
				inetAddress, 
				puerto);
		datagramSocket.send(datagramPacketEnviar);

		// RECIBIENDO DATAGRAMA DEL SERVIDOR
		byte[] msgRecibirBytes = new byte[2];
		DatagramPacket datagramPacketRecibir = new DatagramPacket(
				msgRecibirBytes, 
				msgRecibirBytes.length);
		System.out.println("Esperando datagrama....");
		datagramSocket.receive(datagramPacketRecibir);

		// Obtener el número de caracteres
		byte[] info = datagramPacketRecibir.getData();
		int numero = info[0];

		System.out.println("Recibo Nº de caracteres que son a=> " + numero);

		datagramSocket.close();// cerrar socket
	}
}
