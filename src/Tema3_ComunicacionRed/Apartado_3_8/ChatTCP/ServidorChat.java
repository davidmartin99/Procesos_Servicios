package Tema3_ComunicacionRed.Apartado_3_8.ChatTCP;

import java.io.*;
import java.net.*;


public class ServidorChat  {
	static final int MAXIMO = 5;//MAXIMO DE CONEXIONES PERMITIDAS	
	
	public static void main(String args[]) throws IOException {
		int PUERTO = 44444;	
		
		ServerSocket serverSocket = new ServerSocket(PUERTO);
		System.out.println("Servidor iniciado...");
		
		Socket socketsClientes[] = new Socket[MAXIMO];//para controlar las conexiones	
		ComunHilos comunHilos = new ComunHilos(MAXIMO, 0, 0, socketsClientes);
		
		while (comunHilos.getCONEXIONES() < MAXIMO) {
			Socket socket = new Socket();			
			socket = serverSocket.accept();// esperando cliente
			
			comunHilos.addTabla(socket, comunHilos.getCONEXIONES());
			comunHilos.setACTUALES(comunHilos.getACTUALES() + 1);
			comunHilos.setCONEXIONES(comunHilos.getCONEXIONES() + 1);			
			
			HiloServidorChat hiloServidorChat = new HiloServidorChat(socket, comunHilos);
			hiloServidorChat.start();
		}	
		serverSocket.close();
	}//main
}//ServidorChat..  

