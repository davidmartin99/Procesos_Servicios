package Tema3_ComunicacionRed.Apartado_3_8.ChatTCP;

import java.io.*;
import java.net.*;

public class HiloServidorChat extends Thread {
	DataInputStream dataInputStream;
	Socket socket = null;
	ComunHilos comunHilos;

	public HiloServidorChat(Socket socket, ComunHilos comunHilos) {
		this.socket = socket;
		this.comunHilos = comunHilos;
		try {
			// CREO FLUJO DE entrada para leer los mensajes
			dataInputStream = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("ERROR DE E/S");
			e.printStackTrace();
		}
	}// ..

	public void run() {
		System.out.println("NUMERO DE CONEXIONES ACTUALES: " + comunHilos.getACTUALES());

		// NADA MAS CONECTARSE LE ENVIO TODOS LOS MENSAJES
		String texto = comunHilos.getMensajes();
		enviarMensajesaTodos(texto);

		while (true) {
			String cadena = "";
			try {
				cadena = dataInputStream.readUTF();
				if (cadena.trim().equals("*")) {// EL CLIENTE SE DESCONECTA
					comunHilos.setACTUALES(comunHilos.getACTUALES() - 1);
					System.out.println("NUMERO DE CONEXIONES ACTUALES: " + comunHilos.getACTUALES());
					break;
				}
				comunHilos.setMensajes(comunHilos.getMensajes() + cadena + "\n");
				enviarMensajesaTodos(comunHilos.getMensajes());
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		} // fin while

		// se cierra el socket del cliente
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}// run

	// ENVIA LOS MENSAJES DEL CHAT A LOS CLIENTES
	private void enviarMensajesaTodos(String texto) {
		int i;
		// recorremos tabla de sockets para enviarles los mensajes
		for (i = 0; i < comunHilos.getCONEXIONES(); i++) {
			Socket socket = comunHilos.getElementoTabla(i);
			if (!socket.isClosed()) {
				try {
					DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
					dataOutputStream.writeUTF(texto);					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} // for
		
	}// EnviarMensajesaTodos

}// ..HiloServidorChat