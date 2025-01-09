package Tema3_ComunicacionRed.Apartado_3_5;


import java.io.IOException;
import java.net.*;

public class TCPServidorBasico {

	public static void main(String[] args) throws IOException {
		
		int puerto = 6000;// Puerto 
		ServerSocket serverSocket = new ServerSocket(puerto);
		
		System.out.println("Escuchando en " + serverSocket.getLocalPort());	
		Socket socket= serverSocket.accept();//esperando a un cliente 
		//realizar acciones con cliente1
		
		Socket socket2 = serverSocket.accept();//esperando a otro cliente
		//realizar acciones con cliente2
		
		serverSocket.close();	//cierro socket servidor
	}
}


